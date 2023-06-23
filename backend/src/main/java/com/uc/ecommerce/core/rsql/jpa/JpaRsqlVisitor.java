package com.uc.ecommerce.core.rsql.jpa;


import com.uc.ecommerce.core.rsql.parser.ast.AndNode;
import com.uc.ecommerce.core.rsql.parser.ast.ComparisonNode;
import com.uc.ecommerce.core.rsql.parser.ast.OrNode;
import com.uc.ecommerce.core.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;


public class JpaRsqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    private GenericRsqlSpecBuilder<T> builder;

    public JpaRsqlVisitor() {
        builder = new GenericRsqlSpecBuilder<T>();
    }

    @Override
    public Specification<T> visit(AndNode node, Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(OrNode node, Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(ComparisonNode node, Void params) {
        return builder.createSpecification(node);
    }
}