package org.daijie.api.workflow.feign;

import org.daijie.core.result.ModelResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文物备案流程案例
 * @author daijie_jay
 * @since 2018年1月9日
 */
@Api(description = "文物备案流程案例")
@FeignClient(value="${feign.workflow-cloud}")
public interface RelicCloud {

	/**
	 * 备案申请
	 * @param username
	 * @return
	 */
	@ApiOperation(value = "备案申请")
	@RequestMapping(value = "relic/apply", method = RequestMethod.POST)
	public ModelResult<Boolean> apply(
			@ApiParam(value = "备案人") @RequestParam(name = "username") String username);

	/**
	 * 支付
	 * @param pay
	 * @param auditor
	 * @return
	 */
	@ApiOperation(value = "支付")
	@RequestMapping(value = "relic/pay", method = RequestMethod.POST)
	public ModelResult<Boolean> pay(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "支付状态") @RequestParam(name = "pay") Integer pay, 
			@ApiParam(value = "初审人") String auditor);
	
	/**
	 * 初审
	 * @param trial
	 * @param reviewer
	 * @return
	 */
	@ApiOperation(value = "初审")
	@RequestMapping(value = "relic/trial", method = RequestMethod.POST)
	public ModelResult<Boolean> trial(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "初审状态") @RequestParam(name = "trial") Integer trial, 
			@ApiParam(value = "复审人") String[] reviewer);
	
	/**
	 * 重新提交
	 * @param auditor
	 * @return
	 */
	@ApiOperation(value = "重新提交")
	@RequestMapping(value = "relic/submit", method = RequestMethod.POST)
	public ModelResult<Boolean> submit(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "初审人") String auditor);
	
	/**
	 * 复审
	 * @param vote
	 * @param customerService
	 * @return
	 */
	@ApiOperation(value = "复审")
	@RequestMapping(value = "relic/review", method = RequestMethod.POST)
	public ModelResult<Boolean> review(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "投票") @RequestParam(name = "vote") Boolean vote, 
			@ApiParam(value = "客服") String customerService);
	
	/**
	 * 预约实物线下终审
	 * @param vote
	 * @param customerService
	 * @return
	 */
	@ApiOperation(value = "预约实物线下终审")
	@RequestMapping(value = "relic/appointment", method = RequestMethod.POST)
	public ModelResult<Boolean> appointment(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "是否预约成功") @RequestParam(name = "appointment") Integer appointment, 
			@ApiParam(value = "客服") String customerService);
	
	/**
	 * 客户到场确认
	 * @param arrive
	 * @param lastReviewer
	 * @return
	 */
	@ApiOperation(value = "客户到场确认")
	@RequestMapping(value = "relic/arrive", method = RequestMethod.POST)
	public ModelResult<Boolean> arrive(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "是否到场") @RequestParam(name = "arrive") Integer arrive, 
			@ApiParam(value = "终审人") String[] lastReviewer);
	
	/**
	 * 终审
	 * @param vote
	 * @param operator
	 * @return
	 */
	@ApiOperation(value = "终审")
	@RequestMapping(value = "relic/lastTrail", method = RequestMethod.POST)
	public ModelResult<Boolean> lastTrail(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "投票") @RequestParam(name = "vote") Boolean vote, 
			@ApiParam(value = "操作员") String operator);
	
	/**
	 * 备案入库
	 * @return
	 */
	@ApiOperation(value = "备案入库")
	@RequestMapping(value = "relic/record", method = RequestMethod.POST)
	public ModelResult<Boolean> record(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee);
}
