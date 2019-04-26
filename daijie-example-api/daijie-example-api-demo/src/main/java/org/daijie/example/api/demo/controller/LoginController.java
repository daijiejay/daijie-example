package org.daijie.example.api.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.RSAUtil;
import org.daijie.shiro.authc.Auth;
import org.daijie.example.feign.demo.UserCloud;
import org.daijie.example.feign.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@Api(description="用户登录")
public class LoginController {
	
	@Autowired
	private UserCloud userCloud;

	@ApiOperation(notes = "登录", value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelResult<Object> login(
			@ApiParam(value="用户名") @RequestParam String username, 
			@ApiParam(value="密码") @RequestParam String password) throws Exception{
		//公钥传给客户端
		String publicKey = Auth.getPublicKey();
		//客户端调用登录接口时进行公钥加密后传参调用此接口
		password = RSAUtil.encryptByPubKey(password, publicKey);
		
		//以下正式走登录流程
		UserResponse user = userCloud.getUserByUsername(username).getData();
		Auth.login(username, password, user.getSalt(), user.getPassword(), user);
		//加入角色权限
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("USER");
		roles.add("ADMIN");
		Auth.refreshRoles(roles);
		ArrayList<String> permissions = new ArrayList<String>();
		permissions.add("SELECT");
		permissions.add("UPDATE");
		Auth.refreshPermissions(permissions);
		return Result.build("登录成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
	
	@ApiOperation(notes = "退出", value = "退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelResult<Object> logout(){
		Auth.logOut();
		return Result.build("退出成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
}
