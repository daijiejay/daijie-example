package org.daijie.oauth2.server;

import org.daijie.core.annotation.EnableParametersFilter;
import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.shiro.oauth2.annotation.EnableShiroOauth2SecurityServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableShiroOauth2SecurityServer
@EnableParametersFilter
@EnableMySwagger
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@RefreshScope
public class Oauth2StartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Oauth2StartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
