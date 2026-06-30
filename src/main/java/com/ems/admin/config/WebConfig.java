package com.ems.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // registry.addResourceHandler("/upload/**")
    //     //         .addResourceLocations("file:upload/");

    //     //         registry.addResourceHandler("/upload/**")
    //     // .addResourceLocations("file:/home/tari01/upload/");
    //      registry.addResourceHandler("/uploads/**")
    //             .addResourceLocations("file:/home/tari01/upload/");
    // }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/tari01/upload/");
    }
}
