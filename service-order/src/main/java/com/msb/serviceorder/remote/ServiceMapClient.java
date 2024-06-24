package com.msb.serviceorder.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: ServiceMapClient
 * Package: com.msb.serviceorder.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/6/24 14:04
 * @Version 1.0
 */

@FeignClient("service-map")
public interface ServiceMapClient {

    //@RestController
    //@RequestMapping("/terminal")
    //public class GaodeTrimianlController {

    //    @PostMapping("/aroundsearch")
    //    public ResponseResult<List<GaodeTerminalResponse>> aroundsearch(String center, Integer radius){
    //        return gaodeTrimianlService.aroundsearch(center,radius);
    //    }

    @RequestMapping(method = RequestMethod.POST, value = "/terminal/aroundsearch")
    public ResponseResult<List<GaodeTerminalResponse>> aroundsearch(@RequestParam String center, @RequestParam Integer radius);

}
