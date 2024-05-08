package com.msb.servicedriveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.internalcommon.dto.DriverUserWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DriverUserWorkStatusService{

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus){

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverId);

        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);

        driverUserWorkStatus.setWorkStatus(workStatus);

        LocalDateTime now = LocalDateTime.now();
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success();
    }

}
