package com.fengzhi.rxjava2demo66.base;

public class ResultException extends RuntimeException {
    private String errorCode = "-10000";

    public ResultException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return super.toString() + ",ResultException{" + "errorCode='" + errorCode + '\'' + '}';
    }
}
