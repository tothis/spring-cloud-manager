package com.example.common.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.PageEntity;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@UtilityClass
public class EntityUtil extends BeanUtil {
    @SneakyThrows
    public <A, B> List<B> copyListProperties(List<A> a, Class<B> type) {
        List<B> result = new ArrayList<>();
        for (A item : a) {
            B response = type.newInstance();
            EntityUtil.copyProperties(item, response);
            result.add(response);
        }
        return result;
    }

    public static <T> PageEntity<T> toPage(Page page, Class<T> type) {
        List<T> list = EntityUtil.copyListProperties(page.getRecords(), type);
        return new PageEntity(list, page.getTotal());
    }
}