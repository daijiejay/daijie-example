package org.daijie.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.daijie.core.lock.Callback;
import org.daijie.core.lock.Lock;
import org.daijie.core.lock.LockTool;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式锁测试
 * @author daijie_jay
 * @since 2017年11月24日
 */
@Api(description="分布式锁测试")
@RestController
public class LockController {
	
	private static final Logger logger = LoggerFactory.getLogger(LockController.class);

	@ApiOperation(notes = "锁测试", value = "锁测试")
	@RequestMapping(value = "testLock", method = RequestMethod.GET)
	public ModelResult<Object> testLock(){
		Object result = LockTool.execute("test", 5000, new Callback() {
			
			@Override
			public Object onTimeout() throws InterruptedException {
				logger.info("锁占用业务处理");
				return 0;
			}
			
			@Override
			public Object onGetLock() throws InterruptedException {
				logger.info("获取锁业务处理");
				Thread.sleep(5000);
				return 1;
			}
			
			@Override
			public Object onError(Exception exception){
				logger.info("锁异常业务处理");
		    	return 0;
		    }
		});
		return Result.build(result);
	}
	
	@Lock(argName = "id", 
			timeout = 5000,
			errorMethodName = "org.daijie.api.controller.LockController.lockError(java.lang.String)", 
			timeOutMethodName = "org.daijie.api.controller.LockController.lockTimeOut(java.lang.String)")
	@ApiOperation(notes = "锁注解测试", value = "锁注解测试")
	@RequestMapping(value = "testLockAnnotation", method = RequestMethod.GET)
	public ModelResult<Object> testLockAnnotation(@ApiParam(value="业务编号") @RequestParam String id){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Result.build("业务"+id+"锁已获取，获取锁业务处理");
	}
	
	public String lockTimeOut(String id){
		return "业务"+id+"锁已被占用，锁占用业务处理";
	}
	
	public String lockError(String id){
		return "业务"+id+"锁获取失败，锁异常业务处理";
	}
}
