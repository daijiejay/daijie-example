package org.daijie.api.controller;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.PasswordUtil;
import org.daijie.core.util.encrypt.RSAUtil;
import org.daijie.data.feign.UserCloud;
import org.daijie.mybatis.model.User;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="用户注册")
public class RegisterController {
	
	@Autowired
	private UserCloud userCloud;

	@ApiOperation(notes = "注册", value = "注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelResult<Object> register(@RequestParam String username, @RequestParam String password) throws Exception{
		//公钥传给客户端
		String publicKey = Auth.getPublicKey();
		//客户端调用登录接口时进行公钥加密后传参调用此接口
		password = RSAUtil.encryptByPubKey(password, publicKey);

		User user = userCloud.getUser(username).getData();
		if(user != null){
			return Result.build("该账号已注册", ApiResult.SUCCESS, ResultCode.CODE_200);
		}
		user = new User();
		String salt = PasswordUtil.generateSalt();
		String saltPassword = PasswordUtil.generatePassword(password, salt.getBytes());
		user.setSalt(salt);
		user.setUserName(username);
		user.setPassword(saltPassword);
		boolean result = userCloud.addUser(user).getData();
		if(!result){
			return Result.build("注册失败", ApiResult.SUCCESS, ResultCode.CODE_200);
		}
		Auth.login(username, password, user.getSalt(), user.getPassword(), "user", user);
		return Result.build("注册并登录成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
}
