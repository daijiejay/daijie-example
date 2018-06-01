package org.daijie.activiti.cloud;

import org.daijie.activiti.EnableActiviti;
import org.daijie.core.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableMySwagger
@EnableActiviti
public class ActivitiStratUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ActivitiStratUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
