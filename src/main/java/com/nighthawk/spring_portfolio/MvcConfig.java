package com.nighthawk.spring_portfolio;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // set up your own index
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    /* map path and location for "uploads" outside of application resources
       ... creates a directory outside "static" folder, "file:volumes/uploads"
       ... CRITICAL, without this uploaded file will not be loaded/displayed by frontend
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/volumes/uploads/**").addResourceLocations("file:volumes/uploads/");
    }

<<<<<<< HEAD
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**").allowedOrigins("https://gave-csa.github.io");
        registry.addMapping("/**").allowedOrigins("https://vivianknee.github.io", "https://aliyatang.github.io", "http://localhost:5500", "http://127.0.0.1:4100", "https://e-shen2022.github.io", "http://localhost:4000", "https://e-shen2022.github.io", "http://127.0.0.1:4000", "https://gave-csa.github.io", "https://wsw.stu.nighthawkcodingsociety.com");
    }
    /*
=======
>>>>>>> cdcb9d14d10f4883fcd6f6a1f3bf0d6668066381
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://vivianknee.github.io", "https://aliyatang.github.io", "http://localhost:5500", "http://127.0.0.1:4100");
    }
}