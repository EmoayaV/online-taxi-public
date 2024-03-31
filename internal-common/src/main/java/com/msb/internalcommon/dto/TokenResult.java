package com.msb.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: TokenResult
 * Package: com.msb.internalcommon.dto
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/31 16:54
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResult {
    private String phone;
    private String identity;
}
