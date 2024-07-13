package com.freedom.backend.engine.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.freedom.backend.engine")
@MapperScan("com.freedom.backend.engine.dao")
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
public class WorkFlowEngineConfig {

}
