package com.msb.apipaggenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServicePriceCilent
 * Package: com.msb.apipaggenger.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 16:00
 * @Version 1.0
 */

//@RestController
//public class ForecastPriceController {
//
//    @Autowired
//    private ForecastPriceService forecastPriceService;
//
//
//    @PostMapping("/forecast-price")
//    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

@FeignClient("service-price")
public interface ServicePriceCilent {

    @RequestMapping (method = RequestMethod.POST, value = "/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
