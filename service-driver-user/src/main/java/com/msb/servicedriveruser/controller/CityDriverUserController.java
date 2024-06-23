package com.msb.servicedriveruser.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CityDriverUserController
 * Package: com.msb.servicedriveruser.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/6/23 16:36
 * @Version 1.0
 */

@RestController
@RequestMapping("/city-driver")
public class CityDriverUserController {

    @Autowired
    CityDriverUserService cityDriverUserService;

    @GetMapping("/is-available-driver")
    public ResponseResult isAvailableDriver(String cityCode){
        return cityDriverUserService.isAvailableDriver(cityCode);
    }

}
