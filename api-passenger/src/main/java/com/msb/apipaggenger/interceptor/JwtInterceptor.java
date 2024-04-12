package com.msb.apipaggenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.msb.internalcommon.constant.TokenConstant;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtils;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.util.RedisPrefixUtils;
import io.netty.util.internal.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * ClassName: JwtInterceptor
 * Package: com.msb.apipaggenger.interceptor
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/31 20:27
 * @Version 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //在请求到达我们定义的handler之前工作的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";
        TokenResult tokenResult = null;

        //获取token
        String token = request.getHeader("Authorization");

        try {
            //解析token
            tokenResult = JwtUtils.parseToken(token);

        }catch (SignatureVerificationException e){
            resultString = "token sign error";
            result = false;
        }catch(TokenExpiredException e){
            resultString = "token time out";
            result = false;
        }catch(AlgorithmMismatchException e){
            resultString = "token AlgorithmMismatchException";
            result = false;
        }catch (Exception e){
            resultString = "token invalid";
            result = false;
        }


        //从redis中取出token
        if(tokenResult == null){
            resultString = "token invalid";
            result = false;
        }else{
            //利用token中携带的phone和identity获取key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
            //从redis 中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            //token过期
            if(StringUtils.isBlank(tokenRedis)){
                resultString = "token invalid";
                result = false;
            }else{
                //没过期，但是传进来错误的token
                if(!token.trim().equals(tokenRedis.trim())){
                    resultString = "token invalid";
                    result = false;
                }
            }
        }

        if (!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
