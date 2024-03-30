package com.msb.apipaggenger.service;

import com.msb.apipaggenger.remote.ServiceVerificationCodeClient;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: VerificationCodeService
 * Package: com.msb.apipaggenger.service
 * Description:实现两个功能1.调用验证码服务，获取验证码
 * 2.存入redis
 * 3.返回对应的json格式的字符串，格式如下
 * {
 * "code": 1,
 * "message": "success"
 * }
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:57
 * @Version 1.0
 */
@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    //验证码前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generatorCode(String passengerPhone) {
        // 1.调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("调用serviceverification生成的验证码为：" + numberCode);


        // 2.存入redis,需要key，value，过期时间
        System.out.println("存入redis");
        String key = verificationCodePrefix + passengerPhone;
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        //通过短信服务，将验证码发送到手机上，
        return ResponseResult.success();
    }

}
