package com.msb.apidriver.controller;

import com.msb.apidriver.service.PointService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: PointController
 * Package: com.msb.apidriver.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 16:25
 * @Version 1.0
 */

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){
        return pointService.upload(apiDriverPointRequest);
    }


}
