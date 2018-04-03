package org.daijie.social.cloud;

import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.social.captcha.EnableSocialCaptcha;
import org.daijie.social.login.annotation.EnableSocialLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableSocialCaptcha
@EnableSocialLogin
@EnableMySwagger
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class SocialStartUp {

	public static void main(String[] args) {
		SpringApplication.run(SocialStartUp.class, args);
	}
}
