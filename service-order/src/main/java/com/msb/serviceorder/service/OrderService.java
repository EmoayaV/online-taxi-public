package com.msb.serviceorder.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: OrderService
 * Package: com.msb.serviceorder.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/10 17:33
 * @Version 1.0
 */

@Service
public class OrderService {

    public ResponseResult add(@RequestBody OrderRequest orderRequest){

        return null;
    }


}
