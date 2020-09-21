package com.example.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * validation配置
 *
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                /**
                 * 普通模式 校验完所有的属性 返回所有的验证失败信息
                 * 快速失败返回模式 有一个验证失败立即返回失败信息
                 */
                // 配置hibernate Validator为快速失败返回
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory().getValidator();
    }
}