package com.freedom.backend.engine.dao;

import com.freedom.backend.engine.entity.InstanceDataPO;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class InstanceDataDAOTest extends BaseTest {

    @Resource
    private InstanceDataDAO instanceDataDAO;

    @Test
    public void insertSuccess() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        int result = instanceDataDAO.insert(instanceDataPO);
        LOGGER.info("insertTest.result={}", result);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void insertFailed() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(instanceDataPO);
        // test DuplicateKeyException
        int result = instanceDataDAO.insert(instanceDataPO);
        LOGGER.info("insertTest.result={}", result);
        Assert.assertTrue(result == -1);
    }

    @Test
    public void select() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(instanceDataPO);
        InstanceDataPO result = instanceDataDAO.select(instanceDataPO.getFlowInstanceId(), instanceDataPO.getInstanceDataId());
        Assert.assertTrue(result.getInstanceDataId().equals(instanceDataPO.getInstanceDataId()));
    }

    @Test
    public void selectRecentOne() {
        InstanceDataPO oldInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(oldInstanceDataPO);
        InstanceDataPO newInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(newInstanceDataPO);
        InstanceDataPO result = instanceDataDAO.selectRecentOne(oldInstanceDataPO.getFlowInstanceId());
        Assert.assertTrue(result.getInstanceDataId().equals(newInstanceDataPO.getInstanceDataId()));
    }
}
