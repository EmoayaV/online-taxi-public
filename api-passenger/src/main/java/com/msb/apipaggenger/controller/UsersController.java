package com.msb.apipaggenger.controller;

import com.msb.apipaggenger.service.UsersService;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: UserController
 * Package: com.msb.apipaggenger.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/23 20:14
 * @Version 1.0
 */

@Controller
public class UsersController {

    @Autowired
    public UsersService usersService;

    @RequestMapping("/users")
    @ResponseBody
    public ResponseResult getUser(HttpServletRequest request){
        //从http请求获取accessToken，
        //根据accessToken查询
        String accessToken = request.getHeader("Authorization");
        return usersService.getUserByAccessToken(accessToken);
    }

}
