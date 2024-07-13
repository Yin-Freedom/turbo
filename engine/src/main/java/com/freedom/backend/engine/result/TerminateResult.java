package com.freedom.backend.engine.result;

import com.freedom.backend.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

public class TerminateResult extends RuntimeResult {

    public TerminateResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getErrCode())
                .add("errMsg", getErrMsg())
                .toString();
    }
}
