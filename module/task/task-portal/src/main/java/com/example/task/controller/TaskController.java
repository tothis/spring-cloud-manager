package com.example.task.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.PageEntity;
import com.example.common.entity.ResultEntity;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.task.entity.vo.TaskGetResponse;
import com.example.task.entity.vo.TaskPageRequest;
import com.example.task.entity.vo.TaskPageResponse;
import com.example.task.entity.vo.TaskSaveRequest;
import com.example.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static com.example.common.entity.ResultEntity.OK;
import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "任务接口")
@RequestMapping("task")
@RestController
public class TaskController extends BaseController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation("保存/修改任务")
    @PostMapping
    public ResultEntity save(@Valid @RequestBody TaskSaveRequest request) {
        taskService.save(request);
        return OK;
    }

    @ApiOperation("根据id查询任务详情")
    @GetMapping("{id}")
    public ResultEntity<TaskGetResponse> get(@PathVariable Long id) {
        return ok(taskService.getById(id));
    }

    @ApiOperation("分页查询")
    @PostMapping("page")
    public ResultEntity<PageEntity<TaskPageResponse>> page(@Valid @RequestBody TaskPageRequest request) {
        return ok(taskService.selectPage(request));
    }

    @ApiIgnore
    @GetMapping("user/{userId}")
    public List<UserTaskDTO> selectTaskByUserId(@PathVariable Long userId) {
        return taskService.selectTaskByUserId(userId);
    }
}