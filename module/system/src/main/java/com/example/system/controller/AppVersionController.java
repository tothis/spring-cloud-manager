package com.example.system.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import com.example.system.entity.AppVersion;
import com.example.system.service.AppVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "版本接口")
@RequestMapping("version")
@RestController
public class AppVersionController extends BaseController {

    private final AppVersionService appVersionService;

    public AppVersionController(AppVersionService appVersionService) {
        this.appVersionService = appVersionService;
    }

    /**
     * 根据设备类型获取最新版本号
     */
    @ApiOperation("查询版本")
    @GetMapping
    public ResultEntity<AppVersion> get(Byte osType) {
        AppVersion task = appVersionService.getByCodeAndOsType(osType);
        return ok(task);
    }
}