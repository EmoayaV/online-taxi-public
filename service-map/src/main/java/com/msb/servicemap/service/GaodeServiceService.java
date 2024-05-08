package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicemap.remote.GaodeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: GaodeServiceMapService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 15:52
 * @Version 1.0
 */

@Service
public class GaodeServiceService {

    @Autowired
    private GaodeServiceClient gaodeServiceClient;

    //创建服务
    public ResponseResult add(String name){
        return gaodeServiceClient.add(name);
    }


}
