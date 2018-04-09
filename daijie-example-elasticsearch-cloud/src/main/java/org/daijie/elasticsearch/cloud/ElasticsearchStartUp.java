package org.daijie.elasticsearch.cloud;

import org.daijie.core.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableMySwagger
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class ElasticsearchStartUp {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchStartUp.class, args);
	}
}
