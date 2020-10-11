package com.example.user.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.user.entity.vo.UserGetResponse;
import com.example.user.entity.vo.UserLoginRequest;
import com.example.user.entity.vo.UserSaveRequest;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "用户接口")
@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public void login(@RequestBody UserLoginRequest request) {
        userService.login(request);
    }

    @ApiOperation("保存")
    @PostMapping
    public void save(@RequestBody UserSaveRequest request) {
        this.userService.save(request);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public UserGetResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @ApiOperation("获取用户任务")
    @GetMapping("task")
    public List<UserTaskDTO> getTaskByUserId() {
        return userService.getTaskByUserId();
    }
}