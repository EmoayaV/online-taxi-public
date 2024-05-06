package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;

/**
 * ClassName: UserService
 * Package: com.msb.apidriver.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 20:24
 * @Version 1.0
 */

@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult updateUser(DriverUser driverUser){
        return serviceDriverUserClient.updateUser(driverUser);
    }

}
