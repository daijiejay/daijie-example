package org.daijie.mybatis.shardingjdbc.cloud.service;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.data.feign.UserCloud;
import org.daijie.mybatis.mapper.UserMapper;
import org.daijie.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService implements UserCloud {
	
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
	public ModelResult<Boolean> updateUser(User user) {
		return Result.build(userMapper.updateByPrimaryKey(user) > 0);
	}

	@Override
	public ModelResult<Boolean> addUser(User user) {
		return Result.build(userMapper.insertSelective(user) > 0);
	}

}
