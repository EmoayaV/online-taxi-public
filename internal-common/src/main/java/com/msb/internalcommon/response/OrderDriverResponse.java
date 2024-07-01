package com.msb.internalcommon.response;

import lombok.Data;

/**
 * ClassName: OrderDriverResponse
 * Package: com.msb.internalcommon.response
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/6/24 20:04
 * @Version 1.0
 */

@Data
public class OrderDriverResponse {

    private Long driverId;
    private String driverPhone;
    private Long carId;
    private String licenseId;
    private String vehicleNo;


}
