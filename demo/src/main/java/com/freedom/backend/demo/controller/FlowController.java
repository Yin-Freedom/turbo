package com.freedom.backend.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.freedom.backend.demo.dto.QueryDTO;
import com.freedom.backend.demo.pojo.BaseResponse;
import com.freedom.backend.demo.pojo.request.CreateFlowRequest;
import com.freedom.backend.demo.pojo.request.UpdateFlowRequest;
import com.freedom.backend.demo.util.ResponseUtil;
import com.freedom.backend.engine.common.ErrorEnum;
import com.freedom.backend.engine.dao.FlowDefinitionDAO;
import com.freedom.backend.engine.dao.NodeInstanceDAO;
import com.freedom.backend.engine.dao.ProcessInstanceDAO;
import com.freedom.backend.engine.engine.ProcessEngine;
import com.freedom.backend.engine.entity.FlowDefinitionPO;
import com.freedom.backend.engine.entity.FlowInstancePO;
import com.freedom.backend.engine.entity.NodeInstanceLogPO;
import com.freedom.backend.engine.entity.NodeInstancePO;
import com.freedom.backend.engine.param.CommitTaskParam;
import com.freedom.backend.engine.param.DeployFlowParam;
import com.freedom.backend.engine.param.GetFlowModuleParam;
import com.freedom.backend.engine.param.RollbackTaskParam;
import com.freedom.backend.engine.param.StartProcessParam;
import com.freedom.backend.engine.result.CommitTaskResult;
import com.freedom.backend.engine.result.CreateFlowResult;
import com.freedom.backend.engine.result.DeployFlowResult;
import com.freedom.backend.engine.result.FlowModuleResult;
import com.freedom.backend.engine.result.RollbackTaskResult;
import com.freedom.backend.engine.result.RuntimeResult;
import com.freedom.backend.engine.result.StartProcessResult;
import com.freedom.backend.engine.result.UpdateFlowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description: logigcFlow与turbo交互样例
 */
@RestController
@RequestMapping("/api/flow")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Resource
    private ResponseUtil responseUtil;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private FlowDefinitionDAO flowDefinitionDAO;
    @Resource
    private NodeInstanceDAO nodeInstanceDAO;
    @Resource
    private ProcessInstanceDAO processInstanceDAO;

    /**
     * 分页查询流程列表
     *
     * @param query 分页请求参数
     * @return 返回列表数据
     */
    @RequestMapping(value = "/findDefinitionByPage")
    public BaseResponse<IPage<FlowDefinitionPO>> findDefinitionByPage(@RequestBody QueryDTO query) {
        try {
            IPage<FlowDefinitionPO> page = flowDefinitionDAO.findByPage(query.getCurrent(), query.getSize(), query.getBlur());
            return responseUtil.buildSuccessResponse(page);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/findFlowInstanceByPage")
    public BaseResponse<IPage> findFlowInstanceByPage(@RequestBody QueryDTO query) {
        try {
            IPage<FlowInstancePO> pageRes = processInstanceDAO.findByPage(query.getCurrent(), query.getSize(), query.getBlur());
            return responseUtil.buildSuccessResponse(pageRes);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/findNodeInstanceByPage")
    public BaseResponse<IPage> findNodeInstanceByPage(@RequestBody QueryDTO query) {
        try {
            IPage<NodeInstancePO> pageRes = nodeInstanceDAO.findByPage(query.getCurrent(), query.getSize(), query.getBlur());
            return responseUtil.buildSuccessResponse(pageRes);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/findNodeInstanceLogByPage")
    public BaseResponse<IPage<NodeInstanceLogPO>> findNodeInstanceLogByPage(@RequestBody RollbackTaskParam param) {
        try {
            IPage<NodeInstanceLogPO> pageRes = null;
            return responseUtil.buildSuccessResponse(pageRes);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 创建流程
     *
     * @param createFlowRequest
     * @return
     */
    @RequestMapping(value = "/createFlow")
    public BaseResponse<CreateFlowResult> createFlow(@RequestBody CreateFlowRequest createFlowRequest) {
        try {
            CreateFlowResult result = processEngine.createFlow(createFlowRequest);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 保存流程模型数据
     *
     * @param updateFlowParam flowModuleId 使用createFlow返回的flowModuleId
     * @return 成功 or 失败
     */
    @RequestMapping(value = "/saveFlowModel")
    public BaseResponse<UpdateFlowResult> saveFlowModel(@RequestBody UpdateFlowRequest updateFlowParam) {
        try {
            UpdateFlowResult result = processEngine.updateFlow(updateFlowParam);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 发布流程
     *
     * @param deployFlowParam flowModuleId 使用createFlow返回的flowModuleId
     * @return
     */
    @RequestMapping(value = "/deployFlow")
    public BaseResponse<DeployFlowResult> deployFlow(@RequestBody DeployFlowParam deployFlowParam) {
        try {
            DeployFlowResult result = processEngine.deployFlow(deployFlowParam);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 查询流程
     *
     * @param getFlowModuleParam 单个流程请求参数
     * @return 单个流程数据
     */
    @RequestMapping(value = "/queryFlow")
    public BaseResponse<FlowModuleResult> queryFlow(@RequestBody GetFlowModuleParam getFlowModuleParam) {
        try {
            FlowModuleResult result = processEngine.getFlowModule(getFlowModuleParam);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/startProcess")
    public BaseResponse<RuntimeResult> startProcess(@RequestBody StartProcessParam param) {
        try {
            StartProcessResult result = processEngine.startProcess(param);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/commitTask")
    public BaseResponse<RuntimeResult> commitTask(@RequestBody CommitTaskParam param) {
        try {
            CommitTaskResult result = processEngine.commitTask(param);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/rollbackTask")
    public BaseResponse<RuntimeResult> rollbackTask(@RequestBody RollbackTaskParam param) {
        try {
            RollbackTaskResult result = processEngine.rollbackTask(param);
            return responseUtil.buildSuccessResponse(result);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/upload")
    public BaseResponse<RuntimeResult> upload() {
        try {
            return responseUtil.buildSuccessResponse(null);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }
}
