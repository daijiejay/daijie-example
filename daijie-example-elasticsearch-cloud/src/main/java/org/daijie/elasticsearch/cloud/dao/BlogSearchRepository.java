package org.daijie.elasticsearch.cloud.dao;

import org.daijie.api.model.Blog;
import org.daijie.elasticsearch.cloud.service.base.BaseSearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface BlogSearchRepository extends BaseSearchRepository<Blog, Integer>{

}
