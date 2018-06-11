package org.daijie.mybatis.shardingjdbc.cloud;

import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.jdbc.annotation.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableMybatis(basePackages = {"org.daijie.mybatis.mapper"})
@EnableMySwagger
@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class ShardingjdbcStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ShardingjdbcStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
