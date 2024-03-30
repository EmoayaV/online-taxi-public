package com.msb.serviceverificationcode.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.serviceverificationcode.service.NumberCodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * ClassName: NumberCodeController
 * Package: com.msb.serviceverificationcode.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/25 15:28
 * @Version 1.0
 */
@Controller
public class NumberCodeController {
    @Autowired
    private NumberCodeService numberCodeService;
    @ResponseBody
    @RequestMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {
        //接收参数
        System.out.println("size:" + size);

        //用NumberCodeResponse对象接受生成的验证码
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(numberCodeService.getRandom(size));
        System.out.println("serviceverification生成的验证码为：" + response.getNumberCode());

        //返回参数，会自动把ResponseResult对象转为json格式的字符串
        return ResponseResult.success(response);
    }
}
