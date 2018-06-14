package org.daijie.api.workflow.feign.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

import org.daijie.core.process.factory.OrderEnumProcessFactory;

public enum LeaveStatus implements OrderEnumProcessFactory<LeaveStatus> {

	START(0, null, "请假申请"),
	
	APPLY(1, null, "请假申请"),
	
	PROJECT_LEADER(2, "projectLeaderId", "项目组长审批"),
	
	PROJECT_MANAGER(3, "projectManagerId", "项目经理审批"),
	
	DEPARTMENT_MANAGER(4, "departmentManagerId", "部门经理审批"),
	
	PERSONNEL(5, null, "人事自动审批"),
	
	COMPLATE(6, null, "请假完成"),
	
	REFUSE(99, null, "已拒绝");
	
	private Integer status;
	
	private String assignee;
	
	private String msg;
	
	LeaveStatus(Integer status, String assignee, String msg){
		this.status = status;
		this.assignee = assignee;
		this.msg = msg;
	}

	@Override
	public Integer getStatus() {
		return status;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public String getAssignee() {
		return assignee;
	}
	
	public static EnumMap<LeaveStatus, Integer> getEnumMap(){
		EnumMap<LeaveStatus, Integer> enumMap = new EnumMap<>(LeaveStatus.class);
		for (LeaveStatus enumObject: LeaveStatus.values()) {
			enumMap.put(enumObject, enumObject.status);
		}
		return enumMap;
	}
	
	public static EnumMap<LeaveStatus, Integer> getSortEnumMap(){
		EnumMap<LeaveStatus, Integer> enumMap = LeaveStatus.getEnumMap();
		List<LeaveStatus> list = new ArrayList<LeaveStatus>();
		Collections.sort(list, new Comparator<LeaveStatus>() {
			public int compare(LeaveStatus o1,LeaveStatus o2){
				return enumMap.get(o1).compareTo(enumMap.get(o2));
			}
		});
		return enumMap;
	}

	@Override
	public LeaveStatus getEnumType() {
		return this;
	}

	@Override
	public LeaveStatus[] getEnumTypes() {
		return LeaveStatus.values();
	}
}
