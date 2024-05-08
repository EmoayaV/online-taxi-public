package com.msb.servicemap.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.servicemap.service.GaodeTrimianlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: GaodeTrimianlController
 * Package: com.msb.servicemap.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 16:34
 * @Version 1.0
 */

@RestController
@RequestMapping("/terminal")
public class GaodeTrimianlController {

    @Autowired
    private GaodeTrimianlService gaodeTrimianlService;

    @PostMapping("/add")
    public ResponseResult add(String name){
        return gaodeTrimianlService.add(name);
    }


}
