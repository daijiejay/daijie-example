package org.daijie.jpa.cloud.dao;

import org.daijie.mybatis.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSearchRepository extends PagingAndSortingRepository<User, Integer>{

	User getUserByUserName(String userName);
}
