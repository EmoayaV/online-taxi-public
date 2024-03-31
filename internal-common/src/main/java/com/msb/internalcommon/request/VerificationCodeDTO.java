package com.msb.internalcommon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: VerificationCodeDTO
 * Package: com.msb.apipaggenger.request
 * Description:用于接受前端返回的json格式的手机号，如
 *              {
 *                  "passengerPhone":"18997925277"
 *              }
 *              注意passengerPhone必须与下面DTO（数据传输对象）类的属性对应
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:41
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationCodeDTO {
    private String passengerPhone;
    private String verificationCode;
}
