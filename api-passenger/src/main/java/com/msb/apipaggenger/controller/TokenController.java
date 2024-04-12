package com.msb.apipaggenger.controller;

import com.msb.apipaggenger.service.TokenService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TokenController
 * Package: com.msb.apipaggenger.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/12 15:43
 * @Version 1.0
 */

@Controller
public class TokenController {

    @Autowired
    public TokenService tokenService;

    @RequestMapping("/token-refresh")
    @ResponseBody
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
        //获取refreshTokenSrc
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken为：" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }

}
