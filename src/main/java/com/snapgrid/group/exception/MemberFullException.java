package com.snapgrid.group.exception;


public class MemberFullException extends RuntimeException{

    public MemberFullException() {
        super();
    }

    public MemberFullException(String message) {
        super(message);
    }

    public MemberFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberFullException(Throwable cause) {
        super(cause);
    }

    protected MemberFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
