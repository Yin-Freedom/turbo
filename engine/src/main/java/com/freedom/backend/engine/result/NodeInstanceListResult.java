package com.freedom.backend.engine.result;

import com.freedom.backend.engine.bo.NodeInstance;
import com.freedom.backend.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

import java.util.List;

public class NodeInstanceListResult extends CommonResult {
    private List<NodeInstance> nodeInstanceList;

    public NodeInstanceListResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public List<NodeInstance> getNodeInstanceList() {
        return nodeInstanceList;
    }

    public void setNodeInstanceList(List<NodeInstance> nodeInstanceList) {
        this.nodeInstanceList = nodeInstanceList;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getErrCode())
                .add("errMsg", getErrMsg())
                .add("nodeInstanceList", nodeInstanceList)
                .toString();
    }
}
