package org.daijie.activiti.cloud.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

@SuppressWarnings("serial")
public class ApplyLeaveTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println(delegateTask.getAssignee() + "申请请假！");
	}
}
