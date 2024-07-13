package com.freedom.backend.engine.result;

import com.freedom.backend.engine.bo.ElementInstance;
import com.freedom.backend.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

import java.util.List;

public class ElementInstanceListResult extends CommonResult {
    private List<ElementInstance> elementInstanceList;

    public ElementInstanceListResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public List<ElementInstance> getElementInstanceList() {
        return elementInstanceList;
    }

    public void setElementInstanceList(List<ElementInstance> elementInstanceList) {
        this.elementInstanceList = elementInstanceList;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getErrCode())
                .add("errMsg", getErrMsg())
                .add("elementInstanceList", elementInstanceList)
                .toString();
    }
}
