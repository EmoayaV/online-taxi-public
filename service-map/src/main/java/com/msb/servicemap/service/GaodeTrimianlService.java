package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.servicemap.remote.GaodeTerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * ClassName: GaodeTrimianlService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 16:36
 * @Version 1.0
 */

@Service
public class GaodeTrimianlService {

    @Autowired
    private GaodeTerminalClient gaodeTerminalClient;


    public ResponseResult<GaodeTerminalResponse> add(String name, String desc){
        return gaodeTerminalClient.add(name, desc);
    }


    public ResponseResult<List<GaodeTerminalResponse>> aroundsearch(String center, Integer radius){
        return gaodeTerminalClient.aroundsearch(center,radius);
    }
}
