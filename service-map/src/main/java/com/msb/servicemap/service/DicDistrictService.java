package com.msb.servicemap.service;

import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.DicDistrict;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicemap.mapper.DicDistrictMapper;
import com.msb.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ClassName: DicDistrictService
 * Package: com.msb.servicemap.service
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/5 19:57
 * @Version 1.0
 */

@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {

        //请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);
        //解析结果
        JSONObject dicDistrictJSONObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJSONObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }


        JSONArray countryJSONArray = dicDistrictJSONObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int i = 0; i < countryJSONArray.size(); i++) {
            JSONObject countryJsonObject = countryJSONArray.getJSONObject(i);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);


            JSONArray provinceJSONArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int j = 0; j < provinceJSONArray.size(); j++) {
                JSONObject provinceJsonObject = provinceJSONArray.getJSONObject(j);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceLevel, provinceParentAddressCode);


                JSONArray cityJSONArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int k = 0; k < cityJSONArray.size(); k++) {
                    JSONObject cityJsonObject = cityJSONArray.getJSONObject(k);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);
                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);




                    JSONArray districtJSONArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0; d < districtJSONArray.size(); d++) {
                        JSONObject districtJsonObject = districtJSONArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        if(districtLevel.equals(AmapConfigConstants.STREET)){
                            continue;
                        }
                        insertDicDistrict(districtAddressCode, districtAddressName, districtLevel, districtParentAddressCode);
                    }
                }
            }
        }

        //插入数据库


        return ResponseResult.success();

    }

    public void insertDicDistrict (String addressCode, String addressName, String level, String parentAddressCode){
        //数据库对象
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        int levelInt = generateLevel(level);
        district.setLevel(levelInt);

        district.setParentAddressCode(parentAddressCode);
        dicDistrictMapper.insert(district);
    }


    public int generateLevel (String level){
        int levelInt = 0;
        if (level.trim().equals("country")) {
            levelInt = 0;
        } else if (level.trim().equals("province")) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        }


        return levelInt;
    }


}
