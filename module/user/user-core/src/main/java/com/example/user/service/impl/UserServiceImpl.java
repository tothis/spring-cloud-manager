package com.example.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.common.util.EntityUtil;
import com.example.user.client.TaskClient;
import com.example.user.entity.User;
import com.example.user.entity.vo.UserGetResponse;
import com.example.user.entity.vo.UserLoginRequest;
import com.example.user.entity.vo.UserSaveRequest;
import com.example.user.mapper.UsersMapper;
import com.example.user.mongo.UserLoginRecord;
import com.example.user.service.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, User>
        implements UserService {

    private final MongoTemplate mongoTemplate;

    private final TaskClient taskClient;

    public UserServiceImpl(MongoTemplate mongoTemplate, TaskClient taskClient) {
        this.mongoTemplate = mongoTemplate;
        this.taskClient = taskClient;
    }

    @Override
    public void save(UserSaveRequest request) {
        User entity = new User();
        EntityUtil.copyProperties(request, entity);
        super.save(entity);
    }

    @Override
    public void login(UserLoginRequest request) {
        UserLoginRecord record = new UserLoginRecord();
        EntityUtil.copyProperties(request, record);
        record.setCreateDateTime(LocalDateTime.now());
        mongoTemplate.save(record);
    }

    @Override
    public UserGetResponse getById(Long id) {
        User entity = super.baseMapper.selectById(id);
        UserGetResponse response = new UserGetResponse();
        EntityUtil.copyProperties(entity, response);
        return response;
    }

    @Override
    public List<UserTaskDTO> getTaskByUserId() {
        return taskClient.selectById(0L);
    }
}