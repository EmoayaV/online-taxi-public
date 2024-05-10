package com.msb.internalcommon.request;

import lombok.Data;

/**
 * ClassName: ApiDriverPointRequest
 * Package: com.msb.internalcommon.request
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 16:29
 * @Version 1.0
 */

@Data
public class ApiDriverPointRequest {

    public Long carId;
    public PointDTO[] points;



}
