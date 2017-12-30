package org.daijie.oauth2.server;

import org.daijie.core.annotation.EnableParametersFilter;
import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.shiro.oauth2.annotation.EnableShiroOauth2SecurityServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableShiroOauth2SecurityServer
@EnableParametersFilter
@EnableMySwagger
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@RefreshScope
public class BootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BootApplication.class).web(true).run(args);
	}
}
