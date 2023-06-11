package com.uc.ecommerce.model.enums;

public enum AccountLogType {
    WRONG_ENTRY(AccountLogType.Values.WRONG_ENTRY);
    private AccountLogType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of AccountLogType");
    }
    public static class Values {
        public static final String WRONG_ENTRY= "WRONG_ENTRY";

    }
}
