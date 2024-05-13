package com.msb.serviceorder.service;

import com.msb.internalcommon.constant.OrderConstants;
import com.msb.internalcommon.dto.OrderInfo;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

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

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        OrderInfo orderInfo = new OrderInfo();

        //将orderRequest的属性传入orderInfo
        BeanUtils.copyProperties(orderRequest, orderInfo);

        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);


        orderInfoMapper.insert(orderInfo);

        return ResponseResult.success();
    }


}
