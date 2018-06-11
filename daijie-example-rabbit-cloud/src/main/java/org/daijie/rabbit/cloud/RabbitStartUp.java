package org.daijie.rabbit.cloud;

import org.daijie.core.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableMySwagger
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@RefreshScope
public class RabbitStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(RabbitStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
