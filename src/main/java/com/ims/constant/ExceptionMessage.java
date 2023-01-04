package com.ims.constant;

import lombok.Getter;

@Getter
public class ExceptionMessage {
    public static final String EMPTY_VALUE_EXCEPTION_MESSAGE = "VALUE SHOULD NOT BE EMPTY.";
    public static final String PASSWORD_NOT_MATCH_MESSAGE = "PASSWORD DOES NOT MATCH.";
    public static final String USER_ALREADY_EXIST_MESSAGE = "USER ALREADY EXISTS.";
    public static final String USER_NOT_EXIST_MESSAGE = "USER DOES NOT EXIST.";
    public static final String LOG_IN_FAIL_MESSAGE = "LOG IN FAILED.";
}
