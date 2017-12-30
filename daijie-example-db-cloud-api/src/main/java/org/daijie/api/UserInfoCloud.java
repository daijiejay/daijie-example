package org.daijie.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(description="用户信息管理")
@FeignClient(value="${feign.db-cloud}")
public interface UserInfoCloud {
	
	@ApiOperation(notes = "根据用户ID获取用户信息", value = "根据用户ID获取用户信息")
	@RequestMapping(value = "/userinfo/{userId}", method = RequestMethod.GET)
	public ModelResult<UserInfo> getUserinfo(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(notes = "获取全部用户信息", value = "获取全部用户信息")
	@RequestMapping(value = "/userinfo/all", method = RequestMethod.GET)
	public ModelResult<List<UserInfo>> getUserinfoAll();
	
	@ApiOperation(notes = "根据手机号获取用户信息", value = "根据手机号获取用户信息")
	@RequestMapping(value = "/userinfo/mobile/{mobile}", method = RequestMethod.GET)
	public ModelResult<UserInfo> getUserinfo(@PathVariable(name = "mobile") String mobile);
	
	@ApiOperation(notes = "修改用户信息", value = "修改用户信息")
	@RequestMapping(value = "/userinfo", method = RequestMethod.PUT)
	public ModelResult<Boolean> updateUserinfo(@RequestBody UserInfo userInfo);
	
	@ApiOperation(notes = "添加用户信息", value = "添加用户信息")
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST)
	public ModelResult<Boolean> addUserinfo(@RequestBody UserInfo userInfo);
}
