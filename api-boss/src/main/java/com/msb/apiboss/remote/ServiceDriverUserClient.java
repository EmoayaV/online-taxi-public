package com.msb.apiboss.remote;

import com.msb.apiboss.service.DriverCarBindingRelationshipService;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceDriverUserClient
 * Package: com.msb.apiboss.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 18:38
 * @Version 1.0
 */

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    //    @PostMapping ("/user")
    //    public ResponseResult addUser(@RequestBody DriverUser driverUser){
    //        return driverUserService.addDriverUser(driverUser);
    //    }
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    //    @PutMapping("/user")
    //    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
    //        log.info(JSONObject.fromObject(driverUser).toString());
    //        return driverUserService.updateDriverUser(driverUser);
    //    }
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);


    //    @PostMapping("/car")
    //    public ResponseResult addCar(@RequestBody Car car){
    //        return carService.addCar(car);
    //    }

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    public ResponseResult addCar(@RequestBody Car car);


//    @RestController
//    @RequestMapping("c")
//    public class DriverCarBindingRelationshipController {
//
//        @Autowired
//        private DriverCarBindingRelationshipService driverCarBindingRelationshipService;
//
//        @PostMapping("/bind")
//        public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
//            return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
//        }
//
//        @PostMapping("/unbind")
//        public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
//            return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
//        }

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);



}
