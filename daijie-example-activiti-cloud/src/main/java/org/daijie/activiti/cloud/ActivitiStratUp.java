package org.daijie.activiti.cloud;

import org.daijie.core.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableMySwagger
public class ActivitiStratUp {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiStratUp.class, args);
	}
}
