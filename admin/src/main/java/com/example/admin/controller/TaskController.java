package com.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.model.Task;
import com.example.admin.service.TaskService;
import com.example.common.controller.BaseController;
import com.example.common.result.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "任务接口")
@RequestMapping("task")
@RestController
public class TaskController extends BaseController<TaskService> {

    @ApiOperation("分页查询")
    @PostMapping("page")
    public ResultEntity<Page<Task>> page(@RequestBody Task entity) {
        Page<Task> list = super.baseService.page(entity);
        return ok(list);
    }

    @ApiOperation("根据id查询")
    @GetMapping("{id}")
    public ResultEntity<Task> get(@PathVariable Integer id) {
        Task task = super.baseService.getById(id);
        return ok(task);
    }

    @ApiOperation("保存")
    @PostMapping
    public ResultEntity saveOrUpdate(@RequestBody Task entity) {
        super.baseService.saveOrUpdate(entity);
        return OK;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("{id}")
    public ResultEntity removeById(@PathVariable Integer id) {
        super.baseService.removeById(id);
        return OK;
    }
}