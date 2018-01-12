package org.daijie.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;

import org.daijie.api.UserCloud;
import org.daijie.core.controller.ApiController;
import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.RSAUtil;
import org.daijie.mybatis.model.User;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description="用户登录")
public class LoginController extends ApiController {
	
	@Autowired
	private UserCloud userCloud;

	@ApiOperation(notes = "登录", value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelResult<Object> login(@RequestParam String username, @RequestParam String password) throws Exception{
		//公钥传给客户端
		String publicKey = Auth.getPublicKey();
		//客户端调用登录接口时进行公钥加密后传参调用此接口
		password = RSAUtil.encryptByPubKey(password, publicKey);
		
		//以下正式走登录流程
		User user = userCloud.getUser(username).getData();
		Auth.login(username, password, user.getSalt(), user.getPassword(), user);
		//加入角色权限
		Auth.refreshRoles(new ArrayList<String>());
		ArrayList<String> permissions = new ArrayList<String>();
		permissions.add("USER");
		Auth.refreshPermissions(permissions);
		Auth.getPermissions();
		return Result.build("登录成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
	
	@ApiOperation(notes = "退出", value = "退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelResult<Object> logout(){
		Auth.logOut();
		return Result.build("退出成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}

	@RequestMapping(value = "/invalid", method = RequestMethod.GET)
	public ModelResult<Boolean> invalid(){
		return Result.build(null, "用户过期", ApiResult.ERROR, ResultCode.CODE_300);
	}
}
