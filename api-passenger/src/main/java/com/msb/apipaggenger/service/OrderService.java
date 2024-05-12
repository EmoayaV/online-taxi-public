package com.msb.apipaggenger.service;

import com.msb.apipaggenger.remote.ServiceOrderClient;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: OrderService
 * Package: com.msb.apipaggenger.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/10 16:01
 * @Version 1.0
 */

@Service
public class OrderService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }




}
