package com.example.admin.controller;

import com.example.common.controller.BaseController;
import com.example.common.result.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 李磊
 * @since 1.0
 */
@RestController
public class TestController extends BaseController {

    @GetMapping("get")
    public ResultEntity<Map> get(@RequestParam Map params) {
        return ok(params);
    }

    @GetMapping
    public ResultEntity index() {
        return SUCCESS;
    }
}