package com.msb.apiboss.service;

import com.msb.apiboss.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CarService
 * Package: com.msb.apiboss.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 13:15
 * @Version 1.0
 */

@Service
public class CarService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }


}
