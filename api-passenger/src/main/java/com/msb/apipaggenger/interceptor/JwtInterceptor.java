package com.msb.apipaggenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.msb.apipaggenger.util.JwtUtils;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

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
    //在请求到达我们定义的handler之前工作的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        //获取token
        String token = request.getHeader("Authorization");

        try {
            //解析token
            JwtUtils.parseToken(token);

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
        if (!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }


        return result;
    }
}
