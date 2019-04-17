package org.daijie.activiti.cloud.listener.leave;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

@SuppressWarnings("serial")
public class DepartmentManagerTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		Boolean checkStatus = (Boolean) delegateTask.getVariable("checkStatus");
		if(checkStatus){
			System.out.println("部门经理"+delegateTask.getAssignee()+"审批通过，跳转到人事经理记录考勤");
		}else{
			System.out.println("部门经理"+delegateTask.getAssignee()+"审批拒绝，流程结束！");
		}
	}
}
