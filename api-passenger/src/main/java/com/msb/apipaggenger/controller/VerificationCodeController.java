package com.msb.apipaggenger.controller;

import com.msb.apipaggenger.remote.ServiceVerificationCodeClient;
import com.msb.apipaggenger.request.VerificationCodeDTO;
import com.msb.apipaggenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: verificationCodeController
 * Package: com.msb.apipaggenger.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:46
 * @Version 1.0
 */
@Controller
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @RequestMapping("/verification-code")
    @ResponseBody
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        //接收参数
        System.out.println("接收到的手机号为" + verificationCodeDTO.getPassengerPhone());

        //返回参数：调用service层方法生成验证码并且返回响应成功的json格式的字符串
        return verificationCodeService.generatorCode(verificationCodeDTO.getPassengerPhone());
    }

}
