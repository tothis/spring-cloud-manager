package com.example.common.config;

import com.example.common.converter.LocalDateConverter;
import com.example.common.converter.LocalDateTimeConverter;
import com.example.common.converter.LocalTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 客户端发送get请求时会使用转换器
        registry.addConverter(new LocalDateConverter());
        registry.addConverter(new LocalTimeConverter());
        registry.addConverter(new LocalDateTimeConverter());
    }
}