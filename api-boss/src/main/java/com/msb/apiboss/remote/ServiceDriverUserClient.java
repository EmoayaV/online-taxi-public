package com.msb.apiboss.remote;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

}
