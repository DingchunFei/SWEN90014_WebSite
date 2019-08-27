package com.fei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication //一个注解顶下面3个
@MapperScan("com.fei.mapper")
@ServletComponentScan
public class StimuliApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StimuliApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StimuliApplication.class, args);
    }
    

}