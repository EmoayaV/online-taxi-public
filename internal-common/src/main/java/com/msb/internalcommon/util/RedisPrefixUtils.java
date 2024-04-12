package com.msb.internalcommon.util;

/**
 * ClassName: RedisPrefixUtils
 * Package: com.msb.internalcommon.util
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/31 21:27
 * @Version 1.0
 */
public class RedisPrefixUtils {

    //验证码前缀
    private static String verificationCodePrefix = "passenger-verification-code-";
    private static String tokenPrefix = "token-";

    /**
     * 根据手机号生成key
     *
     * @param passengerPhone 手机号
     * @return key
     */
    public static String generateKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份表示生成token的key
     * @param phone
     * @param identity
     * @return
     */
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity+"-" + tokenType;
    }
}
