package com.msb.serviceorder.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceDriverUserClient
 * Package: com.msb.serviceorder.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/6/23 20:28
 * @Version 1.0
 */

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/city-driver/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);

//    //根据车辆id查询订单需要的司机信息
//    @GetMapping("/get-available-driver/{carId}")
//    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
//        return driverUserService.getAvailableDriver(carId);
//    }
    @RequestMapping(method = RequestMethod.GET, value = "/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);

}
