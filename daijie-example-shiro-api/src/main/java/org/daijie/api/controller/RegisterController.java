package org.daijie.api.controller;

import org.daijie.api.data.feign.UserCloud;
import org.daijie.api.data.feign.request.user.AddUserRequest;
import org.daijie.api.data.feign.response.user.AddUserResponse;
import org.daijie.api.data.feign.response.user.UserResponse;
import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.PasswordUtil;
import org.daijie.core.util.encrypt.RSAUtil;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(description="用户注册")
public class RegisterController {
	
	@Autowired
	private UserCloud userCloud;

	@ApiOperation(notes = "注册", value = "注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelResult<Object> register(
			@ApiParam(value="用户名") @RequestParam String username, 
			@ApiParam(value="密码") @RequestParam String password) throws Exception{
		//公钥传给客户端
		String publicKey = Auth.getPublicKey();
		//客户端调用登录接口时进行公钥加密后传参调用此接口
		String pubKeyPassword = RSAUtil.encryptByPubKey(password, publicKey);

		UserResponse user = userCloud.getUserByUsername(username).getData();
		if(user != null && user.getUserId() != null){
			return Result.build("该账号已注册", ApiResult.SUCCESS, ResultCode.CODE_200);
		}
		
		String salt = PasswordUtil.generateSalt();
		String saltPassword = PasswordUtil.generatePassword(password, salt.getBytes());
		
		AddUserRequest addUserRequest = new AddUserRequest();
		addUserRequest.setSalt(salt);
		addUserRequest.setUserName(username);
		addUserRequest.setPassword(saltPassword);
		AddUserResponse result = userCloud.addUser(addUserRequest).getData();
		if(!result.isStatus()){
			return Result.build("注册失败", ApiResult.SUCCESS, ResultCode.CODE_200);
		}
		user = userCloud.getUserByUsername(username).getData();
		Auth.login(username, pubKeyPassword, user.getSalt(), user.getPassword(), "user", user);
		return Result.build("注册并登录成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
}
