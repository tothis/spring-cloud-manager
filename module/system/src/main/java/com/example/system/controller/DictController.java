package com.example.system.controller;

import com.example.common.controller.BaseController;
import com.example.system.entity.vo.DictListResponse;
import com.example.system.entity.vo.DictSaveRequest;
import com.example.system.entity.vo.DictSelectResponse;
import com.example.system.entity.vo.DictTypeSaveRequest;
import com.example.system.service.DictService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<DictListResponse> list() {
        return dictService.selectList();
    }

    /**
     * 根据字典分类获取字典
     *
     * @param name
     * @return
     */
    @GetMapping("select")
    public List<DictSelectResponse> select(String name) {
        return dictService.selectSelectList(name);
    }

    @PostMapping("type")
    public void saveType(@RequestBody DictTypeSaveRequest request) {
        dictService.saveType(request);
    }

    @PostMapping
    public void save(@RequestBody DictSaveRequest request) {
        dictService.save(request);
    }

    @DeleteMapping("type/{id}")
    public void deleteType(@PathVariable Long id) {
        dictService.removeDictTypeById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        dictService.removeById(id);
    }
}