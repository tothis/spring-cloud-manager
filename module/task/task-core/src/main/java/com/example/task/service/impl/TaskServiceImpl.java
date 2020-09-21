package com.example.task.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.BaseEntity;
import com.example.task.entity.Task;
import com.example.task.entity.vo.TaskGetResponse;
import com.example.task.entity.vo.TaskPageRequest;
import com.example.task.entity.vo.TaskPageResponse;
import com.example.task.entity.vo.TaskSaveRequest;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        BeanUtil.copyProperties(request, entity);
        if (entity.getId() == null) {
            super.baseMapper.insert(entity);
        } else {
            super.baseMapper.updateById(entity);
        }
    }

    @Override
    public Page<TaskPageResponse> selectPage(TaskPageRequest request) {
        Page<Task> page = BaseEntity.newPage(request.getPageNum());
        // 排序
        page.addOrder(OrderItem.asc("id"));
        // 构建查询条件
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .like(StrUtil.isNotBlank(request.getTaskName()), Task::getTaskName, request.getTaskName());

        Page taskPage = super.baseMapper.selectPage(page, queryWrapper);
        List<TaskPageResponse> result = new ArrayList<>();
        for (Object item : taskPage.getRecords()) {
            TaskPageResponse response = new TaskPageResponse();
            BeanUtil.copyProperties(item, response);
            result.add(response);
        }
        taskPage.setRecords(result);
        return taskPage;
    }

    @Override
    public TaskGetResponse getById(Long id) {
        Task task = super.baseMapper.selectById(id);
        TaskGetResponse response = new TaskGetResponse();
        BeanUtil.copyProperties(task, response);
        return response;
    }
}