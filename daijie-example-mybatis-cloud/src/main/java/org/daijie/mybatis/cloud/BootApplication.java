package org.daijie.mybatis.cloud;

import org.daijie.core.controller.EnableExceptionHandler;
import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.jdbc.annotation.EnableMybatis;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableExceptionHandler
@EnableMySwagger
@EnableMybatis(basePackages = {"org.daijie.mybatis.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class BootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BootApplication.class).web(true).run(args);
	}
}
