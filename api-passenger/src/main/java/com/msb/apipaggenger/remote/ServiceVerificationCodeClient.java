package com.msb.apipaggenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServiceVerificationCodeClient
 * Package: com.msb.apipaggenger.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/30 13:47
 * @Version 1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    //    @ResponseBody
    //    @RequestMapping("/numberCode/{size}")
    //    public ResponseResult numberCode(@PathVariable("size") int size) {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);

}
