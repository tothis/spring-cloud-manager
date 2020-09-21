package com.example.task.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.task.entity.Task;
import com.example.task.entity.vo.TaskGetResponse;
import com.example.task.entity.vo.TaskPageRequest;
import com.example.task.entity.vo.TaskPageResponse;
import com.example.task.entity.vo.TaskSaveRequest;

/**
 * @author 李磊
 * @since 1.0
 */
public interface TaskService extends IService<Task> {

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    Page<TaskPageResponse> selectPage(TaskPageRequest request);

    /**
     * 保存
     *
     * @param request
     * @return
     */
    void save(TaskSaveRequest request);

    /**
     * 根据id查询任务
     *
     * @param id
     * @return
     */
    TaskGetResponse getById(Long id);
}