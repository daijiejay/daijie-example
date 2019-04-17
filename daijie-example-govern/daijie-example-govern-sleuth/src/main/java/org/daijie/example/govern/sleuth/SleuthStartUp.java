package org.daijie.example.govern.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
@RefreshScope
public class SleuthStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SleuthStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
