package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.entity.AppVersion;
import com.example.system.mapper.AppVersionMapper;
import com.example.system.service.AppVersionService;
import org.springframework.stereotype.Service;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion>
        implements AppVersionService {
    @Override
    public AppVersion getByCodeAndOsType(Byte osType) {
        return this.baseMapper.getByCodeAndOsType(osType);
    }
}