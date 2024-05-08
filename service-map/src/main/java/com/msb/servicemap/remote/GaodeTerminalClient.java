package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeServiceResponse;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: GaodeTerminalClient
 * Package: com.msb.servicemap.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 16:38
 * @Version 1.0
 */

@Service
public class GaodeTerminalClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult add(String name){
        //拼装请求url
        //https://tsapi.amap.com/v1/track/terminal/add?key=b8a8989856a6d3969d7cf1c7e033f074&sid=1025223&name=车辆1
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("name=" + name);


        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");

        GaodeTerminalResponse gaodeTerminalResponse = new GaodeTerminalResponse();
        gaodeTerminalResponse.setTid(tid);

        return ResponseResult.success(gaodeTerminalResponse);
    }

}
