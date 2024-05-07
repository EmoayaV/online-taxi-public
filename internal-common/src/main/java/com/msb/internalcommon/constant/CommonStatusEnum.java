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
//验证码错误提示1000-1099
@AllArgsConstructor
public enum CommonStatusEnum {

    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    TOKEN_ERROR(1199,"Token错误"),
    USER_NOT_EXISTS(1200,"当前用户不存在"),
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),
    DRIVER_CAR_BIND_NOT_EXITS(1500,"司机和车辆绑定关系不存在"),
    DRIVER_NOT_EXITS(1501,"司机不存在"),
    DRIVER_CAR_BIND_EXITS(1502,"司机车辆绑定关系已经存在，请勿重复绑定"),
    DRIVER_BIND_EXISTS(1503,"司机已经被绑定，请勿重复绑定"),
    CAR_BIND_EXISTS(1504,"车辆已经被绑定，请勿重复绑定"),
    SUCCESS(1, "success"),
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String value;


}
