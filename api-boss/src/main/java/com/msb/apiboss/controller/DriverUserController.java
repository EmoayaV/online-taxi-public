package com.msb.apiboss.controller;

import com.msb.apiboss.service.CarService;
import com.msb.apiboss.service.DriverUserService;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: DriverUserController
 * Package: com.msb.apiboss.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 16:22
 * @Version 1.0
 */

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private CarService carService;

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    @PostMapping("/car")
    public ResponseResult car(@RequestBody Car car){
        return carService.addCar(car);
    }


}
