package org.daijie.mybatis.mapper;

import org.daijie.mybatis.model.UserInfo;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserInfoMapper extends Mapper<UserInfo>, ConditionMapper<UserInfo>, MySqlMapper<UserInfo> {
}