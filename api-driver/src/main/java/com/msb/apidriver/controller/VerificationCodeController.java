package com.msb.apidriver.controller;

import com.msb.apidriver.service.VerificationCodeService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: VerificationCodeController
 * Package: com.msb.apidriver.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 14:46
 * @Version 1.0
 */

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码："+driverPhone);
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //接受参数
        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("接收到的手机号为" + driverPhone);
        System.out.println("接收到的验证码为" + verificationCode);

        //返回参数
        return verificationCodeService.checkCode(driverPhone, verificationCode);
    }


}
