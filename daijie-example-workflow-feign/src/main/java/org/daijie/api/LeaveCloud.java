package org.daijie.api;

import org.daijie.core.result.ModelResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 请假流程服务
 * @author daijie_jay
 * @since 2017年12月8日
 */
@Api(description = "请假流程案例")
@FeignClient(value="${feign.workflow-cloud}")
public interface LeaveCloud {

	/**
	 * 请假申请
	 * @param username
	 * @param days
	 * @param nextUserId
	 * @return
	 */
	@ApiOperation(value = "请假申请")
	@RequestMapping(value = "leave/apply", method = RequestMethod.POST)
	public ModelResult<Boolean> apply(
			@ApiParam(value = "请假人") @RequestParam(name = "username") String username, 
			@ApiParam(value = "请假天数") @RequestParam(name = "days") Integer days,
			@ApiParam(value = "下一个流程处理人ID") @RequestParam(name = "nextUserId") Integer nextUserId);
	
	/**
	 * 请假审批
	 * @param processInstanceId
	 * @param userId
	 * @param nextUserId
	 * @param checkStatus
	 * @return
	 */
	@ApiOperation(value = "请假审批")
	@RequestMapping(value = "leave/check", method = RequestMethod.POST)
	public ModelResult<Boolean> check(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人ID") @RequestParam(name = "userId") Integer userId, 
			@ApiParam(value = "下一个流程处理人ID") @RequestParam(name = "nextUserId") Integer nextUserId, 
			@ApiParam(value = "审批意见") @RequestParam(name = "checkStatus") Boolean checkStatus);
}
