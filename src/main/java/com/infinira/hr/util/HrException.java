package com.infinira.hr.util;

public class HrException extends RuntimeException {
    public HrException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
    public HrException(String errorMessage) {
        super(errorMessage);
    }

}