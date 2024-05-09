package com.msb.servicedriveruser.remote;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceMapClient
 * Package: com.msb.servicedriveruser.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 20:07
 * @Version 1.0
 */

@FeignClient("service-map")
public interface ServiceMapClient {
//    @RestController
//    @RequestMapping("/terminal")
//    public class GaodeTrimianlController {
//
//        @Autowired
//        private GaodeTrimianlService gaodeTrimianlService;
//
//        @PostMapping("/add")
//        public ResponseResult add(String name){
//            return gaodeTrimianlService.add(name);
//        }
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/add")
    public ResponseResult<GaodeTerminalResponse> addTerminal(@RequestParam String name);


}
