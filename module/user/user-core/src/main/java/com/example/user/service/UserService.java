package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.user.entity.User;
import com.example.user.entity.vo.UserGetResponse;
import com.example.user.entity.vo.UserLoginRequest;
import com.example.user.entity.vo.UserSaveRequest;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 保存
     *
     * @param request
     */
    void save(UserSaveRequest request);

    /**
     * 登录
     *
     * @param request
     */
    void login(UserLoginRequest request);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    UserGetResponse getById(Long id);

    /**
     * 获取用户任务
     *
     * @return
     */
    List<UserTaskDTO> getTaskByUserId();
}