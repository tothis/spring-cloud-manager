package com.example.common.converter;

import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

/**
 * LocalTime转换器
 *
 * @author 李磊
 * @since 1.0
 */
public class LocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        return DateUtil.parseLocalDateTime(source).toLocalTime();
    }
}