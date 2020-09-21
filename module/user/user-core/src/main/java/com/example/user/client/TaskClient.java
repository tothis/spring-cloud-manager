package com.example.user.client;

import com.example.common.constant.ClientConstant;
import com.example.common.entity.ResultEntity;
import com.example.common.entity.dto.UserTaskIncomeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 李磊
 * @since 1.0
 */
@FeignClient(ClientConstant.TASK_PORTAL)
public interface TaskClient {

    /**
     * 根据id查询任务
     *
     * @param id
     * @return
     */
    @GetMapping("task/{id}")
    ResultEntity<UserTaskIncomeDTO> selectById(@PathVariable("id") Long id);
}