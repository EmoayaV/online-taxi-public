package com.msb.internalcommon.dto;

import com.msb.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName: ResponseResult
 * Package: com.msb.internalcommon.dto
 * Description:用来传递响应消息的对象
 *
 * @Author Emoaya
 * @Create 2024/3/25 16:27
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    //成功响应的方法
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    //失败的方法
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);
    }

    public static <T> ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    public static <T> ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }

}
