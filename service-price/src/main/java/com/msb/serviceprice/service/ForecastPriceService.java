package com.msb.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.internalcommon.util.BigDecimalUtiles;
import com.msb.serviceprice.mapper.PriceRuleMapper;
import com.msb.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: ForecastPriceService
 * Package: com.msb.serviceprice.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/4/29 16:38
 * @Version 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType) {
        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：" + distance);
        log.info("时长：" + duration);

        log.info("读取计价规则");
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        log.info("priceRules：" + priceRules.toString());
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("priceRule：" + priceRule);


        log.info("根据距离、时常和计价规则计算价格");
        double price = getPrice(distance, duration, priceRule);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(cityCode);
        forecastPriceResponse.setVehicleType(vehicleType);
        return ResponseResult.success(forecastPriceResponse);
    }

    //根据距离时常计算最终价格
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0;

        //起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtiles.add(price,startFare);

        //里程费
        //总里程 单位：m
        double distanceMile = BigDecimalUtiles.divide(distance,1000);
        //起步的里程
        double startMile = priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtiles.substract(distanceMile,startMile);
        //最终收费的里程数 km
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        //计程单价 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        //里程价格
        double mileFare = BigDecimalUtiles.multiply(mile, unitPricePerMile);
        price = BigDecimalUtiles.add(price, mileFare);

        //时常费
        //分钟数
        double timeMinutes = BigDecimalUtiles.divide(duration,60);
        //计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //时常费用
        double timeFare = BigDecimalUtiles.multiply(timeMinutes, unitPricePerMinute);
        price = BigDecimalUtiles.add(price, timeFare);

        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        priceBigDecimal = priceBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();
    }

}
