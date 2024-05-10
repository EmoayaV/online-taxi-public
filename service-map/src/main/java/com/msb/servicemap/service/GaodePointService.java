package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.PointRequest;
import com.msb.servicemap.remote.GaodePointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: GaodePointService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 14:47
 * @Version 1.0
 */

@Service
public class GaodePointService {

    @Autowired
    private GaodePointClient gaodePointClient;

    public ResponseResult upload(PointRequest pointRequest){
        return gaodePointClient.upload(pointRequest);
    }

}
