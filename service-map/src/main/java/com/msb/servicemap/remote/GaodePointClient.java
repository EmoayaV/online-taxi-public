package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.PointDTO;
import com.msb.internalcommon.request.PointRequest;
import com.msb.internalcommon.response.GaodeTraceResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * ClassName: GaodePointClient
 * Package: com.msb.servicemap.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/9 14:53
 * @Version 1.0
 */
@Service
@Slf4j
public class GaodePointClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;


    public ResponseResult upload(PointRequest pointRequest){
        //拼装请求url
        //https://tsapi.amap.com/v1/track/point/upload?key=b8a8989856a6d3969d7cf1c7e033f074&sid=1025223&tid=898283805&trid=40&cc=[{"location":"116.358005,39.962154","locatetime":1715153673378}]
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.POINT_UPLOAD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapSid);
        url.append("&");
        url.append("tid=" + pointRequest.getTid());
        url.append("&");
        url.append("trid=" + pointRequest.getTrid());
        url.append("&");
        url.append("points=");
        // "[" ---> %5B
        // "]" ---> %5D
        // "{" ---> %7B
        // "}" ---> %7D
        //  "  ---> %22
        //  :  ---> %3A
        //  ,  ---> %2C
        url.append("%5B");
        PointDTO[] points = pointRequest.getPoints();
        for (PointDTO pointDTO : points){
            url.append("%7B");
            String location = pointDTO.getLocation();
            String locatetime = pointDTO.getLocatetime();
            url.append("%22location%22");
            url.append("%3A");
            url.append("%22"+location+"%22");
            url.append("%2C");
            url.append("%22locatetime%22");
            url.append("%3A");
            url.append(locatetime);
            url.append("%7D");
        }
        url.append("%5D");

        log.info("上传位置请求："+url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);
        String body = forEntity.getBody();
        log.info("上传位置响应："+body);



        return ResponseResult.success();
    }

}
