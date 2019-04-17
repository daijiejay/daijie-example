package org.daijie.activiti.cloud.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.daijie.api.workflow.feign.LeaveCloud;
import org.daijie.api.workflow.feign.enums.LeaveStatus;
import org.daijie.core.process.Process;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
public class LeaveService implements LeaveCloud {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;

	@Override
	public ModelResult<Boolean> apply(
			@ApiParam(value = "请假人") @RequestParam(name = "username") String username, 
			@ApiParam(value = "请假天数") @RequestParam(name = "days") Integer days,
			@ApiParam(value = "下一个流程处理人ID") @RequestParam(name = "nextUserId") Integer nextUserId) {
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("username", username);
        variables.put("days", days);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", variables);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
				.singleResult();
		variables = new HashMap<String, Object>();
		//设置下一流程审批人ID
        variables.put(LeaveStatus.PROJECT_LEADER.getAssignee(), nextUserId);
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> check(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人ID") @RequestParam(name = "userId") Integer userId, 
			@ApiParam(value = "下一个流程处理人ID") @RequestParam(name = "nextUserId") Integer nextUserId, 
			@ApiParam(value = "审批意见") @RequestParam(name = "checkStatus") Boolean checkStatus) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(userId.toString())
				.singleResult();
		if(task == null){
			return Result.build("没有需要审批的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("checkStatus", checkStatus);
        //设置下一流程审批人ID
        LeaveStatus leaveStatus = LeaveStatus.valueOf(task.getTaskDefinitionKey());
        if(leaveStatus.nextProcess(Process.THROUGH) != null){
        	if(LeaveStatus.PROJECT_LEADER.equals(leaveStatus) || LeaveStatus.PROJECT_MANAGER.equals(leaveStatus)){
        		variables.put(leaveStatus.nextProcess(Process.THROUGH).getAssignee(), nextUserId);
        	}
        }
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

}
