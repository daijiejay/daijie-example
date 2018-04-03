package org.daijie.api;

import java.util.Map;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author daijie_jay
 * @since 2017年12月8日
 */
@Api(description = "流程管理")
@FeignClient(value="${feign.workflow-cloud}")
public interface BaseCloud {

	/**
	 * 查询所有流程
	 * @return
	 */
	@ApiOperation(value = "查询所有流程")
	@RequestMapping(value = "flow/processes", method = RequestMethod.GET)
	public ModelResult<PageResult<Map<String, Object>>> getProcesses();
	
	/**
	 * 根据流程ID查询流程
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据流程ID查询流程")
	@RequestMapping(value = "flow/process/{id}", method = RequestMethod.GET)
	public ModelResult<PageResult<Map<String, Object>>> getProcessById(@PathVariable(name = "id") Integer id);
	
	/**
	 * 根据处理人查询所有流程
	 * @param operatorId
	 * @return
	 */
	@ApiOperation(value = "根据处理人查询所有流程")
	@RequestMapping(value = "flow/process/operator/{assignee}", method = RequestMethod.GET)
	public ModelResult<PageResult<Map<String, Object>>> getProcessByOperator(@PathVariable(name = "assignee") String assignee);
}
