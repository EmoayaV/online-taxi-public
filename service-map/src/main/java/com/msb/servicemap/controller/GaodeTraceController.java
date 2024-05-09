package com.msb.servicemap.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTraceResponse;
import com.msb.servicemap.service.GaodeTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: GaodeTrackController
 * Package: com.msb.servicemap.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 21:32
 * @Version 1.0
 */

@RestController
@RequestMapping("/trace")
public class GaodeTraceController {

    @Autowired
    private GaodeTraceService gaodeTraceService;

    @PostMapping("/add")
    public ResponseResult<GaodeTraceResponse> add(String tid){

        return gaodeTraceService.add(tid);
    }


}
