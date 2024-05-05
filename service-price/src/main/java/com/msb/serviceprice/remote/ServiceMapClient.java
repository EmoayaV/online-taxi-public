package com.msb.serviceprice.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceMapClient
 * Package: com.msb.serviceprice.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/4 16:36
 * @Version 1.0
 */

//@RestController
//@RequestMapping("/direction")
//public class DirectionController {
//
//    @Autowired
//    DirectionService directionService;
//
//    @GetMapping("/driving")
//    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){
//
//
//    }

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
