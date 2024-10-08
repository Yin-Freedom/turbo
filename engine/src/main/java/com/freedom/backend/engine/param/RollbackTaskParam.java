package com.freedom.backend.engine.param;

import com.google.common.base.MoreObjects;

public class RollbackTaskParam extends RuntimeTaskParam {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("flowInstanceId", getFlowInstanceId())
                .add("taskInstanceId", getTaskInstanceId())
                .toString();
    }
}
