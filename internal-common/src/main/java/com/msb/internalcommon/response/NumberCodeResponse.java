package com.msb.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: NumberCodeResponse
 * Package: com.msb.internalcommon.response
 * Description:用来接受生成的验证码numberCode
 *
 * @Author Emoaya
 * @Create 2024/3/25 16:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberCodeResponse {
    private int numberCode;

}
