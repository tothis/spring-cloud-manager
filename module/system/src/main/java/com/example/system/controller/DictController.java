package com.example.system.controller;

import com.example.common.controller.BaseController;
import com.example.common.entity.ResultEntity;
import com.example.system.entity.vo.DictListResponse;
import com.example.system.entity.vo.DictSaveRequest;
import com.example.system.entity.vo.DictSelectResponse;
import com.example.system.entity.vo.DictTypeSaveRequest;
import com.example.system.service.DictService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.common.entity.ResultEntity.OK;
import static com.example.common.entity.ResultEntity.ok;

/**
 * @author 李磊
 * @since 1.0
 */
@Api(tags = "字典接口")
@RequestMapping("dict")
@RestController
public class DictController extends BaseController {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping("list")
    public ResultEntity<List<DictListResponse>> list() {
        return ok(dictService.selectList());
    }

    /**
     * 根据字典分类获取字典
     *
     * @param name
     * @return
     */
    @GetMapping("select")
    public ResultEntity<List<DictSelectResponse>> select(String name) {
        return ok(dictService.selectSelectList(name));
    }

    @PostMapping("type")
    public ResultEntity saveType(@RequestBody DictTypeSaveRequest request) {
        dictService.saveType(request);
        return OK;
    }

    @PostMapping
    public ResultEntity save(@RequestBody DictSaveRequest request) {
        dictService.save(request);
        return OK;
    }

    @DeleteMapping("type/{id}")
    public ResultEntity deleteType(@PathVariable Long id) {
        dictService.removeDictTypeById(id);
        return OK;
    }

    @DeleteMapping("{id}")
    public ResultEntity delete(@PathVariable Long id) {
        dictService.removeById(id);
        return OK;
    }
}