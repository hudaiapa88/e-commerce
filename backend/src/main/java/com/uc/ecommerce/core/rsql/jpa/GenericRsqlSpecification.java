package com.uc.ecommerce.core.rsql.jpa;


import com.uc.ecommerce.core.rsql.parser.ast.ComparisonOperator;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //TODO count query için bir
        if (Long.class != query.getResultType() && Long.class != query.getResultType()) {

            Expression<?> expression = null;
            if (property.contains("-")) {
                arguments.set(0, arguments.get(0).replace(" ", ""));
                List<String> columns = Arrays.asList(property.split("-"));
                for (int i = 0; i < columns.size(); i++) {
                    expression = builder.concat(i == 0 ? addJoinColumn(root, columns.get(i)) : (Expression<String>) expression, builder.concat("", addJoinColumn(root, columns.get(i == 0 ? i + 1 : i))));
                }
            } else {
                expression = addJoinColumn(root);

            }
            List<Object> args = castArguments(expression.getJavaType());
            Object argument = args.get(0);
            if (argument.toString().replace("*", "").equals("")) {
                return builder.conjunction();
            }


            switch (RsqlSearchOperation.getSimpleOperator(operator)) {

                case EQUAL: {
                    if (argument instanceof String) {
                        return builder.equal(expression, argument.toString());
                    } else if (argument == null) {
                        return builder.isNull(expression);
                    } else {
                        return builder.equal(expression, argument);
                    }
                }
                case NOT_EQUAL: {
                    if (argument instanceof String) {
                        return builder.notEqual(expression, argument.toString());
                    } else if (argument == null) {
                        return builder.isNull(expression);
                    } else {
                        return builder.equal(expression, argument);
                    }
                }
                case LIKE: {
                    if (argument instanceof String) {
                        return builder.like((Expression<String>) expression, "%" + argument.toString().replace("*", "") + "%");
                    } else if (argument == null) {
                        return builder.isNull(expression);
                    } else {
                        return builder.equal(expression, argument);
                    }
                }
                case NOT_LIKE: {
                    if (argument instanceof String) {
                        return builder.notLike((Expression<String>) expression, "%" + argument.toString().replace("*", "") + "%");
                    } else if (argument == null) {
                        return builder.isNotNull(expression);
                    } else {
                        return builder.notEqual(expression, argument);
                    }
                }
                case GREATER_THAN: {
                    if (expression.getJavaType().equals(LocalDate.class)) {
                        return builder.greaterThan((Expression<LocalDate>) expression, LocalDate.parse(argument.toString()));
                    }
                    if (expression.getJavaType().equals(LocalDateTime.class)) {
                        return builder.greaterThan((Expression<LocalDateTime>) expression, LocalDateTime.parse(argument.toString()));
                    }
                    return builder.greaterThan((Expression<String>) expression, argument.toString());
                }
                case GREATER_THAN_OR_EQUAL: {
                    if (expression.getJavaType().equals(LocalDate.class)) {
                        return builder.greaterThanOrEqualTo((Expression<LocalDate>) expression, LocalDate.parse(argument.toString()));
                    }
                    if (expression.getJavaType().equals(LocalDateTime.class)) {
                        return builder.greaterThanOrEqualTo((Expression<LocalDateTime>) expression, LocalDateTime.parse(argument.toString()));
                    }
                    return builder.greaterThanOrEqualTo((Expression<String>) expression, argument.toString());
                }
                case LESS_THAN: {
                    if (expression.getJavaType().equals(LocalDate.class)) {

                        return builder.lessThan((Expression<LocalDate>) expression, LocalDate.parse(argument.toString()));
                    }
                    if (expression.getJavaType().equals(LocalDateTime.class)) {

                        return builder.lessThan((Expression<LocalDateTime>) expression, LocalDateTime.parse(argument.toString()));
                    }
                    return builder.lessThan((Expression<String>) expression, argument.toString());
                }
                case LESS_THAN_OR_EQUAL: {
                    if (expression.getJavaType().equals(LocalDate.class)) {

                        return builder.lessThanOrEqualTo((Expression<LocalDate>) expression, LocalDate.parse(argument.toString()));
                    }
                    if (expression.getJavaType().equals(LocalDateTime.class)) {

                        return builder.lessThanOrEqualTo((Expression<LocalDateTime>) expression, LocalDateTime.parse(argument.toString()));
                    }
                    return builder.lessThanOrEqualTo((Expression<String>) expression, argument.toString());
                }
                case IN:
                    return expression.in(args);
                case NOT_IN:
                    return builder.not(expression.in(args));
                case IS_NULL:
                    return builder.isNull(expression);
                case IS_NOT_NULL:
                    return builder.isNotNull(expression);

            }
        }
        return null;
    }

    private List<Object> castArguments(Class type) {

        List<Object> args = arguments.stream().map(arg -> {
   /*         if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else {
                return arg;
            }*/
          /*  if(type.isAssignableFrom(Long.class)) {
                return Long.valueOf(arg);
            }*/
            if (type.isAssignableFrom(Double.class)) {
                return Double.valueOf(arg);
            } else if (type.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(arg);
            } else if (Enum.class.isAssignableFrom(type)) {
                return Enum.valueOf(type, arg);
            } else if (type.isAssignableFrom(String.class)) {
                return String.valueOf(arg);
            } else if (type.isAssignableFrom(UUID.class)) {
                return UUID.fromString(arg);
            } else if (type.isAssignableFrom(Boolean.class)) {
                return Boolean.valueOf(arg);
            }
         /*   else if (type.isAssignableFrom(LocalDate.class)) {
                return LocalDate.parse(arg);
            } else if (type.isAssignableFrom(LocalDateTime.class)) {
                return LocalDateTime.parse(arg);
            }*/

            return arg;
        }).collect(Collectors.toList());

        return args;
    }

    private Path addJoinColumn(Root<T> root) {
        Join<Object, Object> joinParent = null;
        Boolean isJoinColumn = !(property.lastIndexOf(".") == -1);
        if (isJoinColumn) {
            List<String> properties = Arrays.asList(property.split("\\."));
            List<String> columns = properties.subList(0, properties.size() - 1);
            property = properties.get(properties.size() - 1);
            joinParent = root.join(columns.get(0), JoinType.LEFT);
            if (columns.size() > 1) {
                for (int i = 1; i < columns.size(); i++) {
                    joinParent = joinParent.join(columns.get(i), JoinType.LEFT);
                }
            }

            return joinParent.get(property);
        } else {
            return root.get(property);
        }

    }

    private Path addJoinColumn(Root<T> root, String property) {
        Join<Object, Object> joinParent = null;
        Boolean isJoinColumn = !(property.lastIndexOf(".") == -1);
        if (isJoinColumn) {
            List<String> properties = Arrays.asList(property.split("\\."));
            List<String> columns = properties.subList(0, properties.size() - 1);
            property = properties.get(properties.size() - 1);
            joinParent = root.join(columns.get(0), JoinType.LEFT);
            if (columns.size() > 1) {
                for (int i = 1; i < columns.size(); i++) {
                    joinParent = joinParent.join(columns.get(i), JoinType.LEFT);
                }
            }

            return joinParent.get(property);
        } else {
            return root.get(property);
        }

    }

}
