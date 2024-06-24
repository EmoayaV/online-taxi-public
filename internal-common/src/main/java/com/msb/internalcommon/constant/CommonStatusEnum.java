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
    PRICE_RULE_EXISTS(1301,"计价规则存在"),
    PRICE_RULE_NOT_EDIT(1302,"计价规则没有变化"),
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),
    DRIVER_CAR_BIND_NOT_EXITS(1500,"司机和车辆绑定关系不存在"),
    DRIVER_NOT_EXITS(1501,"司机不存在"),
    DRIVER_CAR_BIND_EXITS(1502,"司机车辆绑定关系已经存在，请勿重复绑定"),
    DRIVER_BIND_EXISTS(1503,"司机已经被绑定，请勿重复绑定"),
    CAR_BIND_EXISTS(1504,"车辆已经被绑定，请勿重复绑定"),
    CITY_DRIVER_EMPTY(1505,"当前城市没有可用的司机"),
    ORDER_GOING_ON(1600,"有正在进行的订单"),
    DEVICE_IS_BLACK(1601,"该设备超过下单次数"),
    CITY_SERVICE_NOT_SERVICE(1602,"当前城市不提供叫车服务"),
    SUCCESS(1, "success"),
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String value;


}
