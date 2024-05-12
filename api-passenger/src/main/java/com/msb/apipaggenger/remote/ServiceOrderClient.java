package com.msb.apipaggenger.remote;

import com.msb.apipaggenger.service.OrderService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ServiceOrderClient
 * Package: com.msb.apipaggenger.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/10 17:46
 * @Version 1.0
 */

@FeignClient("service-order")
public interface ServiceOrderClient {

//    @RestController
//    @RequestMapping("/order")
//    @Slf4j
//    public class OrderController {
//
//        @Autowired
//        private OrderService orderService;
//
//        @PostMapping("/add")
//        public ResponseResult add(@RequestBody OrderRequest orderRequest){
//            log.info("service-order:"+orderRequest.getAddress());
//            return orderService.add(orderRequest);
//        }

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);



}
