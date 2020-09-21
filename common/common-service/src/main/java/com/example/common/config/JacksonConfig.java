package com.example.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * jackson配置
 * <p>
 * jackson只能指定Date序列化格式 LocalDateTime需要手动配置
 *
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class JacksonConfig {

    @Value("${spring.jackson.date-format}")
    private String pattern;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDate.class
                    , new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern)));
            builder.deserializerByType(LocalDate.class
                    , new LocalDateDeserializer(DateTimeFormatter.ofPattern(pattern)));

            builder.serializerByType(LocalTime.class
                    , new LocalTimeSerializer(DateTimeFormatter.ofPattern(pattern)));
            builder.deserializerByType(LocalTime.class
                    , new LocalTimeDeserializer(DateTimeFormatter.ofPattern(pattern)));

            builder.serializerByType(LocalDateTime.class
                    , new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)));
            builder.deserializerByType(LocalDateTime.class
                    , new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern)));

            // js数字精度小于java long 返回数据时把long类型属性转为字符串类型
            builder.serializerByType(Long.class, ToStringSerializer.instance);
        };
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(ObjectMapper mapper) {
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @SneakyThrows
            @Override
            public void serialize(Object o, JsonGenerator g, SerializerProvider s) {
                String fieldName = g.getOutputContext().getCurrentName();
                // 反射获取字段类型
                Field field = g.getCurrentValue().getClass().getDeclaredField(fieldName);
                if (Objects.equals(field.getType(), String.class)) {
                    // 字符串型空值""
                    g.writeString("");
                } else if (Objects.equals(field.getType(), List.class)) {
                    // 列表型空值返回[]
                    g.writeStartArray();
                    g.writeEndArray();
                } else if (Objects.equals(field.getType(), Map.class)) {
                    // map型空值返回{}
                    g.writeStartObject();
                    g.writeEndObject();
                } else {
                    // 默认返回""
                    g.writeString("");
                }
            }
        });
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        return converter;
    }
}