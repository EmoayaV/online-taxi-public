package com.msb.apidriver.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.PointRequest;
import feign.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServiceMapClient
 * Package: com.msb.apidriver.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 19:38
 * @Version 1.0
 */

@FeignClient("service-map")
public interface ServiceMapClient {

//    @RequestMapping("/point")
//    public class GaodePointController {
//
//        @Autowired
//        private GaodePointService gaodePointService;
//
//        @PostMapping("/upload")
//        public ResponseResult upload(@RequestBody PointRequest pointRequest){
//            return gaodePointService.upload(pointRequest);
//        }
    @RequestMapping(method = RequestMethod.POST, value = "/point/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest);


}
