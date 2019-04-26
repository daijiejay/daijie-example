package org.daijie.example.api.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.daijie.core.result.ModelResult;
import org.daijie.core.util.encrypt.PasswordUtil;
import org.daijie.core.util.encrypt.RSAUtil;
import org.daijie.shiro.authc.Auth;
import org.daijie.example.feign.demo.UserCloud;
import org.daijie.example.feign.demo.request.UpdateUserRequest;
import org.daijie.example.feign.demo.response.UpdateUserResponse;
import org.daijie.example.feign.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description="用户管理")
@RestController
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserCloud userCloud;
	
	@ApiOperation(notes = "获取当前登录用户信息", value = "获取当前登录用户信息")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelResult<UserResponse> getUser(){
		UserResponse user = Auth.getAuthc(UserResponse.class);
		return userCloud.getUserById(user.getUserId());
	}

	@ApiOperation(notes = "修改登录密码", value = "修改登录密码")
	@RequestMapping(value = "/modify/password", method = RequestMethod.POST)
	public ModelResult<UpdateUserResponse> modifyPassword(@ApiParam(value="密码") @RequestParam(name = "password") String password) throws Exception{
		//公钥传给客户端
		String publicKey = Auth.getPublicKey();
		//客户端调用接口时进行公钥加密后传参调用此接口
		password = RSAUtil.encryptByPubKey(password, publicKey);
		
		String salt = PasswordUtil.generateSalt();
		String saltPassword = PasswordUtil.generatePassword(password, salt.getBytes());
		
		UserResponse user = Auth.getAuthc(UserResponse.class);
		user.setSalt(salt);
		user.setPassword(saltPassword);
		
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		BeanUtil.copyProperties(user, updateUserRequest);
		updateUserRequest.setSalt(salt);
		updateUserRequest.setPassword(saltPassword);
		return userCloud.updateUser(updateUserRequest);
	}
}
