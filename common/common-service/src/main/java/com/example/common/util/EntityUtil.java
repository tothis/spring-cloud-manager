package com.example.common.util;

import cn.hutool.core.bean.BeanUtil;
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
}