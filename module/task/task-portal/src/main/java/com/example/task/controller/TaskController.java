package com.example.task.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.PageEntity;
import com.example.common.entity.dto.UserTaskDTO;
import com.example.task.entity.vo.TaskGetResponse;
import com.example.task.entity.vo.TaskPageRequest;
import com.example.task.entity.vo.TaskPageResponse;
import com.example.task.entity.vo.TaskSaveRequest;
import com.example.task.mq.entity.MessageQueue;
import com.example.task.mq.producer.TaskProducer;
import com.example.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "任务接口")
@RequestMapping("task")
@RestController
public class TaskController extends BaseController {

    private final TaskService taskService;
    private final TaskProducer taskProducer;

    public TaskController(TaskService taskService, TaskProducer taskProducer) {
        this.taskService = taskService;
        this.taskProducer = taskProducer;
    }

    @ApiOperation("保存/修改任务")
    @PostMapping
    public void save(@Valid @RequestBody TaskSaveRequest request) {
        taskService.save(request);
    }

    @ApiOperation("根据id查询任务详情")
    @GetMapping("{id}")
    public TaskGetResponse get(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @ApiOperation("分页查询")
    @PostMapping("page")
    public PageEntity<TaskPageResponse> page(@Valid @RequestBody TaskPageRequest request) {
        return taskService.selectPage(request);
    }

    @ApiIgnore
    @GetMapping("user/{userId}")
    public List<UserTaskDTO> selectTaskByUserId(@PathVariable Long userId) {
        return taskService.selectTaskByUserId(userId);
    }

    @GetMapping("mq/{time}")
    public void mq(@PathVariable long time) {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.setId(1);
        messageQueue.setTime(time);
        messageQueue.setType(MessageQueue.Type.AUTO_CANCEL);
        taskProducer.send(messageQueue);
    }
}