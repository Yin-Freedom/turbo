package com.freedom.backend.engine.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freedom.backend.engine.entity.FlowInstancePO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProcessInstanceMapper extends BaseMapper<FlowInstancePO> {

    @Select("SELECT * FROM ei_flow_instance WHERE flow_instance_id=#{flowInstanceId}")
    FlowInstancePO selectByFlowInstanceId(@Param("flowInstanceId") String flowInstanceId);

    @Update("UPDATE ei_flow_instance SET status=#{status}, modify_time=#{modifyTime} " +
        "WHERE flow_instance_id=#{flowInstanceId}")
    void updateStatus(FlowInstancePO entity);
}
