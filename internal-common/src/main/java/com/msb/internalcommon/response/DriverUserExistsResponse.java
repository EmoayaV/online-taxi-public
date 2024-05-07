package com.msb.internalcommon.response;

import lombok.Data;

/**
 * ClassName: DriverUserExistsResponse
 * Package: com.msb.internalcommon.response
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 15:28
 * @Version 1.0
 */
@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private int isExists;

}
