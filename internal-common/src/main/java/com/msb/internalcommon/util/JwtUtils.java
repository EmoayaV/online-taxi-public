package com.msb.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.msb.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.JWT.require;

/**
 * ClassName: JwtUtils
 * Package: com.msb.apipaggenger.util
 * Description: 这个类的作用就是生成token，并且token  1天过期
 *
 * @Author Emoaya
 * @Create 2024/3/31 15:59
 * @Version 1.0
 */
public class JwtUtils {
    //盐:乱写
    private static final String SIGN = "&yuigsg*&yas";

    //乘客或者司机的手机号
    private static final String JWT_KEY_PHONE = "phone";

    //乘客是1，司机是2
    private static final String JWT_KEY_IDENTITY = "identity";

    //TOKEN类型
    private static final String JWT_TOKEN_TYPE = "tokenType";


    //生成token
    public static String generatorToken(String passengerPhone, String identity, String tokenType) {
        //使用passengerPhone生成Token
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);

        //token过期时间1天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );
        //整合过期时间，注释----》由redis控制
//        builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));


        return sign;
    }


    //解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(phone);
        return tokenResult;
    }


    public static void main(String[] args) {
        String token = generatorToken("18997925277","1", "accseeToken");
        System.out.println("生成的token" + token);
        System.out.println("解析后的token" + parseToken(token));
    }

}
