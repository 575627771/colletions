package com.xiaohong.wu.collections.logs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author wu
 * @version 1.0
 * @date 18-12-31 下午6:12
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new MDCInterceptor());
        super.addInterceptors(registry);
    }


}
