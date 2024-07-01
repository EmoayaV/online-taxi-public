package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeServiceResponse;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bouncycastle.asn1.its.Longitude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
@Slf4j
public class GaodeTerminalClient {
    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<GaodeTerminalResponse> add(String name, String desc){
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
        url.append("&");
        url.append("desc=" + desc);
        log.info("创建终端的请求："+url.toString());

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        log.info("创建终端的响应："+body);

        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");

        GaodeTerminalResponse gaodeTerminalResponse = new GaodeTerminalResponse();
        gaodeTerminalResponse.setTid(tid);

        return ResponseResult.success(gaodeTerminalResponse);
    }


    public ResponseResult<List<GaodeTerminalResponse>> aroundsearch(String center, Integer radius){
        //拼装请求url
        //https://tsapi.amap.com/v1/track/terminal/aroundsearch?&key=b8a8989856a6d3969d7cf1c7e033f074&sid=1025039&center=39.98,116.38&radius=5000
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUNDSEARCH_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("center=" + center);
        url.append("&");
        url.append("radius=" + radius);
        log.info("终端搜索的请求："+url.toString());

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        log.info("终端搜索的响应："+body);

        //解析终端搜索结果
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        List<GaodeTerminalResponse> terminalResponseList = new ArrayList<>();

        JSONArray results = data.getJSONArray("results");
        for (int i = 0; i < results.size(); i++) {
            GaodeTerminalResponse gaodeTerminalResponse = new GaodeTerminalResponse();

            JSONObject jsonObject = results.getJSONObject(i);
            Long carId = Long.parseLong(jsonObject.getString("desc"));
            String tid = jsonObject.getString("tid");

            JSONObject location = jsonObject.getJSONObject("location");
            long longitude = location.getLong("longitude");
            long latitude = location.getLong("latitude");

            gaodeTerminalResponse.setCarId(carId);
            gaodeTerminalResponse.setTid(tid);
            gaodeTerminalResponse.setLongitude(longitude);
            gaodeTerminalResponse.setLatitude(latitude);

            terminalResponseList.add(gaodeTerminalResponse);

        }

        return ResponseResult.success(terminalResponseList);
    }

}
