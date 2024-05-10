package com.msb.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: OrderRequest
 * Package: com.msb.internalcommon.request
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/10 15:48
 * @Version 1.0
 */

@Data
public class OrderRequest {
    public String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime departTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime orderTime;
    public String departure;
    public String depLongitude;
    public String depLatitude;
    public String destination;
    public String destLongitude;
    public String destLatitude;
    public Integer encrypt;
    public String fareType;
}
