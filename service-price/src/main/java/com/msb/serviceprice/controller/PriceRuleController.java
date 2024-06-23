package com.msb.serviceprice.controller;

import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: PriceRuleController
 * Package: com.msb.serviceprice.controller
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/13 16:37
 * @Version 1.0
 */

@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){
        return priceRuleService.add(priceRule);
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        return priceRuleService.edit(priceRule);
    }

    @GetMapping("/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
        return priceRuleService.ifExists(priceRule);
    }

}
