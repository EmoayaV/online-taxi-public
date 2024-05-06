package com.msb.servicedriveruser.controller;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.msb.servicedriveruser.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 15:10
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping ("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }


}
