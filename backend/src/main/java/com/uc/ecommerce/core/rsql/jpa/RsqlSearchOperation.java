package com.uc.ecommerce.core.rsql.jpa;

import com.uc.ecommerce.core.rsql.parser.ast.ComparisonOperator;
import com.uc.ecommerce.core.rsql.parser.ast.RSQLOperators;


public enum RsqlSearchOperation {

    EQUAL(RSQLOperators.EQUAL),
    NOT_EQUAL(RSQLOperators.NOT_EQUAL),
    LIKE(RSQLOperators.LIKE),
    NOT_LIKE(RSQLOperators.NOT_LIKE),
    IS_NULL(RSQLOperators.IS_NULL),
    IS_NOT_NULL(RSQLOperators.IS_NOT_NULL),
    GREATER_THAN(RSQLOperators.GREATER_THAN),
    GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
    LESS_THAN(RSQLOperators.LESS_THAN),
    LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
    IN(RSQLOperators.IN),
    NOT_IN(RSQLOperators.NOT_IN);

    private ComparisonOperator operator;

    private RsqlSearchOperation(ComparisonOperator operator) {
        this.operator = operator;
    }

    public static RsqlSearchOperation getSimpleOperator(ComparisonOperator operator) {
        for (RsqlSearchOperation operation : values()) {
            if (operation.operator == operator) {
                return operation;
            }
        }
        return null;
    }
}
