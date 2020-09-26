package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.entity.Dict;
import com.example.system.entity.vo.DictListResponse;
import com.example.system.entity.vo.DictSaveRequest;
import com.example.system.entity.vo.DictSelectResponse;
import com.example.system.entity.vo.DictTypeSaveRequest;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
public interface DictService extends IService<Dict> {

    /**
     * 保存
     *
     * @param request
     */
    void save(DictSaveRequest request);

    /**
     * 保存分类
     *
     * @param request
     */
    void saveType(DictTypeSaveRequest request);

    /**
     * 分页查询
     *
     * @return
     */
    List<DictListResponse> selectList();

    /**
     * 删除字典类型
     *
     * @param id
     */
    void removeDictTypeById(Long id);

    /**
     * 删除字典
     *
     * @param id
     */
    void removeById(Long id);

    /**
     * 根据字典分类获取字典
     *
     * @param type
     * @return
     */
    List<DictSelectResponse> selectSelectList(String type);

    /**
     * 根据字典类型和字典value获取字典label
     *
     * @param name
     * @param value
     * @return
     */
    String selectLabel(String name, String value);
}