package com.example.user.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    @GetMapping
    public ResultEntity<String> get() {
        return ok("admin");
    }
}