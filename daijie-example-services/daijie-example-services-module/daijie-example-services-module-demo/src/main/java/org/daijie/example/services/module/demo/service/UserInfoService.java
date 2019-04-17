package org.daijie.example.services.module.demo.service;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.example.common.model.demo.entity.UserInfo;
import org.daijie.example.common.model.demo.mapper.UserInfoMapper;
import org.dijie.example.feign.demo.UserInfoCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoService implements UserInfoCloud {
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public ModelResult<UserInfo> getUserinfo(Integer userId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		return Result.build(userInfo);
	}

	@Override
	public ModelResult<List<UserInfo>> getUserinfoAll() {
		return Result.build(userInfoMapper.selectAll());
	}

	@Override
	public ModelResult<UserInfo> getUserinfo(String mobile) {
		UserInfo userinfo = new UserInfo();
		userinfo.setMobile(mobile);
		return Result.build(userInfoMapper.selectOne(userinfo));
	}

	@Override
	public ModelResult<Boolean> updateUserinfo(UserInfo userInfo) {
		return Result.build(userInfoMapper.updateByPrimaryKey(userInfo) > 0);
	}

	@Override
	public ModelResult<Boolean> addUserinfo(UserInfo userInfo) {
		return Result.build(userInfoMapper.insert(userInfo) > 0);
	}

}
