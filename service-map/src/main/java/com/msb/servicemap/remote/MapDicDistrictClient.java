package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: MapDicDistrictClient
 * Package: com.msb.servicemap.remote
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 20:15
 * @Version 1.0
 */

@Service
public class MapDicDistrictClient {
    @Value("${amap.key}")
    private String amapkey;

    @Autowired
    private RestTemplate restTemplate;


    public String dicDistrict(String keywords) {

        //拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords=" + keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key=" + amapkey);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        return forEntity.getBody();
    }


}
