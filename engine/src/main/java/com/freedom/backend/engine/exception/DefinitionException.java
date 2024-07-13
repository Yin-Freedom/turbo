package com.freedom.backend.engine.exception;

import com.freedom.backend.engine.common.ErrorEnum;

public class DefinitionException extends BaseException {

    public DefinitionException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public DefinitionException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
