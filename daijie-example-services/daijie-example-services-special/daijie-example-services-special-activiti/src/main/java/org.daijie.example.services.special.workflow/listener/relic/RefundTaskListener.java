package org.daijie.activiti.cloud.listener.relic;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class RefundTaskListener implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("处理退款流程！");
	}
}
