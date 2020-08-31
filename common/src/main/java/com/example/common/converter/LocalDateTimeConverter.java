package com.example.common.converter;

import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * LocalDateTime转换器
 *
 * @author 李磊
 * @since 1.0
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return DateUtil.parseLocalDateTime(source);
    }
}