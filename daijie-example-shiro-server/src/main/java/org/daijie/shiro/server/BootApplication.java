package org.daijie.shiro.server;

import org.daijie.shiro.security.annotation.EnableShiroSecurityServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableShiroSecurityServer
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BootApplication.class).web(true).run(args);
	}
}
