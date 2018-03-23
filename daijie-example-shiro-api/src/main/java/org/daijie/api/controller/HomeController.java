package org.daijie.api.controller;

import org.daijie.core.annotation.ErrorMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ErrorMapping(path = "home")
@Controller
public class HomeController {
	
	/**
	 * 测试异常时默认返回页面
	 * @return Object
	 */
	@RequestMapping(value = "page")
	public Object page(){
		int a = 1/0;
		System.out.println(a);
		return "page";
	}
	
	/**
	 * 测试异常时默认返回数据
	 * @return Object
	 */
	@RequestMapping(value = "body")
	@ResponseBody
	public Object body(){
		int a = 1/0;
		System.out.println(a);
		return "body";
	}

	@RequestMapping(value = "home")
	public String home(){
		return "home";
	}
}
