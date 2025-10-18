package com.xuxulearn.springbootheadline.config;

import com.alibaba.druid.wall.WallConfigMBean;
import com.xuxulearn.springbootheadline.interceptors.LoginProtectedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {//重写拦截器方法
    @Autowired
    private LoginProtectedInterceptor loginProtectedInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(loginProtectedInterceptor).addPathPatterns("/headline/**");

    }
}
