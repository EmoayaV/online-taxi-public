package com.msb.servicepassengeruser.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.PassengerUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicepassengeruser.mapper.PassengerUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone) {
        //根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);//相当于sql语句SELECT * FROM 表名 WHERE 列名1= ?
        log.info(passengerUsers.toString());

        //判断用户信息是否存在
        System.out.println("判断用户信息是否存在");
        //不存在就新建一条数据
        if (passengerUsers.size() == 0) {
            System.out.println("插入数据");
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone("18997925277");
            passengerUser.setState((byte) 0);
            passengerUser.setGmtModified(LocalDateTime.now());
            passengerUser.setGmtCreate(LocalDateTime.now());
            passengerUserMapper.insert(passengerUser);
        }

        return ResponseResult.success();
    }

    public ResponseResult getUserByPhone(String passengerPhone) {
        //根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);//相当于sql语句SELECT * FROM 表名 WHERE 列名1= ?
        log.info(passengerUsers.toString());

        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(), CommonStatusEnum.USER_NOT_EXISTS.getValue());
        } else{
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }

}
