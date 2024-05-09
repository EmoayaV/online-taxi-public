package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTraceResponse;
import com.msb.servicemap.remote.GaodeTraceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: GaodeTraceService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 21:47
 * @Version 1.0
 */

@Service
public class GaodeTraceService {

    @Autowired
    private GaodeTraceClient gaodeTraceClient;

    public ResponseResult<GaodeTraceResponse> add(String tid){

        return gaodeTraceClient.add(tid);
    }

}
