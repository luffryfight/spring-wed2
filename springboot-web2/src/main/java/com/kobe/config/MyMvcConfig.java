package com.kobe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作用：
 * 2020/11/11
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/index.html").setViewName("index");
        //对应退出功能，退出后应该取消session,否则推出后任然可以访问资源
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter()).
                addPathPatterns("/**").excludePathPatterns("/index.html").
                excludePathPatterns("/css/**").excludePathPatterns("/js/**").
                excludePathPatterns("/login").excludePathPatterns("/qqLogin").
                excludePathPatterns("/img/**").excludePathPatterns("/user/login").
                excludePathPatterns("/need_login.do");
    }
}
