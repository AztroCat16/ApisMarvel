package com.challenge.apismarvel.util;

public class AppMessages {

    private AppMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String ERROR = "SERVER_ERROR";
    public static final String ERROR_DATA = "An error occurred while obtaining the data";
    public static final String ERROR_MD5_KEYS = "An error occurred while obtaining the MD5 of the keys";
    public static final String ERROR_FOUND = "We couldn't find that character";
    public static final String ERROR_LIMIT = "Provided value for limit param is invalid";

    public static final String SUCCESS = "SUCCESS";
    public static final String CONTINUE = "CONTINUE";
    public static final String ERROR_TYPE = "ERROR";
    public static final String CANCEL = "CANCEL";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String INVALID_LIMIT = "INVALID_LIMIT";

}
