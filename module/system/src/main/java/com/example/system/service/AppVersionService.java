package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.entity.AppVersion;

/**
 * @author 李磊
 * @since 1.0
 */
public interface AppVersionService extends IService<AppVersion> {
    /**
     * 根据设备类型获取最新版本号
     *
     * @param osType
     * @return
     */
    AppVersion getByCodeAndOsType(Byte osType);
}