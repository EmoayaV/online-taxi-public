package com.msb.internalcommon.response;

import lombok.Data;

/**
 * ClassName: ForecastPriceResponse
 * Package: com.msb.internalcommon.response
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/29 15:59
 * @Version 1.0
 */

@Data
public class ForecastPriceResponse {
    private double price;
    private String cityCode;
    private String vehicleType;
}
