package org.daijie.mybatis.cloud.service;

import java.util.List;

import org.daijie.api.data.feign.UserCloud;
import org.daijie.api.data.feign.request.user.AddUserRequest;
import org.daijie.api.data.feign.request.user.UpdateUserRequest;
import org.daijie.api.data.feign.request.user.UserRequest;
import org.daijie.api.data.feign.response.user.AddUserResponse;
import org.daijie.api.data.feign.response.user.UpdateUserResponse;
import org.daijie.api.data.feign.response.user.UserResponse;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.mybatis.mapper.UserMapper;
import org.daijie.mybatis.model.User;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class UserService implements UserCloud {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public ModelResult<UserResponse> getUserById(@PathVariable(name = "userId") Integer userId) {
		UserResponse userResponse = new UserResponse();
		BeanUtil.copyProperties(userMapper.selectByPrimaryKey(userId), userResponse);
		return Result.build(userResponse);
	}

	@Override
	public ModelResult<UserResponse> getUserByUsername(@PathVariable(name = "userName") String userName) {
		User user = new User();
		user.setUserName(userName);
		UserResponse userResponse = new UserResponse();
		BeanUtil.copyProperties(userMapper.selectOne(user), userResponse);
		return Result.build(userResponse);
	}

	@Override
	public ModelResult<PageResult<UserResponse>> getUserAll(UserRequest userRequest) {
		PageHelper.startPage(userRequest.getPageNumber(), userRequest.getPageSize());
		User user = new User();
		BeanUtil.copyProperties(userRequest, user);
		List<User> users = userMapper.select(user);
        PageInfo<User> pageInfo = new PageInfo<>(users);
		return Result.build(new PageResult<UserResponse>(pageInfo.getList(), pageInfo.getTotal(), UserResponse.class));
	}

	@Override
	@Transactional
	public ModelResult<UpdateUserResponse> updateUser(UpdateUserRequest userRequest) {
		User user = new User();
		BeanUtil.copyProperties(userRequest, user);
		if(userMapper.updateByPrimaryKey(user) > 0){
			UserInfo userInfo = userInfoService.getUserinfo(user.getUserId()).getData();
			userInfo.setRealname(user.getUserName());
			userInfoService.updateUserinfo(userInfo);
//			int a = 3/0;
			return Result.build(new UpdateUserResponse(true));
		}
		return Result.build(new UpdateUserResponse(false));
	}

	@Override
	@Transactional
	public ModelResult<AddUserResponse> addUser(AddUserRequest userRequest) {
		User user = new User();
		BeanUtil.copyProperties(userRequest, user);
		return Result.build(new AddUserResponse(userMapper.insert(user) > 0));
	}
}
