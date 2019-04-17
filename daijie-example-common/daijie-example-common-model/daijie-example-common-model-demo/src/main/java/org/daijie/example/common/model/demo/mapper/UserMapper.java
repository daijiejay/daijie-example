package org.daijie.example.common.model.demo.mapper;

import org.daijie.example.common.model.demo.entity.User;
import org.daijie.jdbc.annotation.SelectDataSource;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@SelectDataSource(name = "demo1")
public interface UserMapper extends Mapper<User>, ConditionMapper<User>, MySqlMapper<User> {
}