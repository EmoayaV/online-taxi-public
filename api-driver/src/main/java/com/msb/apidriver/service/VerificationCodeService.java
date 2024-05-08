package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverUserClient;
import com.msb.apidriver.remote.ServiceVerificationCodeClient;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.DriverCarConstants;
import com.msb.internalcommon.constant.IdentityConstant;
import com.msb.internalcommon.constant.TokenConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.DriverUserExistsResponse;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtils;
import com.msb.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        //查询service-driver-user ，该手机号是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        int isExists = data.getIsExists();
        if(isExists == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITS.getCode(), CommonStatusEnum.DRIVER_NOT_EXITS.getValue());
        }
        log.info(driverPhone+" 的司机存在");

        //获取验证码
        ResponseResult<NumberCodeResponse> verificationCodeResult = serviceVerificationCodeClient.getVerificationCode(6);
        NumberCodeResponse numberCodeResponse = verificationCodeResult.getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("验证码："+numberCode);

        //调用第三方发送验证码，先略过



        //存入redis
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 30, TimeUnit.DAYS);
        log.info("存入redis，key为："+key);

        return ResponseResult.success();
    }

    public ResponseResult checkCode(String driverPhone, String verificationCode) {
        //根据手机号，去redis读取验证码:1生成key2根据key获取value
        System.out.println("根据手机号，去redis读取验证码");
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:" + codeRedis);


        //校验验证码1.验证码过期redis中不存在这个验证码2.用户写的验证码和redis中的验证码不一致
        System.out.println("校验验证码");
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.equals(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }


        //颁发令牌，不应该将identity设置为字符串固定值，应该使用枚举类
        System.out.println("颁发令牌");
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        System.out.println("生成的accessToken为" + accessToken);
        System.out.println("生成的refreshToken为" + refreshToken);

        //将token存入redis,有效期30天
        System.out.println("将token存入redis");
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        //响应信息
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }

}
