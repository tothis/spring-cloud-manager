package com.example.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.entity.Task;
import com.example.admin.mapper.TaskMapper;
import com.example.admin.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public Task getByName(String name) {
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .eq(StrUtil.isNotBlank(name), Task::getTaskName, name);
        Task result = super.baseMapper.selectOne(queryWrapper);
        return result;
    }

    @Override
    public Page<Task> page(Task entity) {
        Page<Task> page = entity.newPage();
        // 排序
        page.addOrder(OrderItem.asc("id"));
        // 构建查询条件
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .like(StrUtil.isNotBlank(entity.getTaskName()), Task::getTaskName, entity.getTaskName())
                .eq(entity.getTaskState() != null, Task::getTaskState, entity.getTaskState());
        Page<Task> result = super.baseMapper.selectPage(page, queryWrapper);
        return result;
    }
}