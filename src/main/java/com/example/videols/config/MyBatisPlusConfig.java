package com.example.videols.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.videols.mapper")
public class MyBatisPlusConfig {
}
