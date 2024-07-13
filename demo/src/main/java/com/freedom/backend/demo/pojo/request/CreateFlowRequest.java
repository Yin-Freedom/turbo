package com.freedom.backend.demo.pojo.request;

import com.freedom.backend.demo.util.Constant;
import com.freedom.backend.engine.param.CreateFlowParam;

/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description:
 */
public class CreateFlowRequest extends CreateFlowParam {

    public CreateFlowRequest() {
        super(Constant.tenant, Constant.caller);
    }

}
