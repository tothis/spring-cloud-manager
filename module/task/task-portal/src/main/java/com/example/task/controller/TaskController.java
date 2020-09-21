package com.example.task.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import com.example.common.entity.dto.UserTaskIncomeDTO;
import com.example.task.entity.vo.*;
import com.example.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.example.common.entity.ResultEntity.OK;
import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "任务接口")
@RequestMapping("task")
@Validated
@RestController
public class TaskController extends BaseController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation("保存/修改任务")
    @PostMapping
    public ResultEntity save(@Validated @RequestBody TaskSaveRequest vo) {
        taskService.save(vo);
        return OK;
    }

    @ApiOperation("根据id查询任务详情")
    @GetMapping("{id}")
    public ResultEntity<TaskGetResponse> getById(@PathVariable Long id) {
        return ok(taskService.getById(id));
    }

    @ApiOperation("分页查询")
    @PostMapping("page")
    public ResultEntity<Page<TaskPageResponse>> selectPage(@Validated @RequestBody TaskPageRequest entity) {
        Page<TaskPageResponse> list = taskService.selectPage(entity);
        return ok(list);
    }

    @ApiOperation("领取任务")
    @GetMapping("collect/{id}")
    public ResultEntity insertUserTask(@PathVariable Long id) {
        taskService.insertUserTask(id);
        return OK;
    }

    @ApiOperation("提交任务")
    @PostMapping("submit")
    public ResultEntity updateUserTaskContent(@Validated @RequestBody TaskSubmitRequest request) {
        taskService.updateUserTaskContent(request);
        return OK;
    }

    @ApiOperation("查看当前用户任务")
    @PostMapping("page/user")
    public ResultEntity<Page<TaskPageUserResponse>> selectUserTaskByUserId(@Validated @RequestBody TaskPageUserRequest request) {
        return ok(taskService.selectUserTaskByUserId(request));
    }

    @ApiIgnore
    @GetMapping("userIncome/{userId}")
    public ResultEntity<UserTaskIncomeDTO> selectUserTaskByUserId(@PathVariable Long userId) {
        return ok(taskService.selectUserIncomeByUserId(userId));
    }
}