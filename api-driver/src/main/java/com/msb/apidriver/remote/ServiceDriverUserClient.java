package com.msb.apidriver.remote;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceDriverUserClient
 * Package: com.msb.apidriver.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 20:26
 * @Version 1.0
 */

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {


    //    //修改司机
    //    @PutMapping("/user")
    //    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);

    //    //根据条件查询司机
    //    @GetMapping("/check-driver/{driverPhone}")
    //    public ResponseResult getUser(@PathVariable("driverPhone") String driverPhone){
    @RequestMapping(method = RequestMethod.GET, value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);


}
