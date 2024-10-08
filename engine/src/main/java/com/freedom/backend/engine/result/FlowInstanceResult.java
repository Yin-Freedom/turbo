package com.freedom.backend.engine.result;

import com.freedom.backend.engine.bo.FlowInstanceBO;
import com.google.common.base.MoreObjects;

public class FlowInstanceResult extends CommonResult {

    private FlowInstanceBO flowInstanceBO;

    public FlowInstanceBO getFlowInstanceBO() {
        return flowInstanceBO;
    }

    public void setFlowInstanceBO(FlowInstanceBO flowInstanceBO) {
        this.flowInstanceBO = flowInstanceBO;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("errCode", getErrCode())
            .add("errMsg", getErrMsg())
            .add("flowInstanceBO", flowInstanceBO)
            .toString();
    }
}
