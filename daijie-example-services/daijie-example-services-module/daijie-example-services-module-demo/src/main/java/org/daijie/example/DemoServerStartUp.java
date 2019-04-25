package org.daijie.example;

import org.daijie.core.annotation.EnableParametersFilter;
import org.daijie.core.controller.EnableExceptionHandler;
import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.jdbc.mybatis.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableParametersFilter
@EnableExceptionHandler
@EnableMySwagger
@EnableMybatis(basePackages = {"org.daijie.example.common.model.demo.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class DemoServerStartUp {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoServerStartUp.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
