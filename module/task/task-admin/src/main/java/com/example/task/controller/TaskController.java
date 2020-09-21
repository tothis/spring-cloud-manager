package com.example.task.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@RequestMapping("task")
@RestController
public class TaskController extends BaseController {

    @GetMapping
    public ResultEntity<String> get(String k) {
        log.info(k);
        return ok("admin");
    }

    @PostMapping
    public ResultEntity<String> post(@RequestBody Map<String, String> params) {
        log.info(params.get("k"));
        return ok("admin");
    }
}