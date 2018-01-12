package org.daijie.activiti.cloud.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.daijie.api.LeaveCloud;
import org.daijie.api.enums.LeaveStatus;
import org.daijie.core.controller.ApiController;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveService extends ApiController implements LeaveCloud {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ModelResult<Boolean> apply(String username, Integer days, Integer nextUserId) {
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
	public ModelResult<Boolean> check(String processInstanceId, Integer userId, Integer nextUserId,
			Boolean checkStatus) {
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
        if(leaveStatus.nextProcess() != null){
        	if(LeaveStatus.PROJECT_LEADER.equals(leaveStatus) || LeaveStatus.PROJECT_MANAGER.equals(leaveStatus)){
        		variables.put(leaveStatus.nextProcess().getAssignee(), nextUserId);
        	}
        }
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

}
