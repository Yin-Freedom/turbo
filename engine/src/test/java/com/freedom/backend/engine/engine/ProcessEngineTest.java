package com.freedom.backend.engine.engine;

import com.freedom.backend.engine.engine.impl.ProcessEngineImpl;
import com.freedom.backend.engine.param.CreateFlowParam;
import com.freedom.backend.engine.param.DeployFlowParam;
import com.freedom.backend.engine.param.UpdateFlowParam;
import com.freedom.backend.engine.result.CreateFlowResult;
import com.freedom.backend.engine.result.DeployFlowResult;
import com.freedom.backend.engine.result.UpdateFlowResult;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class ProcessEngineTest extends BaseTest {

    @Autowired
    ProcessEngine processEngine;

    @Resource
    ProcessEngineImpl processEngineImpl;

    @Test
    public void createFlowTest() {
        CreateFlowParam createFlowParam = EntityBuilder.buildCreateFlowParam();
        try {
            CreateFlowResult createFlowResult = processEngineImpl.createFlow(createFlowParam);
            LOGGER.info("flowModuleId={}", createFlowResult.getFlowModuleId());
        } catch (Exception e) {
            LOGGER.error("", e);
            LOGGER.error("", e);
        }
    }

    @Test
    public void updateFlowTest() {
        UpdateFlowParam updateFlowParam = EntityBuilder.buildUpdateFlowParam();
        updateFlowParam.setFlowModuleId("a038f993-1d7c-11ea-928e-8214dae31b03");
        try {
            UpdateFlowResult updateFlowResult = processEngineImpl.updateFlow(updateFlowParam);
            LOGGER.info("result={}", updateFlowResult);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @Test
    public void deployFlowTest() {
        DeployFlowParam deployFlowParam = new DeployFlowParam("testTenant", "testCaller");
        deployFlowParam.setFlowModuleId("76bb65d9-35ef-11ea-a4cd-5ef9e2914105");
        deployFlowParam.setOperator("liming");
        try {
            DeployFlowResult deployFlowResult = processEngineImpl.deployFlow(deployFlowParam);
            LOGGER.info("deployFlowResult={}", deployFlowResult);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
