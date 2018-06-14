package org.daijie.activiti.cloud.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.daijie.core.process.Process;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.workflow.feign.RelicCloud;
import org.daijie.workflow.feign.enums.RelicStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
public class RelicService implements RelicCloud {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;

	@Override
	public ModelResult<Boolean> apply(
			@ApiParam(value = "备案人") @RequestParam(name = "username") String username) {
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("username", username);
		runtimeService.startProcessInstanceByKey("relic", variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> pay(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "支付状态") @RequestParam(name = "pay") Integer pay, 
			@ApiParam(value = "初审人") String auditor) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("pay", pay);
        variables.put("repeat", "1");
        RelicStatus relicStatus = RelicStatus.valueOf(task.getTaskDefinitionKey());
        if(relicStatus.nextProcess(Process.THROUGH) != null){
        	variables.put(relicStatus.nextProcess(Process.THROUGH).getAssignee(), auditor);
        }
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> trial(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "初审状态") @RequestParam(name = "trial") Integer trial, 
			@ApiParam(value = "复审人") String[] reviewer) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}

		Map<String, Object> variables = new HashMap<String, Object>();
		Object obj = runtimeService.getVariable(task.getExecutionId(), "repeat");
		Integer repeat = obj == null ? 0 : Integer.parseInt(obj.toString());
		variables.put("repeat", repeat++);
		runtimeService.setVariables(task.getExecutionId(), variables);
        variables.put("trial", trial);
        variables.put("assginees", Arrays.asList(reviewer));
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> submit(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "初审人") String auditor) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
        RelicStatus relicStatus = RelicStatus.valueOf(task.getTaskDefinitionKey());
        if(relicStatus.nextProcess(Process.THROUGH) != null){
        	variables.put(relicStatus.nextProcess(Process.THROUGH).getAssignee(), auditor);
        }
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> review(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "投票") @RequestParam(name = "vote") Boolean vote, 
			@ApiParam(value = "客服") String customerService) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		Object obj = runtimeService.getVariable(task.getExecutionId(), "reviewVotes");
		Integer votes = obj == null ? 0 : (Integer) obj;
		if(vote){
			variables.put("reviewVotes", votes + 1);
		}
		runtimeService.setVariables(task.getExecutionId(), variables);
		variables.put(assignee, vote);
		Integer nrOfCompletedInstances = (Integer) runtimeService.getVariable(task.getExecutionId(), "nrOfCompletedInstances");
		if(nrOfCompletedInstances >= 2){
			RelicStatus relicStatus = RelicStatus.valueOf(task.getTaskDefinitionKey());
			if(relicStatus.nextProcess(Process.THROUGH) != null){
				variables.put(relicStatus.nextProcess(Process.THROUGH).getAssignee(), customerService);
			}
		}
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> appointment(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "是否预约成功") @RequestParam(name = "appointment") Integer appointment, 
			@ApiParam(value = "客服") String customerService) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("appointment", appointment);
        variables.put("customerService", customerService);
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> arrive(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "是否到场") @RequestParam(name = "arrive") Integer arrive, 
			@ApiParam(value = "终审人") String[] lastReviewer) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("arrive", arrive);
        variables.put("assginees", Arrays.asList(lastReviewer));
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> lastTrail(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee, 
			@ApiParam(value = "投票") @RequestParam(name = "vote") Boolean vote, 
			@ApiParam(value = "操作员") String operator) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}

		Map<String, Object> variables = new HashMap<String, Object>();
		Object obj = runtimeService.getVariable(task.getExecutionId(), "lastTrailVotes");
		Integer votes = obj == null ? 0 : (Integer) obj;
		if(vote){
			variables.put("lastTrailVotes", votes + 1);
		}
		runtimeService.setVariables(task.getExecutionId(), variables);
		variables.put(assignee, vote);
        RelicStatus relicStatus = RelicStatus.valueOf(task.getTaskDefinitionKey());
        if(relicStatus.nextProcess(Process.THROUGH) != null){
        	variables.put(relicStatus.nextProcess(Process.THROUGH).getAssignee(), operator);
        }
        taskService.complete(task.getId(), variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> record(
			@ApiParam(value = "流程ID") @RequestParam(name = "processInstanceId") String processInstanceId, 
			@ApiParam(value = "处理人") @RequestParam(name = "assignee") String assignee) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(assignee)
				.singleResult();
		if(task == null){
			return Result.build("没有需要处理的订单！", ApiResult.ERROR);
		}
        taskService.complete(task.getId());
		return Result.build(true);
	}

}
