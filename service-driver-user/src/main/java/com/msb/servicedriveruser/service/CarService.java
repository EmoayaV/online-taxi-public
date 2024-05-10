package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.internalcommon.response.GaodeTraceResponse;
import com.msb.servicedriveruser.controller.CarController;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);

        //保存车辆
        carMapper.insert(car);

        //创建终端，获得此车俩终端id：tid
        String desc = car.getId()+"";
        log.info(desc);
        ResponseResult<GaodeTerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo(), desc);
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        //创建轨迹，获得此车辆轨迹id：trid
        ResponseResult<GaodeTraceResponse> gaodeTraceResponseResponseResult = serviceMapClient.addTrace(tid);
        String trid = gaodeTraceResponseResponseResult.getData().getTrid();
        String trname = gaodeTraceResponseResponseResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        carMapper.updateById(car);


        return ResponseResult.success();
    }

    public ResponseResult<Car> getCarById(Long id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Car> cars = carMapper.selectByMap(map);
        return ResponseResult.success(cars.get(0));
    }

}
