package com.msb.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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

    //时间
    private static final String JWT_TOKEN_TIME = "tokenTime";


    //生成token
    public static String generatorToken(String passengerPhone, String identity, String tokenType) {
        //使用passengerPhone生成Token
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());//防止每次生成的key一样从而导致生成的token一样


        //整合map
        JWTCreator.Builder builder = JWT.create();
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

    //校验token，主要判断token是否异常,如果有异常返回null
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            //解析token
            tokenResult = JwtUtils.parseToken(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tokenResult;
    }


    public static void main(String[] args) {
        String token = generatorToken("18997925277","1", "accseeToken");
        System.out.println("生成的token" + token);
        System.out.println("解析后的token" + parseToken(token));
    }

}
