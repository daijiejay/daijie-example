package org.daijie.mybatis.cloud.service;

import java.util.List;

import org.daijie.api.UserCloud;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.mybatis.mapper.UserMapper;
import org.daijie.mybatis.model.User;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService implements UserCloud {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userId") Integer userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return Result.build(user);
	}

	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userName") String userName) {
		User user = new User();
		user.setUserName(userName);
		return Result.build(userMapper.selectOne(user));
	}

	@Override
	public ModelResult<List<User>> getUserAll() {
		return Result.build(userMapper.selectAll());
	}

	@Override
	@Transactional
	public ModelResult<Boolean> updateUser(User user) {
		if(userMapper.updateByPrimaryKey(user) > 0){
			UserInfo userInfo = userInfoService.getUserinfo(user.getUserId()).getData();
			userInfo.setRealname(user.getUserName());
			userInfoService.updateUserinfo(userInfo);
//			int a = 3/0;
			return Result.build(true);
		}
		return Result.build(false);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> addUser(User user) {
		return Result.build(userMapper.insert(user) > 0);
	}
}
