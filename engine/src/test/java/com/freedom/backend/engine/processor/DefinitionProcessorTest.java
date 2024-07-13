package com.freedom.backend.engine.processor;


import com.freedom.backend.engine.common.ErrorEnum;
import com.freedom.backend.engine.param.CreateFlowParam;
import com.freedom.backend.engine.param.DeployFlowParam;
import com.freedom.backend.engine.param.GetFlowModuleParam;
import com.freedom.backend.engine.param.UpdateFlowParam;
import com.freedom.backend.engine.result.CreateFlowResult;
import com.freedom.backend.engine.result.DeployFlowResult;
import com.freedom.backend.engine.result.FlowModuleResult;
import com.freedom.backend.engine.result.UpdateFlowResult;
import com.freedom.backend.engine.runner.BaseTest;
import com.freedom.backend.engine.util.EntityBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


public class DefinitionProcessorTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefinitionProcessor.class);
    @Resource DefinitionProcessor definitionProcessor;

    @Test
    public void createTest() {
        CreateFlowParam createFlowParam = EntityBuilder.buildCreateFlowParam();
        CreateFlowResult createFlowResult = definitionProcessor.create(createFlowParam);
        LOGGER.info("createFlow.||createFlowResult={}", createFlowResult);
        Assert.assertTrue(createFlowResult.getErrCode() == ErrorEnum.SUCCESS.getErrNo());
    }

    @Test
    public void updateTest() {
        CreateFlowParam createFlowParam = EntityBuilder.buildCreateFlowParam();
        UpdateFlowParam updateFlowParam = EntityBuilder.buildUpdateFlowParam();

        CreateFlowResult createFlowResult = definitionProcessor.create(createFlowParam);
        updateFlowParam.setFlowModuleId(createFlowResult.getFlowModuleId());
        UpdateFlowResult updateFlowResult = definitionProcessor.update(updateFlowParam);
        LOGGER.info("updateFlow.||result={}", updateFlowParam);
        Assert.assertTrue(updateFlowResult.getErrCode() == ErrorEnum.SUCCESS.getErrNo());
    }

    @Test
    public void deployTest() {
        CreateFlowParam createFlowParam = EntityBuilder.buildCreateFlowParam();
        UpdateFlowParam updateFlowParam = EntityBuilder.buildUpdateFlowParam();
        DeployFlowParam deployFlowParam = EntityBuilder.buildDeployFlowParm();

        CreateFlowResult createFlowResult = definitionProcessor.create(createFlowParam);
        updateFlowParam.setFlowModuleId(createFlowResult.getFlowModuleId());
        UpdateFlowResult updateFlowResult = definitionProcessor.update(updateFlowParam);
        Assert.assertTrue(updateFlowResult.getErrCode() == ErrorEnum.SUCCESS.getErrNo());
        deployFlowParam.setFlowModuleId(createFlowResult.getFlowModuleId());
        DeployFlowResult deployFlowResult = definitionProcessor.deploy(deployFlowParam);
        LOGGER.info("deployFlowTest.||deployFlowResult={}", deployFlowResult);
        Assert.assertTrue(deployFlowResult.getErrCode() == ErrorEnum.SUCCESS.getErrNo());
    }

    @Test
    public void getFlowModule() {
        CreateFlowParam createFlowParam = EntityBuilder.buildCreateFlowParam();
        UpdateFlowParam updateFlowParam = EntityBuilder.buildUpdateFlowParam();
        DeployFlowParam deployFlowParam = EntityBuilder.buildDeployFlowParm();
        GetFlowModuleParam flowModuleParam = new GetFlowModuleParam();

        CreateFlowResult createFlowResult = definitionProcessor.create(createFlowParam);
        updateFlowParam.setFlowModuleId(createFlowResult.getFlowModuleId());
        UpdateFlowResult updateFlowResult = definitionProcessor.update(updateFlowParam);
        Assert.assertTrue(updateFlowResult.getErrCode() == ErrorEnum.SUCCESS.getErrNo());

        flowModuleParam.setFlowModuleId(updateFlowParam.getFlowModuleId());
        FlowModuleResult flowModuleResultByFlowModuleId = definitionProcessor.getFlowModule(flowModuleParam);
        Assert.assertTrue(flowModuleResultByFlowModuleId.getFlowModuleId().equals(createFlowResult.getFlowModuleId()));

        deployFlowParam.setFlowModuleId(createFlowResult.getFlowModuleId());
        DeployFlowResult deployFlowResult = definitionProcessor.deploy(deployFlowParam);
        flowModuleParam.setFlowDeployId(deployFlowResult.getFlowDeployId());
        flowModuleParam.setFlowModuleId(null);
        FlowModuleResult flowModuleResultByDeployId = definitionProcessor.getFlowModule(flowModuleParam);
        Assert.assertTrue(flowModuleResultByDeployId.getFlowModel().equals(updateFlowParam.getFlowModel()));
    }

}