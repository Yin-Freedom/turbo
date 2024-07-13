package com.freedom.backend.demo.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freedom.backend.demo.pojo.GetFlowModuleListRequest;
import com.freedom.backend.engine.dao.FlowDefinitionDAO;
import com.freedom.backend.engine.entity.FlowDefinitionPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: james zhangxiao
 * @Date: 4/11/22
 * @Description: 流程处理类
 */
@Service
public class FlowServiceImpl {

    @Resource
    private FlowDefinitionDAO flowDefinitionDAO;

    /**
     * 查询流程列表
     *
     * @param getFlowModuleListRequest 查询流程列表参数
     * @return 查询流程结果
     */
    public IPage<FlowDefinitionPO> getFlowModuleList(GetFlowModuleListRequest getFlowModuleListRequest) {
        Page<FlowDefinitionPO> page = new Page<>();
        if (getFlowModuleListRequest.getSize() != null && getFlowModuleListRequest.getCurrent() != null) {
            page.setCurrent(getFlowModuleListRequest.getCurrent());
            page.setSize(getFlowModuleListRequest.getSize());
        }
        QueryWrapper<FlowDefinitionPO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowName())) {
            queryWrapper.like("flow_name", getFlowModuleListRequest.getFlowName());
        }
        return flowDefinitionDAO.page(page, queryWrapper);
    }

}
