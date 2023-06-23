
package com.uc.ecommerce.core.rsql.parser;


/**
 * This exception is thrown when unknown/unsupported comparison operator is parsed.
 */
public class UnknownOperatorException extends ParseException {

    private final String operator;


    public UnknownOperatorException(String operator) {
        this(operator, "Unknown operator: " + operator);
    }

    public UnknownOperatorException(String operator, String message) {
        super(message);
        this.operator = operator;
    }


    public String getOperator() {
        return operator;
    }
}
