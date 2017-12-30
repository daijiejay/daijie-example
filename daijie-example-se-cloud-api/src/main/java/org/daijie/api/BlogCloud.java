package org.daijie.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

import org.daijie.api.model.Blog;
import org.daijie.core.result.ModelResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(description="搜索引擎")
@FeignClient(value="${feign.se-cloud}")
public interface BlogCloud {
	
	@ApiOperation(notes = "查询所有文章", value = "查询所有文章")
	@RequestMapping(value="/searchBlogs", method=RequestMethod.GET)
	public ModelResult<Iterator<Blog>> getBlog();
	
	@RequestMapping(value="/searchBlog/{id}", method=RequestMethod.GET)
	public ModelResult<Blog> getBlog(@PathVariable Integer id);
	
	@RequestMapping(value="/addBlog", method=RequestMethod.POST)
	public ModelResult<Object> saveBlog(@RequestBody Blog blog);
}
