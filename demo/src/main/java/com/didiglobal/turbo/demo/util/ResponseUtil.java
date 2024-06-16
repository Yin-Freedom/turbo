package com.didiglobal.turbo.demo.util;

import com.didiglobal.turbo.demo.pojo.BaseResponse;
import com.didiglobal.turbo.engine.common.ErrorEnum;
import org.springframework.stereotype.Component;

/**
 * @author freedom
 */
@Component
public class ResponseUtil {

    public <T> BaseResponse<T> buildSuccessResponse(T data) {
        BaseResponse<T> response = new BaseResponse<T>(ErrorEnum.SUCCESS);
        response.setData(data);
        return response;
    }
}
