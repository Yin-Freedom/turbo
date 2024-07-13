package com.freedom.backend.demo.util;

import com.freedom.backend.demo.pojo.BaseResponse;
import com.freedom.backend.engine.common.ErrorEnum;
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
