package com.example.user.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import com.example.user.entity.vo.UserGetResponse;
import com.example.user.entity.vo.UserLoginRequest;
import com.example.user.entity.vo.UserSaveRequest;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.example.common.entity.ResultEntity.OK;
import static com.example.common.entity.ResultEntity.ok;

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

    @ApiOperation("登陆")
    @PostMapping("login")
    public ResultEntity login(@RequestBody UserLoginRequest request) {
        userService.login(request);
        return OK;
    }

    @ApiOperation("保存")
    @PostMapping
    public ResultEntity save(@RequestBody UserSaveRequest vo) {
        this.userService.save(vo);
        return OK;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public ResultEntity<UserGetResponse> userInfo(@PathVariable Long id) {
        return ok(userService.getById(id));
    }
}