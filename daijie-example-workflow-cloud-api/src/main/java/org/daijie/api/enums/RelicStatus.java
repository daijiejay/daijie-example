package org.daijie.api.enums;

import org.daijie.core.process.LinkedEnumProcess;
import org.daijie.core.process.Process;
import org.daijie.core.process.factory.LinkedEnumProcessFactory;

public enum RelicStatus implements LinkedEnumProcessFactory<RelicStatus> {

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
	
	private LinkedEnumProcess<RelicStatus> process = new LinkedEnumProcess<>();

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
	public void initEnumProcess() {
		//初始化主线流程，这里会从上往下关连流程，或者是这样this.process.add(new RelicStatus[]{APPLY, PAY, ...});
		this.process.add(APPLY);//申请备案
		this.process.add(PAY);//支付
		this.process.add(TRIAL);//初审
		this.process.add(REVIWE);//复审
		this.process.add(APPOINTENT);//预约线下审核
		this.process.add(ARRIVE);//用户到场确认
		this.process.add(LAST_TRIAL);//终审
		this.process.add(RECORD);//备案入库
		this.process.add(COMPLATE);//备案完成
		//设置支线流程，关连其它节点
		this.process.setBranch(PAY, CANEL, Process.NOT_THROUGH);//支付->备案取消，条件为不通过（如用户取消订单操作）
		this.process.setBranch(TRIAL, SUBMIT, TRIAL, Process.REJECT);//初审->重新提交->初审，条件为拒绝（如初审为图片不清晰）
		this.process.setBranch(TRIAL, REFUND, CANEL);//初审->退款->备案取消，下节点只有一个
		this.process.setBranch(REVIWE, FAIL, Process.NOT_THROUGH);//复审->备案失败，条件为不通过（如文物没有价值意义）
		this.process.setBranch(APPOINTENT, CANEL, Process.NOT_THROUGH);//预约线下审核->备案取消，条件为不通过（如客服在电话预约中用户取消备案）
		this.process.setBranch(ARRIVE, APPOINTENT, Process.RETURN);//用户到场确认->预约线下审核，条件为退回（如用户约定当天未到场）
		this.process.setBranch(LAST_TRIAL, FAIL, Process.NOT_THROUGH);//终审->备案失败，条件为不通过（如文物仿造）
	}
	
	@Override
	public LinkedEnumProcess<RelicStatus> getLinkedEnumProcess() {
		return this.process;
	}
}
