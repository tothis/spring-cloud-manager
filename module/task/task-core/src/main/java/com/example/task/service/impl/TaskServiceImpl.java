package com.example.task.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.BaseEntity;
import com.example.common.entity.PageEntity;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.common.util.EntityUtil;
import com.example.task.entity.Task;
import com.example.task.entity.vo.TaskGetResponse;
import com.example.task.entity.vo.TaskPageRequest;
import com.example.task.entity.vo.TaskPageResponse;
import com.example.task.entity.vo.TaskSaveRequest;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public void save(TaskSaveRequest request) {
        Task entity = new Task();
        EntityUtil.copyProperties(request, entity);
        if (entity.getId() == null) {
            entity.setTaskState(true);
            super.baseMapper.insert(entity);
        } else {
            super.baseMapper.updateById(entity);
        }
    }

    @Override
    public PageEntity<TaskPageResponse> selectPage(TaskPageRequest request) {
        Page<Task> page = BaseEntity.newPage(request.getPageNum());
        // 排序
        page.addOrder(OrderItem.asc("createDateTime"));
        // 构建查询条件
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .like(StrUtil.isNotBlank(request.getTaskName()), Task::getTaskName
                        , request.getTaskName());
        Page<Task> response = super.baseMapper.selectPage(page, queryWrapper);
        List<TaskPageResponse> result = EntityUtil.copyListProperties(response
                .getRecords(), TaskPageResponse.class);
        PageEntity<TaskPageResponse> pageEntity = new PageEntity(response.getTotal());
        pageEntity.setData(result);
        return pageEntity;
    }

    @Override
    public TaskGetResponse getById(Long id) {
        Task task = super.baseMapper.selectById(id);
        TaskGetResponse response = new TaskGetResponse();
        EntityUtil.copyProperties(task, response);
        return response;
    }

    @Override
    public List<UserTaskDTO> selectTaskByUserId(Long userId) {
        // 构建查询条件
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .like(Task::getCreateBy, userId);
        List<Task> result = super.baseMapper.selectList(queryWrapper);
        List<UserTaskDTO> response = EntityUtil.copyListProperties(result
                , UserTaskDTO.class);
        return response;
    }
}