package com.msb.apipaggenger.service;

import com.msb.apipaggenger.remote.ServicePassengerUserClient;
import com.msb.apipaggenger.remote.ServiceVerificationCodeClient;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.internalcommon.response.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    //验证码前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 生成验证码放入redis数据库，返回响应成功信息
     * @param passengerPhone 手机号
     * @return 响应状态
     */
    public ResponseResult generatorCode(String passengerPhone) {
        // 1.调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("调用serviceverification生成的验证码为：" + numberCode);


        // 2.存入redis,需要key，value，过期时间
        System.out.println("存入redis");
        String key = generateKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        //通过短信服务，将验证码发送到手机上，暂时不写
        return ResponseResult.success();
    }

    /**
     * 校验验证码放入redis数据库，返回响应成功信息
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return 响应状态
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        //根据手机号，去redis读取验证码:1生成key2根据key获取value
        System.out.println("根据手机号，去redis读取验证码");
        String key = generateKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);


        //校验验证码1.验证码过期redis中不存在这个验证码2.用户写的验证码和redis中的验证码不一致
        System.out.println("校验验证码");
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.equals(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }


        //判断原来是否有用户，做出相应的处理
        System.out.println("判断原来是否有用户，做出相应的处理");
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.getLoginOrRegister(verificationCodeDTO);


        //颁发令牌
        System.out.println("颁发令牌");

        //响应信息
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token");
        return ResponseResult.success(tokenResponse);
    }


    /**
     * 根据手机号生成key
     * @param passengerPhone 手机号
     * @return key
     */
    private String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

}
