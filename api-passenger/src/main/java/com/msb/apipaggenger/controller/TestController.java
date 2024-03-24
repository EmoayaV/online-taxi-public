package com.msb.apipaggenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TestController
 * Package: com.msb.apipaggenger.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/24 15:19
 * @Version 1.0
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test api-passenger";
    }
}
