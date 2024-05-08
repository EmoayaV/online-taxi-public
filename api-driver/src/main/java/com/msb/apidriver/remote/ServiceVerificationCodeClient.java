package com.msb.apidriver.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DriverUserExistsResponse;
import com.msb.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServiceVerificationCodeClient
 * Package: com.msb.apidriver.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 16:14
 * @Version 1.0
 */

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    //    @ResponseBody
    //    @RequestMapping("/numberCode/{size}")
    //    public ResponseResult numberCode(@PathVariable("size") int size) {
    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> getVerificationCode(@PathVariable("size") int size);


}
