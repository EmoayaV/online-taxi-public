package com.msb.apipaggenger.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    private static final String JWT_KEY = "passengerPhone";

    //生成token
    public static String generatorToken(String passengerPhone) {
        //使用passengerPhone生成Token
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY, passengerPhone);

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
        //整合过期时间
        builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));


        return sign;
    }


    //解析token
    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }


    public static void main(String[] args) {
        String token = generatorToken("18997925277");
        System.out.println("生成的token" + token);
        System.out.println("解析token后的值" + parseToken(token));
    }

}
