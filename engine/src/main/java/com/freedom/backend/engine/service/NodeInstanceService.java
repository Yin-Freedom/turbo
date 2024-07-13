package com.freedom.backend.engine.service;

import com.freedom.backend.engine.common.FlowElementType;
import com.freedom.backend.engine.dao.FlowDeploymentDAO;
import com.freedom.backend.engine.dao.NodeInstanceDAO;
import com.freedom.backend.engine.dao.ProcessInstanceDAO;
import com.freedom.backend.engine.entity.FlowDeploymentPO;
import com.freedom.backend.engine.entity.FlowInstancePO;
import com.freedom.backend.engine.entity.NodeInstancePO;
import com.freedom.backend.engine.model.FlowElement;
import com.freedom.backend.engine.util.FlowModelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class NodeInstanceService {

    @Resource
    private NodeInstanceDAO nodeInstanceDAO;

    @Resource
    private ProcessInstanceDAO processInstanceDAO;

    @Resource
    private FlowDeploymentDAO flowDeploymentDAO;

    @Resource
    private FlowInstanceService flowInstanceService;

    public NodeInstancePO selectByNodeInstanceId(String flowInstanceId, String nodeInstanceId, boolean effectiveForSubFlowInstance) {
        NodeInstancePO nodeInstancePO = nodeInstanceDAO.selectByNodeInstanceId(flowInstanceId, nodeInstanceId);
        if (!effectiveForSubFlowInstance) {
            return nodeInstancePO;
        }
        if (nodeInstancePO != null) {
            return nodeInstancePO;
        }
        String subFlowInstanceId = flowInstanceService.getFlowInstanceIdByRootFlowInstanceIdAndNodeInstanceId(flowInstanceId, nodeInstanceId);
        return nodeInstanceDAO.selectByNodeInstanceId(subFlowInstanceId, nodeInstanceId);
    }

    public NodeInstancePO selectRecentEndNode(String flowInstanceId) {
        FlowInstancePO rootFlowInstancePO = processInstanceDAO.selectByFlowInstanceId(flowInstanceId);
        FlowDeploymentPO rootFlowDeploymentPO = flowDeploymentDAO.selectByDeployId(rootFlowInstancePO.getFlowDeployId());
        Map<String, FlowElement> rootFlowElementMap = FlowModelUtil.getFlowElementMap(rootFlowDeploymentPO.getFlowModel());

        List<NodeInstancePO> nodeInstancePOList = nodeInstanceDAO.selectDescByFlowInstanceId(flowInstanceId);
        for (NodeInstancePO nodeInstancePO : nodeInstancePOList) {
            int elementType = FlowModelUtil.getElementType(nodeInstancePO.getNodeKey(), rootFlowElementMap);
            if (elementType == FlowElementType.END_EVENT) {
                return nodeInstancePO;
            }
        }
        return null;
    }
}
