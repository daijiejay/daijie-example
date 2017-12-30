package org.daijie.activiti.cloud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.daijie.api.BaseCloud;
import org.daijie.core.controller.ApiController;
import org.daijie.core.factory.specific.ModelResultInitialFactory.Result;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseService extends ApiController implements BaseCloud {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ModelResult<PageResult<Map<String, Object>>> getProcesses() {
		List<Map<String, Object>> rows = new ArrayList<>();
		PageResult<Map<String, Object>> datas = new PageResult<>();
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> processInstances = query.list();
		processInstances.forEach(processInstance ->{
			HistoricActivityInstance historicActivityInstance = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
				.unfinished().singleResult();
			Map<String, Object> row = new HashMap<>();
			row.put("processInstanceId", processInstance.getId());
			if(historicActivityInstance != null){
				Task task = taskService.createTaskQuery().taskId(historicActivityInstance.getTaskId()).singleResult();
				Map<String, Object> runtimeVariables = runtimeService.getVariables(historicActivityInstance.getExecutionId());
				row.put("activityName", historicActivityInstance.getActivityName());
				row.put("taskId", historicActivityInstance.getTaskId());
				row.put("createTime", task.getCreateTime());
				row.put("username", runtimeVariables.get("username"));
				row.put("days", runtimeVariables.get("days"));
				row.put("checkUserId", task.getAssignee());
			}else{
				row.put("activityName", "流程结束");
			}
			rows.add(row);
		});
		datas.setTotal(query.count());
		datas.setRows(rows);
		return Result.build(datas);
	}

	@Override
	public ModelResult<Map<String, Object>> getProcessById(Integer id) {
		Task task = taskService.createTaskQuery().processInstanceId(id.toString())
				.singleResult();
		Map<String, Object> runtimeVariables = runtimeService.getVariables(task.getExecutionId());
		Map<String, Object> row = new HashMap<>();
		row.put("taskId", task.getId());
		row.put("taskName", task.getName());
		row.put("assignee", task.getAssignee());
		row.put("category", task.getCategory());
		row.put("createTime", task.getCreateTime());
		row.put("delegationState", task.getDelegationState());
		row.put("description", task.getDescription());
		row.put("dueDate", task.getDueDate());
		row.put("executionId", task.getExecutionId());
		row.put("formKey", task.getFormKey());
		row.put("owner", task.getOwner());
		row.put("parentTaskId", task.getParentTaskId());
		row.put("priority", task.getPriority());
		row.put("processDefinitionId", task.getProcessDefinitionId());
		row.put("processInstanceId", task.getProcessInstanceId());
		row.put("taskDefinitionKey", task.getTaskDefinitionKey());
		row.put("username", runtimeVariables.get("username"));
		row.put("days", runtimeVariables.get("days"));
		return Result.build(row);
	}

	@Override
	public ModelResult<PageResult<Map<String, Object>>> getProcessByOperator(
			Integer operatorId) {
		List<Map<String, Object>> rows = new ArrayList<>();
		PageResult<Map<String, Object>> datas = new PageResult<>();
		TaskQuery query = taskService.createTaskQuery().taskAssignee(operatorId.toString());
		List<Task> tasks = query.list();
		tasks.forEach(task ->{
			Map<String, Object> runtimeVariables = runtimeService.getVariables(task.getExecutionId());
			Map<String, Object> taskVariables = taskService.getVariables(task.getId());
			Map<String, Object> row = new HashMap<>();
			row.put("taskId", task.getId());
			row.put("taskName", task.getName());
			row.put("assignee", task.getAssignee());
			row.put("category", task.getCategory());
			row.put("createTime", task.getCreateTime());
			row.put("delegationState", task.getDelegationState());
			row.put("description", task.getDescription());
			row.put("dueDate", task.getDueDate());
			row.put("executionId", task.getExecutionId());
			row.put("formKey", task.getFormKey());
			row.put("owner", task.getOwner());
			row.put("parentTaskId", task.getParentTaskId());
			row.put("priority", task.getPriority());
			row.put("processDefinitionId", task.getProcessDefinitionId());
			row.put("processInstanceId", task.getProcessInstanceId());
			row.put("taskDefinitionKey", task.getTaskDefinitionKey());
			row.put("username", runtimeVariables.get("username"));
			row.put("days", runtimeVariables.get("days"));
			row.put("checkUserId", taskVariables.get("checkUserId"));
			rows.add(row);
		});
		datas.setTotal(query.count());
		datas.setRows(rows);
		return Result.build(datas);
	}

}
