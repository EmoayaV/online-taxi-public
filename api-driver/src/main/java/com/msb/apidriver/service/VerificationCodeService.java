package com.msb.apidriver.service;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * ClassName: VerificationCodeService
 * Package: com.msb.apidriver.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 14:49
 * @Version 1.0
 */

@Service
public class VerificationCodeService {
    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        //查询service-driver-user ，该手机号是否存在

        //获取验证码

        //调用第三方发送验证码

        //存入redis

        return ResponseResult.success();
    }

}
