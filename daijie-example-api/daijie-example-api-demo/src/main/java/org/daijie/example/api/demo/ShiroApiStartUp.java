package org.daijie.example.api.demo;

import org.daijie.core.controller.EnableExceptionHandler;
import org.daijie.core.lock.redis.EnableRedisLock;
import org.daijie.shiro.annotation.EnableShiro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFastdfs
//@EnableHdfs
@EnableShiro
@EnableRedisLock
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableExceptionHandler
@RefreshScope
public class ShiroApiStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ShiroApiStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
