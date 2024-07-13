package com.freedom.backend.engine.exception;

import com.freedom.backend.engine.common.ErrorEnum;

public class ReentrantException extends ProcessException {

    public ReentrantException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public ReentrantException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
