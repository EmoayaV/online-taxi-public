package com.msb.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.DriverCarConstants;
import com.msb.internalcommon.constant.OrderConstants;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.OrderInfo;
import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.OrderRequest;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.internalcommon.response.OrderDriverResponse;
import com.msb.internalcommon.util.RedisPrefixUtils;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import com.msb.serviceorder.remote.ServiceDriverUserClient;
import com.msb.serviceorder.remote.ServiceMapClient;
import com.msb.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.QueryEval;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
@Slf4j
public class OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;


    public ResponseResult add(@RequestBody OrderRequest orderRequest) {

        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        log.info("orderRequest.getAddress()：" + orderRequest.getAddress());
        log.info("测试城市是否有司机结果：" + availableDriver);
        if (!availableDriver.getData()) {
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(), CommonStatusEnum.CITY_DRIVER_EMPTY.getValue());
        }

        //下单的设备是否是黑名单设备
        String deviceCode = orderRequest.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;
        //设置key，看看原来有没有key
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean) {
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i > 2) {
                //当前设备超过下单次数
                return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);//value加1
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1, TimeUnit.HOURS);
        }

        //判断下单的城市和计价规则是否正常
        if (!isPriceRuleExists(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }


        //判断乘客有正在进行的订单，则不允许下单
        if (isOrderGoingOn(orderRequest.getPassengerId()) > 0) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue());
        }

        //创建订单
        OrderInfo orderInfo = new OrderInfo();

        //将orderRequest的属性传入orderInfo
        BeanUtils.copyProperties(orderRequest, orderInfo);

        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);

        //派单
        dispatchRealTimeOrder(orderInfo);

        return ResponseResult.success();
    }

    //判断下单的城市和计价规则是否正常
    public boolean isPriceRuleExists(OrderRequest orderRequest) {
        String fareType = orderRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule();
        priceRule.setVehicleType(vehicleType);
        priceRule.setCityCode(cityCode);

        ResponseResult<Boolean> booleanResponseResult = servicePriceClient.ifPriceExists(priceRule);
        return booleanResponseResult.getData();
    }


    //判断乘客是否有业务中的订单
    public Long isOrderGoingOn(Long passengerId) {

        //判断有正在进行的订单，则不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);

        // =======================================================
        //and里面的参数是consume接口，所以要传一个consumer接口的实现类
        //        @FunctionalInterface
        //        public interface Consumer<T> {
        //            void accept(T t);
        // 如果代码块的语句只有一条，可以省略大括号和分号,如果有return,return也要省略
        // 如果参数有且只有一个，那么小括号可以省略
        // =======================================================
        queryWrapper.and(Wrapper -> Wrapper.eq("order_status", OrderConstants.ORDER_START)
                .or().eq("order_status", OrderConstants.RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.TO_PICK_IP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.PASSENGER_GETOFF)
                .or().eq("order_status", OrderConstants.TO_START_PAY)
        );

        Long validOrderNumber = orderInfoMapper.selectCount(queryWrapper);

        return validOrderNumber;

    }

    //判断司机是否有业务中的订单
    public Long isDriverOrderGoingOn(Long driverId) {

        //判断有正在进行的订单，则不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);

        // =======================================================
        //and里面的参数是consume接口，所以要传一个consumer接口的实现类
        //        @FunctionalInterface
        //        public interface Consumer<T> {
        //            void accept(T t);
        // 如果代码块的语句只有一条，可以省略大括号和分号,如果有return,return也要省略
        // 如果参数有且只有一个，那么小括号可以省略
        // =======================================================
        queryWrapper.and(Wrapper -> Wrapper.eq("order_status", OrderConstants.RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.TO_PICK_IP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
        );

        Long validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机ID：" + driverId + ",正在进行的订单数量是：" + validOrderNumber);

        return validOrderNumber;

    }


    //实时订单派单逻辑
    public void dispatchRealTimeOrder(OrderInfo orderInfo) {

        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude + "," + depLongitude;

        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);

        ResponseResult<List<GaodeTerminalResponse>> aroundsearch = null;

        radius:
        for (int i = 0; i < radiusList.size(); i++) {
            //获取半径
            Integer radius = radiusList.get(i);
            aroundsearch = serviceMapClient.aroundsearch(center, radius);

            log.info("在半径为：" + radius + "的范围，寻找车辆。");

            //获得终端
            log.info("结果是：" + JSONArray.fromObject(aroundsearch.getData()).toString());

            //解析终端
            JSONArray result = JSONArray.fromObject(aroundsearch.getData());
            for (int j = 0; j < result.size(); j++) {
                JSONObject jsonObject = result.getJSONObject(j);
                String carIdString = jsonObject.getString("carId");
                Long carId = Long.parseLong(carIdString);

                long longitude = jsonObject.getLong("longitude");
                long latitude = jsonObject.getLong("latitude");


                //查询是否有对应的可派单司机
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if (availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()) {
                    log.info("没有车辆ID:" + carId + "对应的司机.");
                    continue;
                } else {
                    log.info("找到了正在出车的司机，他的车辆ID：" + carId);

                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    Long driverId = orderDriverResponse.getDriverId();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    String vehicleNo = orderDriverResponse.getVehicleNo();
                    String licenseId = orderDriverResponse.getLicenseId();


                    //判断司机是否有进行中的订单，则不允许下单
                    if (isDriverOrderGoingOn(driverId) > 0) {
                        continue;
                    }

                    //订单直接匹配司机
                    //查询当前车辆信息
                    QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", carId);
                    //查询当前司机信息


                    orderInfo.setDriverId(driverId);
                    orderInfo.setDriverPhone(driverPhone);
                    orderInfo.setCarId(carId);
                    orderInfo.setReceiveOrderCarLongitude(longitude + "");
                    orderInfo.setReceiveOrderCarLatitude(latitude + "");

                    orderInfo.setReceiveOrderTime(LocalDateTime.now());
                    orderInfo.setLicenseId(licenseId);
                    orderInfo.setVehicleNo(vehicleNo);
                    orderInfo.setOrderStatus(OrderConstants.ORDER_START);


                    //退出，不再进行司机的查找
                    break radius;

                }


            }


            //根据解析的终端查询车辆

            //找到符合的车辆，进行派单

            //如果派单成果，退出循环


        }

    }


}
