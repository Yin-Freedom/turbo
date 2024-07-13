package com.freedom.backend.engine.dao;

import com.freedom.backend.engine.common.FlowInstanceStatus;
import com.freedom.backend.engine.entity.FlowInstancePO;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class ProcessInstanceDAOTest extends BaseTest {

    @Resource
    private ProcessInstanceDAO processInstanceDAO;

    @Test
    public void insert() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        int result = processInstanceDAO.insert(flowInstancePO);
        LOGGER.info("insertTest.result={}", result);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void selectByFlowInstanceId(){
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        processInstanceDAO.insert(flowInstancePO);
        String flowInstanceId = flowInstancePO.getFlowInstanceId();
        flowInstancePO = processInstanceDAO.selectByFlowInstanceId(flowInstancePO.getFlowInstanceId());
        Assert.assertTrue(StringUtils.equals(flowInstancePO.getFlowInstanceId(), flowInstanceId));
    }

    @Test
    public void updateStatusByFlowInstancePO() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        processInstanceDAO.insert(flowInstancePO);
        // change status
        processInstanceDAO.updateStatus(flowInstancePO, FlowInstanceStatus.COMPLETED);
        FlowInstancePO result = processInstanceDAO.selectByFlowInstanceId(flowInstancePO.getFlowInstanceId());
        Assert.assertTrue(result.getStatus() == FlowInstanceStatus.COMPLETED);
    }

    @Test
    public void updateStatusByFlowInstanceId() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        processInstanceDAO.insert(flowInstancePO);
        // change status
        processInstanceDAO.updateStatus(flowInstancePO.getFlowInstanceId(), FlowInstanceStatus.COMPLETED);
        FlowInstancePO result = processInstanceDAO.selectByFlowInstanceId(flowInstancePO.getFlowInstanceId());
        Assert.assertTrue(result.getStatus() == FlowInstanceStatus.COMPLETED);
    }
}
