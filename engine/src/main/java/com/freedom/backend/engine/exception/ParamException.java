package com.freedom.backend.engine.exception;

import com.freedom.backend.engine.common.ErrorEnum;

public class ParamException extends BaseException {

    public ParamException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public ParamException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}

