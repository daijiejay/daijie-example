package org.daijie.api.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

import org.daijie.core.factory.IEnumFactory;

public enum LeaveStatus implements IEnumFactory {

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

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getAssignee() {
		return assignee;
	}

	public static LeaveStatus getEnum(Integer status) {  
        for  (LeaveStatus leaveStatus : LeaveStatus.values()) {  
            if  (leaveStatus.getStatus() == status) {  
                return  leaveStatus;  
            }  
        }  
        return   null ;  
    }
	
	public static EnumMap<LeaveStatus, Integer> getEnumMap(){
		EnumMap<LeaveStatus, Integer> enumMap = new EnumMap<>(LeaveStatus.class);
		for (LeaveStatus leaveStatus: LeaveStatus.values()) {
			enumMap.put(leaveStatus, leaveStatus.status);
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
	
	public static List<LeaveStatus> getSortValueList(){
		List<LeaveStatus> list = new ArrayList<LeaveStatus>();
		Integer[] array = new Integer[LeaveStatus.values().length];
		for (int i = 0; i < LeaveStatus.values().length; i++) {
			array[i] = LeaveStatus.values()[i].status;
		}
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			list.add(LeaveStatus.getEnum(array[i]));
		}
		return list;
	}
	
	public static LeaveStatus getEnumByNextValue(Integer status){
		List<LeaveStatus> list = getSortValueList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).status == status){
				if(list.size() > i+1){
					return list.get(i+1);
				}
				break;
			}
		}
		return null;
	}
}
