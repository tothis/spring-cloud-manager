package com.example.task.controller;

import com.example.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@RequestMapping("task")
@RestController
public class TaskController extends BaseController {

    @GetMapping
    public String get(String k) {
        log.info(k);
        return "admin";
    }

    @PostMapping
    public String post(@RequestBody Map<String, String> params) {
        log.info(params.get("k"));
        return "admin";
    }
}