package com.msb.servicedriveruser.controller;

import com.msb.internalcommon.constant.DriverCarConstants;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DriverUserExistsResponse;
import com.msb.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.msb.servicedriveruser.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 15:10
 * @Version 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    //新增司机
    @PostMapping ("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.addDriverUser(driverUser);
    }

    //修改司机
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    //根据条件查询司机
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDB = driverUserByPhone.getData();

        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
        int isExists = DriverCarConstants.DRIVER_EXISTS;
        if(driverUserDB== null){
            isExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            driverUserExistsResponse.setIsExists(isExists);
            driverUserExistsResponse.setDriverPhone(driverPhone);
        }else{
            driverUserExistsResponse.setIsExists(isExists);
            driverUserExistsResponse.setDriverPhone(driverUserDB.getDriverPhone());
        }

        return ResponseResult.success(driverUserExistsResponse);
    }

}
