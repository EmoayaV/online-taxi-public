package com.msb.servicemap.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicemap.service.GaodeServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: GaodeServiceController
 * Package: com.msb.servicemap.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 15:49
 * @Version 1.0
 */

@RestController
@RequestMapping("/service")
public class GaodeServiceController {

    @Autowired
    private GaodeServiceMapService gaodeServiceMapService;

    //创建服务
    @PostMapping("/add")
    public ResponseResult add(String name){
        return gaodeServiceMapService.add(name);
    }




}
