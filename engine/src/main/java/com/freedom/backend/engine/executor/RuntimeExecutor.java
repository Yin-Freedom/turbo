package com.freedom.backend.engine.executor;


import com.freedom.backend.engine.common.RuntimeContext;
import com.freedom.backend.engine.dao.FlowInstanceMappingDAO;
import com.freedom.backend.engine.dao.InstanceDataDAO;
import com.freedom.backend.engine.dao.NodeInstanceDAO;
import com.freedom.backend.engine.dao.NodeInstanceLogDAO;
import com.freedom.backend.engine.dao.ProcessInstanceDAO;
import com.freedom.backend.engine.exception.ProcessException;
import com.freedom.backend.engine.util.IdGenerator;
import com.freedom.backend.engine.util.StrongUuidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public abstract class RuntimeExecutor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RuntimeExecutor.class);

    @Resource
    protected ExecutorFactory executorFactory;

    @Resource
    protected InstanceDataDAO instanceDataDAO;

    @Resource
    protected NodeInstanceDAO nodeInstanceDAO;

    @Resource
    protected ProcessInstanceDAO processInstanceDAO;

    @Resource
    protected NodeInstanceLogDAO nodeInstanceLogDAO;

    private static final IdGenerator ID_GENERATOR = new StrongUuidGenerator();
    @Resource
    protected FlowInstanceMappingDAO flowInstanceMappingDAO;

    private static final IdGenerator idGenerator = new StrongUuidGenerator();


    protected String genId() {
        return ID_GENERATOR.getNextId();
    }

    public abstract void execute(RuntimeContext runtimeContext) throws ProcessException;

    public abstract void commit(RuntimeContext runtimeContext) throws ProcessException;

    public abstract void rollback(RuntimeContext runtimeContext) throws ProcessException;

    protected abstract boolean isCompleted(RuntimeContext runtimeContext) throws ProcessException;

    protected boolean isSubFlowInstance(RuntimeContext runtimeContext) throws ProcessException {
        return runtimeContext.getParentRuntimeContext() != null;
    }

    protected abstract RuntimeExecutor getExecuteExecutor(RuntimeContext runtimeContext) throws ProcessException;

    protected abstract RuntimeExecutor getRollbackExecutor(RuntimeContext runtimeContext) throws ProcessException;
}
