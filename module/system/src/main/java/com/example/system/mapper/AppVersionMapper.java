package com.example.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.entity.AppVersion;
import org.apache.ibatis.annotations.Select;

/**
 * @author 李磊
 * @since 1.0
 */
public interface AppVersionMapper extends BaseMapper<AppVersion> {
    /**
     * 根据设备类型获取最新版本号
     *
     * @param osType
     * @return
     */
    @Select("select `code` from `app_version` where os_type = ${osType} " +
            "and state = 0 order by code desc limit 1")
    AppVersion getByCodeAndOsType(Byte osType);
}