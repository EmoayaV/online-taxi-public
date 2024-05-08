package com.msb.servicedriveruser.controller;


import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.DriverUserWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author emoaya
 * @since 2024-05-07
 */
@RestController
public class DriverUserWorkStatusController {

    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(), driverUserWorkStatus.getWorkStatus());
    }

}
