package com.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.model.Task;

/**
 * @author 李磊
 * @since 1.0
 */
public interface TaskService extends IService<Task> {

    /**
     * 根据name查询
     */
    Task getByName(String name);

    /**
     * 分页查询
     */
    Page<Task> page(Task task);
}