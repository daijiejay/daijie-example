package org.daijie.jpa.cloud.service;

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
import org.daijie.jpa.cloud.dao.UserRepository;
import org.daijie.mybatis.model.User;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoleilu.hutool.bean.BeanUtil;

@RestController
public class UserService implements UserCloud {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	public ModelResult<UserResponse> getUserById(@PathVariable(name = "userId") Integer userId) {
		UserResponse userResponse = new UserResponse();
		BeanUtil.copyProperties(userRepository.getOne(userId), userResponse);
		return Result.build(userResponse);
	}

	@Override
	public ModelResult<PageResult<UserResponse>> getUserAll(UserRequest userRequest) {
		Pageable pageable = PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize());
		Page<User> page = userRepository.findAll(pageable);
		return Result.build(new PageResult<UserResponse>(page.getContent(), page.getTotalElements(), UserResponse.class));
	}

	@Override
	public ModelResult<UserResponse> getUserByUsername(@PathVariable(name = "userName") String userName) {
		UserResponse userResponse = new UserResponse();
		BeanUtil.copyProperties(userRepository.getUserByUserName(userName), userResponse);
		return Result.build(userResponse);
	}

	@Override
	@Transactional
	public ModelResult<UpdateUserResponse> updateUser(UpdateUserRequest userRequest) {
		User user = new User();
		BeanUtil.copyProperties(userRequest, user);
		userRepository.save(user);
		UserInfo userInfo = userInfoService.getUserinfo(user.getUserId()).getData();
		userInfo.setRealname(user.getUserName());
		userInfoService.updateUserinfo(userInfo);
//		int a = 3/0;
		return Result.build(new UpdateUserResponse(true));
	}

	@Override
	@Transactional
	public ModelResult<AddUserResponse> addUser(AddUserRequest userRequest) {
		User user = new User();
		BeanUtil.copyProperties(userRequest, user);
		userRepository.save(user);
		return Result.build(new AddUserResponse(true));
	}
}
