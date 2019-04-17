package org.daijie.rabbit.cloud.mq.consumer;

import org.daijie.core.util.SerializeUtil;
import org.daijie.mybatis.model.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author daijie
 * @since 2017年8月11日
 */
@Component
@EnableScheduling
public class ConsumerTest {

	@RabbitHandler
	@RabbitListener(queues = "hello") 
	public void process(Message message){
		byte[] body = message.getBody();
		User user = (User) SerializeUtil.deserialize(body);
		System.out.println(user.getUserName());
	}
}
