package org.daijie.jpa.cloud.dao;

import org.daijie.jdbc.annotation.SelectDataSource;
import org.daijie.jdbc.jpa.repository.BaseSearchJpaRepository;
import org.daijie.mybatis.model.User;
import org.springframework.stereotype.Repository;

@Repository
@SelectDataSource(name = "demo1")
public interface UserRepository extends BaseSearchJpaRepository<User, Integer>{

	User getUserByUserName(String userName);
}
