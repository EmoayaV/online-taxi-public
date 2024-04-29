package com.msb.apipaggenger.service;

import com.msb.apipaggenger.remote.ServicePassengerUserClient;
import com.msb.internalcommon.dto.PassengerUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UsersService
 * Package: com.msb.apipaggenger.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/23 20:17
 * @Version 1.0
 */
@Service
@Slf4j
public class UsersService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){
        //解析accesstoken拿到手机号
        log.info("accessToken:"+accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号:"+phone);

        //根据手机号查询用户信息
        ResponseResult userByPhone = servicePassengerUserClient.getUser(phone);



        //响应用户信息
        return ResponseResult.success(userByPhone.getData());
    }



}
