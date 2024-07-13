package com.freedom.backend.engine.service;

import com.freedom.backend.engine.common.FlowElementType;
import com.freedom.backend.engine.dao.FlowDeploymentDAO;
import com.freedom.backend.engine.dao.FlowInstanceMappingDAO;
import com.freedom.backend.engine.dao.InstanceDataDAO;
import com.freedom.backend.engine.dao.NodeInstanceDAO;
import com.freedom.backend.engine.dao.ProcessInstanceDAO;
import com.freedom.backend.engine.entity.FlowDeploymentPO;
import com.freedom.backend.engine.entity.FlowInstanceMappingPO;
import com.freedom.backend.engine.entity.FlowInstancePO;
import com.freedom.backend.engine.entity.InstanceDataPO;
import com.freedom.backend.engine.entity.NodeInstancePO;
import com.freedom.backend.engine.model.FlowElement;
import com.freedom.backend.engine.util.FlowModelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Map;

@Service
public class InstanceDataService {

    @Resource
    private InstanceDataDAO instanceDataDAO;

    @Resource
    private ProcessInstanceDAO processInstanceDAO;

    @Resource
    private FlowDeploymentDAO flowDeploymentDAO;

    @Resource
    private NodeInstanceDAO nodeInstanceDAO;

    @Resource
    private FlowInstanceMappingDAO flowInstanceMappingDAO;

    @Resource
    private FlowInstanceService flowInstanceService;

    public InstanceDataPO select(String flowInstanceId, String instanceDataId, boolean effectiveForSubFlowInstance) {
        InstanceDataPO instanceDataPO = instanceDataDAO.select(flowInstanceId, instanceDataId);
        if (!effectiveForSubFlowInstance) {
            return instanceDataPO;
        }
        if (instanceDataPO != null) {
            return instanceDataPO;
        }
        String subFlowInstanceId = flowInstanceService.getFlowInstanceIdByRootFlowInstanceIdAndInstanceDataId(flowInstanceId, instanceDataId);
        return instanceDataDAO.select(subFlowInstanceId, instanceDataId);
    }

    public InstanceDataPO select(String flowInstanceId, boolean effectiveForSubFlowInstance) {
        InstanceDataPO instanceDataPO = instanceDataDAO.selectRecentOne(flowInstanceId);
        if (!effectiveForSubFlowInstance) {
            return instanceDataPO;
        }
        FlowInstancePO flowInstancePO = processInstanceDAO.selectByFlowInstanceId(flowInstanceId);
        FlowDeploymentPO flowDeploymentPO = flowDeploymentDAO.selectByDeployId(flowInstancePO.getFlowDeployId());
        Map<String, FlowElement> flowElementMap = FlowModelUtil.getFlowElementMap(flowDeploymentPO.getFlowModel());

        NodeInstancePO nodeInstancePO = nodeInstanceDAO.selectRecentOne(flowInstanceId);
        int elementType = FlowModelUtil.getElementType(nodeInstancePO.getNodeKey(), flowElementMap);
        if (elementType != FlowElementType.CALL_ACTIVITY) {
            return instanceDataDAO.selectRecentOne(flowInstanceId);
        } else {
            FlowInstanceMappingPO flowInstanceMappingPO = flowInstanceMappingDAO.selectFlowInstanceMappingPO(flowInstanceId, nodeInstancePO.getNodeInstanceId());
            return select(flowInstanceMappingPO.getSubFlowInstanceId(), true);
        }
    }
}
