package com.msb.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: TokenResponse
 * Package: com.msb.internalcommon.response
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/30 17:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
