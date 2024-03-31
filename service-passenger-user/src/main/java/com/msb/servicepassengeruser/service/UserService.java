package com.msb.servicepassengeruser.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.servicepassengeruser.dto.PassengerUser;
import com.msb.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: UserService
 * Package: com.msb.servicepassengeruser.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/30 19:39
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone) {
        //根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);//相当于sql语句SELECT * FROM 表名 WHERE 列名1= ?
        System.out.println("查询到的记录为:" + passengerUsers.get(0).getPassengerPhone());


        //判断用户信息是否存在
        System.out.println("判断用户信息是否存在");

        //如果不存在，插入用户信息
        System.out.println("判断用户信息是否存在");

        return ResponseResult.success();
    }
}
