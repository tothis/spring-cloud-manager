package com.example.common.converter;

import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * LocalDate转换器
 *
 * @author 李磊
 * @since 1.0
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return DateUtil.parseLocalDateTime(source).toLocalDate();
    }
}