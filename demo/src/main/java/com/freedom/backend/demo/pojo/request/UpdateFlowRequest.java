package com.freedom.backend.demo.pojo.request;

import com.freedom.backend.demo.util.Constant;
import com.freedom.backend.engine.param.UpdateFlowParam;

/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description:
 */
public class UpdateFlowRequest extends UpdateFlowParam {

    public UpdateFlowRequest() {
        super(Constant.tenant, Constant.caller);
    }
}
