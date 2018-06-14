package org.daijie.jpa.cloud.service;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.data.feign.UserCloud;
import org.daijie.jpa.cloud.dao.UserRepository;
import org.daijie.mybatis.model.User;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService implements UserCloud {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userId") Integer userId) {
		return Result.build(userRepository.getOne(userId));
	}

	@Override
	public ModelResult<List<User>> getUserAll() {
		return Result.build((List<User>) userRepository.findAll());
	}

	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userName") String userName) {
		return Result.build(userRepository.getUserByUserName(userName));
	}

	@Override
	@Transactional
	public ModelResult<Boolean> updateUser(User user) {
		userRepository.save(user);
		UserInfo userInfo = userInfoService.getUserinfo(user.getUserId()).getData();
		userInfo.setRealname(user.getUserName());
		userInfoService.updateUserinfo(userInfo);
//		int a = 3/0;
		return Result.build(true);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> addUser(User user) {
		userRepository.save(user);
		return Result.build(true);
	}
}
