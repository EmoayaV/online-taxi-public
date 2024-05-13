package com.msb.internalcommon.dto;

import lombok.Data;

/**
 * ClassName: PriceRule
 * Package: com.msb.internalcommon.dto
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 14:24
 * @Version 1.0
 */

@Data
public class PriceRule {
    private String cityCode;
    private String vehicleType;
    private Double startFare;
    private Integer startMile;
    private Double unitPricePerMile;
    private Double unitPricePerMinute;
    private String fareType;
    private Integer fareVersion;
}
