package com.example.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.mapper.TaskMapper;
import com.example.admin.model.Task;
import com.example.admin.service.TaskService;
import com.example.common.constant.CommonConstant;
import org.springframework.stereotype.Service;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public Page<Task> page(Task entity) {
        Page<Task> page = new Page<>(entity.getPageNum(), entity.getPageSize());
        // 排序
        page.addOrder(OrderItem.asc("id"));
        // 构建查询条件
        LambdaQueryWrapper<Task> queryWrapper = Wrappers.<Task>lambdaQuery()
                .like(StrUtil.isNotBlank(entity.getTaskName()), Task::getTaskName, entity.getTaskName())
                .eq(entity.getTaskState() != null, Task::getTaskState, entity.getTaskState())
                .eq(Task::getState, CommonConstant.NORMAL);
        Page<Task> result = super.baseMapper.selectPage(page, queryWrapper);
        return result;
    }
}