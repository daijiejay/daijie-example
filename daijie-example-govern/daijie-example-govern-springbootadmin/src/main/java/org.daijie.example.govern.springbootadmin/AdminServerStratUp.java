package org.daijie.example.govern.springbootadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableTurbine
@EnableAdminServer
@EnableEurekaClient
@EnableDiscoveryClient  
public class AdminServerStratUp {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerStratUp.class, args);
	}
}
