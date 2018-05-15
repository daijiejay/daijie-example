package org.daijie.mybatis.mapper;

import org.daijie.jdbc.annotation.SelectDataSource;
import org.daijie.mybatis.model.User;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@SelectDataSource(name = "demo1")
public interface UserMapper extends Mapper<User>, ConditionMapper<User>, MySqlMapper<User> {
}