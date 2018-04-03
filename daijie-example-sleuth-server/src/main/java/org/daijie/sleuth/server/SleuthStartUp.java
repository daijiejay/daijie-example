package org.daijie.sleuth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
@RefreshScope
public class SleuthStartUp {

	public static void main(String[] args) {
		SpringApplication.run(SleuthStartUp.class, args);
	}
}
