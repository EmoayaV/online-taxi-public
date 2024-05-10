package com.msb.internalcommon.request;

import lombok.Data;

/**
 * ClassName: PointRequest
 * Package: com.msb.internalcommon.request
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 14:50
 * @Version 1.0
 */

@Data
public class PointRequest {

    public String tid;
    public String trid;
    public PointDTO[] points;

}
