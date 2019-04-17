package org.dijie.example.feign.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.dijie.example.feign.demo.request.AddUserRequest;
import org.dijie.example.feign.demo.request.UpdateUserRequest;
import org.dijie.example.feign.demo.request.UserRequest;
import org.dijie.example.feign.demo.response.AddUserResponse;
import org.dijie.example.feign.demo.response.UpdateUserResponse;
import org.dijie.example.feign.demo.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(description="用户管理")
@FeignClient(value="${feign.services-module-demo}")
public interface UserCloud {
	
	@ApiOperation(notes = "根据用户ID获取用户信息", value = "根据用户ID获取用户信息")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public ModelResult<UserResponse> getUserById(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(notes = "获取全部用户信息", value = "获取全部用户信息")
	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public ModelResult<PageResult<UserResponse>> getUserAll(UserRequest userRequest);
	
	@ApiOperation(notes = "根据用户名获取用户信息", value = "根据用户名获取用户信息")
	@RequestMapping(value = "/user/username/{userName}", method = RequestMethod.GET)
	public ModelResult<UserResponse> getUserByUsername(@PathVariable(name = "userName") String userName);
	
	@ApiOperation(notes = "修改用户", value = "修改用户")
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ModelResult<UpdateUserResponse> updateUser(UpdateUserRequest userRequest);
	
	@ApiOperation(notes = "添加用户", value = "添加用户")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelResult<AddUserResponse> addUser(AddUserRequest userRequest);
}
