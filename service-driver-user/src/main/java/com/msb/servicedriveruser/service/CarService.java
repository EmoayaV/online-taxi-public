package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.controller.CarController;
import com.msb.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * ClassName: CarService
 * Package: com.msb.servicedriveruser.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 21:45
 * @Version 1.0
 */

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success();
    }


}
