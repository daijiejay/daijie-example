package org.daijie.example.common.model.demo.mapper;

import org.daijie.example.common.model.demo.entity.UserInfo;
import org.daijie.jdbc.annotation.SelectDataSource;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@SelectDataSource(name = "demo2")
public interface UserInfoMapper extends Mapper<UserInfo>, ConditionMapper<UserInfo>, MySqlMapper<UserInfo> {
}