package org.daijie.activiti.cloud.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.daijie.api.RelicCloud;
import org.daijie.api.enums.RelicStatus;
import org.daijie.core.process.Process;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelicService implements RelicCloud {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;

	@Override
	public ModelResult<Boolean> apply(String username) {
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("username", username);
		runtimeService.startProcessInstanceByKey("relic", variables);
		return Result.build(true);
	}

	@Override
	public ModelResult<Boolean> pay(String processInstanceId, String assignee,
			Integer pay, String auditor) {
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
	public ModelResult<Boolean> trial(String processInstanceId,
			String assignee, Integer trial, String[] reviewer) {
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
	public ModelResult<Boolean> submit(String processInstanceId,
			String assignee, String auditor) {
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
	public ModelResult<Boolean> review(String processInstanceId,
			String assignee, Boolean vote, String customerService) {
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
	public ModelResult<Boolean> appointment(String processInstanceId,
			String assignee, Integer appointment, String customerService) {
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
	public ModelResult<Boolean> arrive(String processInstanceId,
			String assignee, Integer arrive, String[] lastReviewer) {
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
	public ModelResult<Boolean> lastTrail(String processInstanceId,
			String assignee, Boolean vote, String operator) {
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
	public ModelResult<Boolean> record(String processInstanceId, String assignee) {
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
