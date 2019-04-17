package org.daijie.quartz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
@RefreshScope
public class QuartzStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(QuartzStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
