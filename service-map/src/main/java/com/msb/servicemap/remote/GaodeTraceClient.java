package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.GaodeTerminalResponse;
import com.msb.internalcommon.response.GaodeTraceResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: GaodeTraceClient
 * Package: com.msb.servicemap.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/8 21:48
 * @Version 1.0
 */

@Service
public class GaodeTraceClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<GaodeTraceResponse> add(String tid) {
        //拼装请求url
        //https://tsapi.amap.com/v1/track/trace/add?key=b8a8989856a6d3969d7cf1c7e033f074&sid=1025223&tid=898283805
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACE_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("tid=" + tid);


        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        //轨迹id和name
        String trid = data.getString("trid");
        String trname = "";
        if (data.has("trname")) {
            trname = data.getString("trname");
        }


        GaodeTraceResponse gaodeTraceResponse = new GaodeTraceResponse();
        gaodeTraceResponse.setTrid(trid);
        gaodeTraceResponse.setTrname(trname);


        return ResponseResult.success(gaodeTraceResponse);
    }

}
