package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.servicedriveruser.controller.CarController;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.remote.ServiceMapClient;
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

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);

        //获得此车俩终端id：tid
        ResponseResult<GaodeTerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        //获得此车辆轨迹id：trid

        carMapper.insert(car);
        return ResponseResult.success();
    }


}
