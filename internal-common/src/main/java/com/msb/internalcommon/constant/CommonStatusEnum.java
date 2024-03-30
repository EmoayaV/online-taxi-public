package com.msb.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * ClassName: CommonStatusEnum
 * Package: com.msb.internalcommon.constant
 * Description:用来存放响应状态的类型
 *
 * @Author Emoaya
 * @Create 2024/3/25 16:19
 * @Version 1.0
 */


//枚举类不能有set方法，所以不能用@Data注解，使用@Getter注解只生成get方法
@AllArgsConstructor
public enum CommonStatusEnum {

    SUCCESS(1, "success"),
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String value;


}
