package com.freedom.backend.engine.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.freedom.backend.engine.entity.NodeInstanceLogPO;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class NodeInstanceLogDAOTest extends BaseTest {

    @Resource
    private NodeInstanceLogDAO nodeInstanceLogDAO;

    @Test
    public void batchInsert() {
        NodeInstanceLogPO nodeInstanceLogPO = EntityBuilder.buildNodeInstanceLogPO();
        List<NodeInstanceLogPO> nodeInstanceLogPOList = new ArrayList<>();
        nodeInstanceLogPOList.add(nodeInstanceLogPO);
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO());
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO());
        nodeInstanceLogDAO.insertList(nodeInstanceLogPOList);

        QueryWrapper<NodeInstanceLogPO> entityWrapper = new QueryWrapper<>();
        entityWrapper.in("flow_instance_id", nodeInstanceLogPO.getFlowInstanceId());
        nodeInstanceLogPOList = nodeInstanceLogDAO.list(entityWrapper);
        Assert.assertTrue(nodeInstanceLogPOList.size() == 3);
    }
}
