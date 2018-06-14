package org.daijie.api.controller;

import org.daijie.core.annotation.ErrorMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="请求系统异常默认返回测试")
@ErrorMapping(path = "error")
@Controller
public class HomeController {
	
	/**
	 * 测试异常时默认跳转页面
	 * @return Object
	 */
	@ApiOperation(notes = "测试异常时默认跳转页面", value = "测试异常时默认跳转页面")
	@RequestMapping(value = "page")
	public Object page(){
		int a = 1/0;
		System.out.println(a);
		return "index";
	}
	
	/**
	 * 测试异常时默认返回数据
	 * @return Object
	 */
	@ApiOperation(notes = "测试异常时默认返回数据", value = "测试异常时默认返回数据")
	@RequestMapping(value = "body")
	@ResponseBody
	public Object body(){
		int a = 1/0;
		System.out.println(a);
		return "body";
	}
}
