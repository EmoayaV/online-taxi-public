package com.msb.apipaggenger.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: ForecastPriceService
 * Package: com.msb.apipaggenger.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/28 16:01
 * @Version 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {


    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("调用计价服务");


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }



}
