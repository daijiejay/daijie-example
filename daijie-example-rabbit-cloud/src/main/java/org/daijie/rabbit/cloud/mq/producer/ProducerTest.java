package org.daijie.rabbit.cloud.mq.producer;

import org.daijie.mybatis.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 * @author daijie
 * @date 2017年8月11日
 */
@Service
public class ProducerTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(){
		User user = new User();
		user.setUserName("admin");
		rabbitTemplate.convertAndSend("hello", user);
	}
}
