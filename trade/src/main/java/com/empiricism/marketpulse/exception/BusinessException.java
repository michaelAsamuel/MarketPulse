package com.empiricism.marketpulse.exception;

public class BusinessException extends RuntimeException {
    private final String msg;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
