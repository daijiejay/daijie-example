package org.daijie.elasticsearch.cloud.service;

import java.util.Iterator;

import org.daijie.api.BlogCloud;
import org.daijie.api.model.Blog;
import org.daijie.core.factory.specific.ModelResultInitialFactory.Result;
import org.daijie.core.result.ModelResult;
import org.daijie.elasticsearch.cloud.dao.BlogSearchRepository;
import org.daijie.elasticsearch.cloud.service.base.BaseSearchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogService extends BaseSearchService<Blog, Integer, BlogSearchRepository> implements BlogCloud {

	@Override
	public ModelResult<Iterator<Blog>> getBlog(){
		return Result.build(this.listAll().iterator());
	}
	
	@Override
	public ModelResult<Blog> getBlog(@PathVariable Integer id){
		return Result.build(this.getById(id));
	}
	
	@Override
	public ModelResult<Object> saveBlog(@RequestBody Blog blog){
		this.save(blog);
		return Result.build();
	}
}
