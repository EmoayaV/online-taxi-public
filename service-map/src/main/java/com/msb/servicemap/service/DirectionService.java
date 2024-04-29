package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DirectionResponse;
import org.springframework.stereotype.Service;

/**
 * ClassName: DirectionService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/29 19:12
 * @Version 1.0
 */
@Service

public class DirectionService {

    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);
        return ResponseResult.success(directionResponse);
    }

}
