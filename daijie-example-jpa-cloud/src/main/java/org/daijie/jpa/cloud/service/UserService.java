package org.daijie.jpa.cloud.service;

import java.util.List;

import org.daijie.api.UserCloud;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jpa.cloud.dao.UserSearchRepository;
import org.daijie.jpa.cloud.service.base.BaseSearchService;
import org.daijie.mybatis.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService extends BaseSearchService<User, Integer, UserSearchRepository> implements UserCloud {

	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userId") Integer userId) {
		return Result.build(this.getById(userId));
	}

	@Override
	public ModelResult<List<User>> getUserAll() {
		return Result.build((List<User>) this.listAll());
	}

	@Override
	public ModelResult<User> getUser(@PathVariable(name = "userName") String userName) {
		return Result.build(getRepository().getUserByUserName(userName));
	}

	@Override
	@Transactional
	public ModelResult<Boolean> updateUser(User user) {
		this.save(user);
		return Result.build(true);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> addUser(User user) {
		this.save(user);
		return Result.build(true);
	}
}
