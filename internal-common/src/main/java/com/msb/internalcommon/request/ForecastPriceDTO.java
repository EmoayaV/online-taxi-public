package com.msb.internalcommon.request;

import lombok.Data;

/**
 * ClassName: ForecastPriceDTO
 * Package: com.msb.internalcommon.request
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/28 15:55
 * @Version 1.0
 */
@Data
public class ForecastPriceDTO {
    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
}
