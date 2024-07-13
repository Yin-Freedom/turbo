package com.freedom.backend.engine.dao.mapper;

import com.freedom.backend.engine.common.FlowInstanceStatus;
import com.freedom.backend.engine.entity.FlowInstancePO;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class ProcessInstanceMapperTest extends BaseTest {

    @Resource
    private ProcessInstanceMapper processInstanceMapper;

    @Test
    public void insert() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        int result = processInstanceMapper.insert(flowInstancePO);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void selectByFlowInstanceId() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        processInstanceMapper.insert(flowInstancePO);
        String flowInstanceId = flowInstancePO.getFlowInstanceId();
        flowInstancePO = processInstanceMapper.selectByFlowInstanceId(flowInstancePO.getFlowInstanceId());
        Assert.assertTrue(StringUtils.equals(flowInstancePO.getFlowInstanceId(), flowInstanceId));
    }

    @Test
    public void updateStatus() {
        FlowInstancePO flowInstancePO = EntityBuilder.buildDynamicFlowInstancePO();
        processInstanceMapper.insert(flowInstancePO);
        // change status
        flowInstancePO.setStatus(FlowInstanceStatus.COMPLETED);
        flowInstancePO.setModifyTime(new Date());
        processInstanceMapper.updateStatus(flowInstancePO);
    }
}
