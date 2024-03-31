package com.msb.apipaggenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServicePassengerUserClient
 * Package: com.msb.apipaggenger.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/3/31 14:52
 * @Version 1.0
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    //   @PostMapping("/user")
    //   @ResponseBody
    //   public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    ResponseResult getLoginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}






