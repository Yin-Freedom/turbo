package com.freedom.backend.demo;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.freedom.backend.engine.annotation.EnableWorkFlowEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableWorkFlowEngine
@SpringBootApplication(scanBasePackages = {"com.freedom.backend.demo"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
