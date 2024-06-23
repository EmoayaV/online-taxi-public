package com.msb.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msb.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: DriverUserMapper
 * Package: com.msb.servicemap.mapper
 * Description:
 *
 * @Author Emoaya
 * @Create 2024/5/6 14:40
 * @Version 1.0
 */

@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {
    public long selectDriverUserCountByCityCode(@Param("cityCode") String cityCode);
}
