package org.daijie.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.daijie.api.UserCloud;
import org.daijie.core.controller.ApiController;
import org.daijie.core.result.ModelResult;
import org.daijie.mybatis.model.User;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description="用户管理")
@RestController
@RequestMapping(value = "user")
public class UserController extends ApiController {
	
	@Autowired
	private UserCloud userCloud;
	
	@ApiOperation(notes = "获取当前登录用户信息", value = "获取当前登录用户信息")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelResult<User> getUser(){
		User user = Auth.getAuthc(User.class);
		return userCloud.getUser(user.getUserId());
	}

	@ApiOperation(notes = "修改登录密码", value = "修改登录密码")
	@RequestMapping(value = "/modify/password", method = RequestMethod.POST)
	public ModelResult<Boolean> modifyPassword(@RequestParam(name = "password") String password){
		User user = Auth.getAuthc(User.class);
		user.setPassword(password);
		return userCloud.updateUser(user);
	}
}
