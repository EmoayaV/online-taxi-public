package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CityDriverUserService
 * Package: com.msb.servicedriveruser.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/6/23 16:31
 * @Version 1.0
 */

@Service
public class CityDriverUserService {

    @Autowired
    DriverUserMapper driverUserMapper;

    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        long i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if(i>0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }
}
