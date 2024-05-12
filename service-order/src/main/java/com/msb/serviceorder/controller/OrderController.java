package com.msb.serviceorder.controller;


import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import com.msb.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author emoaya
 * @since 2024-05-12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info("service-order："+orderRequest.getAddress());
        return null;
    }

}
