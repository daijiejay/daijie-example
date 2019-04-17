package org.daijie.example.gate.server;

import org.daijie.shiro.security.annotation.EnableShiroSecurityServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableShiroSecurityServer
@SpringBootApplication
public class GateServerStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(GateServerStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
