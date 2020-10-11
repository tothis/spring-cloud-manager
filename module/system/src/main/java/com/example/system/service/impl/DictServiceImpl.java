package com.example.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redis.constant.RedisKeyConstant;
import com.example.redis.util.RedisUtil;
import com.example.system.entity.Dict;
import com.example.system.entity.DictType;
import com.example.system.entity.vo.DictListResponse;
import com.example.system.entity.vo.DictSaveRequest;
import com.example.system.entity.vo.DictSelectResponse;
import com.example.system.entity.vo.DictTypeSaveRequest;
import com.example.system.mapper.DictMapper;
import com.example.system.mapper.DictTypeMapper;
import com.example.system.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李磊
 * @since 1.0
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
        implements DictService {

    private final DictTypeMapper dictTypeMapper;

    public DictServiceImpl(DictTypeMapper dictTypeMapper) {
        this.dictTypeMapper = dictTypeMapper;
    }

    @Override
    public void save(DictSaveRequest request) {
        Dict entity = new Dict();
        BeanUtil.copyProperties(request, entity);
        DictType dictType = dictTypeMapper.selectById(entity.getDictTypeId());
        String dictKey = RedisKeyConstant.DICT + dictType.getName();

        if (entity.getId() == null) {
            super.baseMapper.insert(entity);
        } else {
            // 字典值发生变化 则先删除原有字典 重新设置
            Dict dict = super.baseMapper.selectById(entity.getId());
            if (!dict.getValue().equals(entity.getValue())) {
                RedisUtil.hashDelete(dictKey, dict.getValue());
            }
            super.baseMapper.updateById(entity);
        }
        // 保存至缓存
        RedisUtil.hashPut(dictKey, entity.getValue(), entity.getLabel());
    }

    @Override
    public void saveType(DictTypeSaveRequest request) {
        DictType entity = new DictType();
        BeanUtil.copyProperties(request, entity);
        if (entity.getId() == null) {
            dictTypeMapper.insert(entity);
        } else {
            DictType dictType = dictTypeMapper.selectById(entity.getId());
            // 字典类型名称发生变化 则修改redis key
            if (!dictType.getName().equals(entity.getName())) {
                String oldKey = RedisKeyConstant.DICT + dictType.getName();
                String newKey = RedisKeyConstant.DICT + entity.getName();
                RedisUtil.rename(oldKey, newKey);
            }
            dictTypeMapper.updateById(entity);
        }
    }

    @Override
    public List<DictListResponse> selectList() {
        return super.baseMapper.selectDictList();
    }

    @Override
    public List<DictSelectResponse> selectSelectList(String name) {
        String dictKey = RedisKeyConstant.DICT + name;
        Map<String, String> all = RedisUtil.hashGetAll(dictKey);
        List<DictSelectResponse> result;
        if (MapUtil.isEmpty(all)) {
            result = super.baseMapper.selectSelectList(name);
            // 缓存字典
            for (DictSelectResponse item : result) {
                RedisUtil.hashPut(dictKey, item.getValue(), item.getLabel());
            }
        } else {
            result = ListUtil.list(false);
            for (Map.Entry<String, String> entry : all.entrySet()) {
                result.add(new DictSelectResponse(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }

    @Override
    public String selectLabel(String name, String value) {
        String dictKey = RedisKeyConstant.DICT + name;
        String label = RedisUtil.hashGet(dictKey, value);
        if (StrUtil.isBlank(label)) {
            label = super.baseMapper.selectLabel(name, value);
            // 缓存数据
            RedisUtil.hashPut(dictKey, value, label);
        }
        return label;
    }

    @Override
    public void removeDictTypeById(Long id) {
        DictType dictType = dictTypeMapper.selectById(id);
        // 删除字典类型
        RedisUtil.delete(RedisKeyConstant.DICT + dictType.getName());
        dictTypeMapper.deleteById(id);
    }

    @Override
    public void removeById(Long id) {
        Dict dict = super.baseMapper.selectById(id);
        DictType dictType = dictTypeMapper.selectById(dict.getDictTypeId());
        // 删除字典
        RedisUtil.hashDelete(RedisKeyConstant.DICT + dictType.getName()
                , dict.getValue());
        super.baseMapper.deleteById(id);
    }
}