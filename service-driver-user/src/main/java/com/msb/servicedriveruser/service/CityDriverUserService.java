package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CityDriverUserService {

    @Autowired
    DriverUserMapper driverUserMapper;

    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        log.info("citycode:"+cityCode);
        Long i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if(i == null){
            return ResponseResult.success(false);
        }
        if(i>0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }
}
