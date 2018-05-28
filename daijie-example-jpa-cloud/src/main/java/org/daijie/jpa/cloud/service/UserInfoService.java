package org.daijie.jpa.cloud.service;

import java.util.List;

import org.daijie.api.UserInfoCloud;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jpa.cloud.dao.UserInfoRepository;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoService implements UserInfoCloud {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public ModelResult<UserInfo> getUserinfo(@PathVariable(name = "userId") Integer userId) {
		return Result.build(userInfoRepository.getOne(userId));
	}

	@Override
	public ModelResult<List<UserInfo>> getUserinfoAll() {
		return Result.build((List<UserInfo>) userInfoRepository.findAll());
	}

	@Override
	public ModelResult<UserInfo> getUserinfo(String mobile) {
		return Result.build(userInfoRepository.getUserByMobile(mobile));
	}

	@Override
	@Transactional
	public ModelResult<Boolean> updateUserinfo(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
		return Result.build(true);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> addUserinfo(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
		return Result.build(true);
	}
}
