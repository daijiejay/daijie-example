package org.daijie.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.log4j.Logger;
import org.daijie.core.factory.specific.ModelResultInitialFactory.Result;
import org.daijie.core.lock.Callback;
import org.daijie.core.lock.LockTool;
import org.daijie.core.result.ModelResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式锁测试
 * @author daijie_jay
 * @date 2017年11月24日
 */
@Api(description="分布式锁测试")
@RestController
public class LockController {
	
	private static final Logger logger = Logger.getLogger(LockController.class);

	@ApiOperation(notes = "锁测试", value = "锁测试")
	@RequestMapping(value = "testLock", method = RequestMethod.GET)
	public ModelResult<Object> testLock(){
		Object result = LockTool.execute("test", 1000, new Callback() {
			
			@Override
			public Object onTimeout() throws InterruptedException {
				logger.info("锁超时业务处理");
				return 0;
			}
			
			@Override
			public Object onGetLock() throws InterruptedException {
				logger.info("获取锁业务处理");
				return 1;
			}
		});
		return Result.build(result);
	}
}
