package org.daijie.api.data.feign.response.user;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "添加用户")
public class AddUserResponse implements Serializable {

	private static final long serialVersionUID = 7859896830375153081L;
	
	@ApiModelProperty(name = "status", value = "是否添加成功", required = true)
	private boolean status;
	
	public AddUserResponse(){}

	public AddUserResponse(boolean status) {
		super();
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
