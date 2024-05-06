package com.msb.internalcommon.dto;

import lombok.Data;

/**
 * ClassName: DicDistrict
 * Package: com.msb.internalcommon.dto
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 19:17
 * @Version 1.0
 */

@Data
public class DicDistrict {
    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private Integer level;
}
