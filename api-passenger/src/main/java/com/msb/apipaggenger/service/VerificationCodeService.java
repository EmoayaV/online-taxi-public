package com.msb.apipaggenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * ClassName: VerificationCodeService
 * Package: com.msb.apipaggenger.service
 * Description:实现两个功能1.调用验证码服务，获取验证码
 *                       2.存入redis
 *                       3.返回对应的json格式的字符串，格式如下
 *                       {
 *                           "code": 1,
 *                            "message": "success"
 *                       }
 *
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:57
 * @Version 1.0
 */
@Service
public class VerificationCodeService {
    public String generatorCode(String passengerPhone){
        // 1.调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        // 2.存入redis
        System.out.println("存入redis");

        // 3.返回对应的json格式的字符串
        System.out.println("返回对应的json格式的字符串");
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }

}
