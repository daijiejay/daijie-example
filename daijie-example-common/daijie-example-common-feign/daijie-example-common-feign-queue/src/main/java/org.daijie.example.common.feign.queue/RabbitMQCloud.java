package org.daijie.example.common.feign.queue;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="消息对列")
@FeignClient(value="${feign.services-special-rabbitmq}")
public interface RabbitMQCloud {
	
	@ApiOperation(notes = "rabbit消息对列测试", value = "rabbit消息对列测试")
	@RequestMapping(value = "/rabbit/send", method = RequestMethod.POST)
	public Object send();
}
