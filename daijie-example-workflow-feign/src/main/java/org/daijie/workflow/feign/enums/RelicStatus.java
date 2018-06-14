package org.daijie.workflow.feign.enums;

import org.daijie.core.process.TreeEnumProcess;
import org.daijie.core.process.Process;
import org.daijie.core.process.factory.TreeEnumProcessFactory;

public enum RelicStatus implements TreeEnumProcessFactory<RelicStatus> {

	APPLY("username", "申请文物备案"),

	PAY("username", "用户支付"),

	TRIAL("auditor", "初审"),

	REVIWE(null, "复审"),

	APPOINTENT("customerService", "预约实物线下终审"),

	ARRIVE("customerService", "客户到场确认"),

	LAST_TRIAL(null, "终审"),

	RECORD("operator", "备案入库"),

	SUBMIT("username", "重新提交"),
	
	REFUND(null, "退款"),

	FAIL(null, "备案失败"),

	CANEL(null, "备案取消"),

	COMPLATE(null, "备案完成");

	private String assignee;

	private String msg;

	RelicStatus(String assignee, String msg){
		this.assignee = assignee;
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public String getAssignee() {
		return assignee;
	}

	@Override
	public RelicStatus getEnumType() {
		return this;
	}
	
	@Override
	public RelicStatus[] getEnumTypes() {
		return RelicStatus.values();
	}

	@Override
	public TreeEnumProcess<RelicStatus> getEnumProcess() {
		TreeEnumProcess<RelicStatus> process = new TreeEnumProcess<>();
		//初始化主线流程，这里会从上往下关连流程，或者是这样this.process.add(new RelicStatus[]{APPLY, PAY, ...});
		process.add(APPLY);//申请备案
		process.add(PAY);//支付
		process.add(TRIAL);//初审
		process.add(REVIWE);//复审
		process.add(APPOINTENT);//预约线下审核
		process.add(ARRIVE);//用户到场确认
		process.add(LAST_TRIAL);//终审
		process.add(RECORD);//备案入库
		process.add(COMPLATE);//备案完成
		//设置支线流程，关连其它节点
		process.setBranch(PAY, CANEL, Process.NOT_THROUGH);//支付->备案取消，条件为不通过（如用户取消订单操作）
		process.setBranch(TRIAL, SUBMIT, TRIAL, Process.REJECT);//初审->重新提交->初审，条件为拒绝（如初审为图片不清晰）
		process.setBranch(TRIAL, REFUND, CANEL);//初审->退款->备案取消，下节点只有一个
		process.setBranch(REVIWE, FAIL, Process.NOT_THROUGH);//复审->备案失败，条件为不通过（如文物没有价值意义）
		process.setBranch(APPOINTENT, CANEL, Process.NOT_THROUGH);//预约线下审核->备案取消，条件为不通过（如客服在电话预约中用户取消备案）
		process.setBranch(ARRIVE, APPOINTENT, Process.RETURN);//用户到场确认->预约线下审核，条件为退回（如用户约定当天未到场）
		process.setBranch(LAST_TRIAL, FAIL, Process.NOT_THROUGH);//终审->备案失败，条件为不通过（如文物仿造）
		return process;
	}
}
