package com.msb.serviceprice.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: ForecastPriceService
 * Package: com.msb.serviceprice.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/29 16:38
 * @Version 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("调用地图服务，查询距离和时长");

        log.info("读取计价规则");

        log.info("根据距离、时常和计价规则计算价格");


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }

}
