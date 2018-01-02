package org.daijie.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.daijie.core.factory.specific.ModelResultInitialFactory.Result;
import org.daijie.core.result.ModelResult;
import org.daijie.shiro.session.ShiroRedisSession.Redis;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于shiro session redis测试
 * @author daijie
 * @since 2017年6月22日
 */
@Api(description="缓存测试")
@RestController
public class RedisSessionController {
	
	/**
	 * 设置redis
	 * @param key
	 * @param value
	 * @return
	 */
	@ApiOperation(notes = "存放缓存", value = "存放缓存")
	@RequestMapping(value = "/setRedis", method = RequestMethod.GET)
	public ModelResult<Object> setRedis(String key, String value){
		Redis.set(key, value);
		return Result.build();
	}
	
	/**
	 * 获取redis
	 * @param key
	 * @return
	 */
	@ApiOperation(notes = "获取缓存", value = "获取缓存")
	@RequestMapping(value = "/getRedis", method = RequestMethod.GET)
	public ModelResult<Object> getRedis(String key){
		return Result.build(Redis.get(key));
	}
}
