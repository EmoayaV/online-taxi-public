package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeServiceResponse;
import lombok.Data;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: GaodeServiceClient
 * Package: com.msb.servicemap.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 15:53
 * @Version 1.0
 */

@Service
public class GaodeServiceClient {
    @Value("${amap.key}")
    private String amapkey;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult add(String name){
        //拼装请求url
        //https://tsapi.amap.com/v1/track/service/add?key=b8a8989856a6d3969d7cf1c7e033f074&name="飞滴出行service"
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key=" + amapkey);
        url.append("&");
        url.append("name=" + name);


        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String sid = data.getString("sid");
        GaodeServiceResponse gaodeServiceResponse = new GaodeServiceResponse();
        gaodeServiceResponse.setSid(sid);

        return ResponseResult.success(gaodeServiceResponse);
    }



}
