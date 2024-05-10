package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverUserClient;
import com.msb.apidriver.remote.ServiceMapClient;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ApiDriverPointRequest;
import com.msb.internalcommon.request.PointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: PointService
 * Package: com.msb.apidriver.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 16:31
 * @Version 1.0
 */

@Service
@Slf4j
public class PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest){

        //获取carId
        Long carId = apiDriverPointRequest.getCarId();

        //通过carId 获取 tid和trid，调用service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();
        log.info("tid:"+tid);
        log.info("trid:"+trid);

        //轨迹点上传
        PointRequest pointRequest = new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());

        return serviceMapClient.upload(pointRequest);
    }



}
