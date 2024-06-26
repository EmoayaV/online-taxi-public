package com.msb.servicepassengeruser.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.msb.servicepassengeruser.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/30 19:03
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //接受数据
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("乘客手机号为" + passengerPhone);

        //返回参数
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    @ResponseBody
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){
        //接受数据
        return userService.getUserByPhone(passengerPhone);
    }

}













