package com.uc.ecommerce.model.enums;

public enum LogType {

    ACCOUNT(LogType.Values.ACCOUNT);
    private LogType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of LogType");
    }
    public static class Values {
        public static final String ACCOUNT= "ACCOUNT";

    }
}
