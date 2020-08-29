package com.example.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring工具
 *
 * @author 李磊
 * @since 1.0
 */
@Component
public final class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtil.applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name) {
        return (T) SpringUtil.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringUtil.applicationContext.getEnvironment().getProperty(key);
    }

    @Override
    /**
     * spring加载时 如果bean实现applicationContextAware接口 spring容器会在创建该bean后
     * 调用该bean的setApplicationContextAware()方法 并传入applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}