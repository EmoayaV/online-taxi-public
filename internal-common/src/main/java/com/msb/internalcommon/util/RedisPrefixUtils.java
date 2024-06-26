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
    private static String verificationCodePrefix = "verification-code-";
    private static String tokenPrefix = "token-";
    public static String blackDeviceCodePrefix = "black-device-";

    /**
     * 根据手机号生成key
     *
     * @param phone    手机号
     * @param identity 手机号
     * @return key
     */
    public static String generateKeyByPhone(String phone, String identity) {
        return verificationCodePrefix + identity + "-" + phone;
    }

    /**
     * 根据手机号和身份表示生成token的key
     *
     * @param phone
     * @param identity
     * @return
     */
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
