package com.freedom.backend.engine.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freedom.backend.engine.entity.FlowDefinitionPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FlowDefinitionMapper extends BaseMapper<FlowDefinitionPO> {

    @Select("SELECT * FROM em_flow_definition WHERE flow_module_id=#{flowModuleId}")
    FlowDefinitionPO selectByFlowModuleId(@Param("flowModuleId") String flowModuleId);
}
