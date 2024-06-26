package com.msb.apidriver.interceptor;

import com.msb.internalcommon.constant.TokenConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtils;
import com.msb.internalcommon.util.RedisPrefixUtils;
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


        //解析+校验token，校验失败，则tokenResult = null
        tokenResult = JwtUtils.checkToken(token);
        System.out.println( "tokenResult为:"+tokenResult);


        //从redis中取出token
        if(tokenResult == null){
            resultString = "access token invalid";
            result = false;
        }else{
            //利用token中携带的phone和identity获取key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
            //从redis 中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            //token过期或者不等
            if((StringUtils.isBlank(tokenRedis)) || (!token.trim().equals(tokenRedis.trim()))){
                resultString = "access token invalid";
                result = false;
            }
        }

        if (!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
