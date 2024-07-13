package com.freedom.generator.utils;

/**
 * yinhuidong
 */
public class BaseResponse<T> {

    int errCode;
    String errMsg;
    T data;

    public static <T> BaseResponse<T> make(T data) {
        return (new BaseResponse<T>()).setData(data);
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
