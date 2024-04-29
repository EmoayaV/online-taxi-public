package com.msb.apipaggenger.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.IdentityConstant;
import com.msb.internalcommon.constant.TokenConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtils;
import com.msb.internalcommon.util.RedisPrefixUtils;
import feign.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: TokenService
 * Package: com.msb.apipaggenger.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/12 15:46
 * @Version 1.0
 */
@Service
public class TokenService {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String tokenRefreshSrc){
        //解析refreshToken
        System.out.println("解析refreshToken");
        TokenResult tokenResult = JwtUtils.checkToken(tokenRefreshSrc);
        if(tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        //读取redis中的refreshToken
        System.out.println("读取redis中的refreshToken");
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity, TokenConstant.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);


        //校验refreshToken
        System.out.println("校验refreshToken");
        if((StringUtils.isBlank(refreshTokenRedis)) || (!tokenRefreshSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }


        //创建双token
        System.out.println("创建双token");
        System.out.println("颁发令牌");
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstant.REFRESH_TOKEN_TYPE);
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
        System.out.println("生成的accessTokenKey为" + accessTokenKey);
        System.out.println("生成的refreshTokenKey为" + refreshTokenKey);
        System.out.println("生成的accessToken为" + accessToken);
        System.out.println("生成的refreshToken为" + refreshToken);


        //将token存入redis,
        System.out.println("将token存入redis");
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }


}
