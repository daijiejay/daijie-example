package org.daijie.activiti.cloud.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PersonnelManagerTaskListener implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("人事经理自动记录考勤，流程结束！");
	}
}
