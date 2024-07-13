package com.freedom.backend.engine.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freedom.backend.engine.dao.provider.NodeInstanceLogProvider;
import com.freedom.backend.engine.entity.NodeInstanceLogPO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NodeInstanceLogMapper extends BaseMapper<NodeInstanceLogPO> {

    @InsertProvider(type = NodeInstanceLogProvider.class, method = "batchInsert")
    boolean batchInsert(@Param("flowInstanceId") String flowInstanceId,
                        @Param("nodeInstanceLogList") List<NodeInstanceLogPO> nodeInstanceLogList);

}
