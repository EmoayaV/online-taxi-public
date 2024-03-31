package com.msb.apipaggenger.controller;

import com.msb.apipaggenger.service.VerificationCodeService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        //接收参数
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号为" + passengerPhone);

        //返回参数：调用service层方法生成验证码并且返回响应成功的json格式的字符串
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    @ResponseBody
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //接受参数
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("接收到的手机号为" + passengerPhone);
        System.out.println("接收到的验证码为" + verificationCode);

        //返回参数
        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }

}
