package com.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.model.Task;
import com.example.admin.service.TaskService;
import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.common.constant.ValidatedConstant.TASK_NAME_FORMAT;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "任务接口")
@Validated
@RequestMapping("task")
@RestController
public class TaskController extends BaseController<TaskService> {

    @ApiOperation("分页查询")
    @PostMapping("page")
    public ResultEntity<Page<Task>> page(@RequestBody @Validated Task entity) {
        Page<Task> list = super.baseService.page(entity);
        return ok(list);
    }

    @ApiOperation("根据id查询")
    @GetMapping("{id}")
    public ResultEntity<Task> get(@PathVariable Long id) {
        Task task = super.baseService.getById(id);
        return ok(task);
    }

    @ApiOperation("根据name查询")
    @GetMapping("name/{name}")
    public ResultEntity<Task> get(
            @PathVariable @Length(min = 2, max = 4, message = TASK_NAME_FORMAT) String name
    ) {
        Task task = super.baseService.getByName(name);
        return ok(task);
    }

    @ApiOperation("保存")
    @PostMapping
    public ResultEntity saveOrUpdate(@RequestBody @Validated Task entity) {
        super.baseService.saveOrUpdate(entity);
        return OK;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("{id}")
    public ResultEntity removeById(@PathVariable Long id) {
        super.baseService.removeById(id);
        return OK;
    }
}