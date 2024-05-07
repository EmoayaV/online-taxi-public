package com.msb.apiboss.service;

import com.msb.apiboss.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: DriverCarBindingRelationshipService
 * Package: com.msb.apiboss.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/7 14:16
 * @Version 1.0
 */

@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }

}
