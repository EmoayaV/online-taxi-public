package com.msb.serviceorder.remote;

import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServicePriceClient
 * Package: com.msb.serviceorder.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/20 20:27
 * @Version 1.0
 */

@FeignClient("service-price")
public interface ServicePriceClient {

    //    @GetMapping("/if-exists")
    //    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
    //        return priceRuleService.ifExists(priceRule);
    //    }
    @RequestMapping(method = RequestMethod.GET, value = "/price-rule/if-exists")
    public ResponseResult<Boolean> ifPriceExists(@RequestBody PriceRule priceRule);
}
