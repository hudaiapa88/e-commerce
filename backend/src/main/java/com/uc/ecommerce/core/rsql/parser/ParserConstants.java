package com.uc.ecommerce.core.rsql.parser;


interface ParserConstants {
    int EOF = 0;
    int ALPHA = 3;
    int ESCAPED_CHAR = 4;
    int UNRESERVED_STR = 5;
    int SINGLE_QUOTED_STR = 6;
    int DOUBLE_QUOTED_STR = 7;
    int AND = 8;
    int OR = 9;
    int LPAREN = 10;
    int RPAREN = 11;
    int COMP_FIQL = 12;
    int COMP_ALT = 13;
    int DEFAULT = 0;
    String[] tokenImage = new String[]{"<EOF>", "\" \"", "\"\\t\"", "<ALPHA>", "<ESCAPED_CHAR>", "<UNRESERVED_STR>", "<SINGLE_QUOTED_STR>", "<DOUBLE_QUOTED_STR>", "<AND>", "<OR>", "\"(\"", "\")\"", "<COMP_FIQL>", "<COMP_ALT>"};
}

