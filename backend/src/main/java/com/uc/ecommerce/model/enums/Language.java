package com.uc.ecommerce.model.enums;

import java.util.Locale;

public enum Language {
    TURKISH,
    ENGLISH;

    public static Locale toLocale(Language language) {
        switch (language) {
            case TURKISH:
                return Locale.forLanguageTag("tr");
            case ENGLISH:
                return Locale.forLanguageTag("en");
            default:
                return Locale.forLanguageTag("tr");
        }
    }
}
