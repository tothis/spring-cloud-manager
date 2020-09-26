package com.example.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.entity.Dict;
import com.example.system.entity.vo.DictListResponse;
import com.example.system.entity.vo.DictSelectResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 查询列表
     *
     * @return
     */
    List<DictListResponse> selectDictList();

    /**
     * 根据字典分类获取字典
     *
     * @param name
     * @return
     */
    List<DictSelectResponse> selectSelectList(String name);

    /**
     * 根据字典类型和字典value获取字典label
     *
     * @param name
     * @param value
     * @return
     */
    String selectLabel(@Param("name") String name, @Param("value") String value);
}