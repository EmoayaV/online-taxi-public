package com.msb.apipaggenger.service;

import com.msb.apipaggenger.remote.ServicePriceCilent;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ServicePriceCilent servicePriceCilent;

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("调用计价服务");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<ForecastPriceResponse> forecast = servicePriceCilent.forecast(forecastPriceDTO);
        double price = forecast.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }



}
