package org.daijie.jpa.cloud.dao;

import org.daijie.jdbc.annotation.SelectDataSource;
import org.daijie.jdbc.jpa.repository.BaseSearchJpaRepository;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
@SelectDataSource(name = "demo2")
public interface UserInfoRepository extends BaseSearchJpaRepository<UserInfo, Integer>{
	
	UserInfo getUserByMobile(String mobile);
}
