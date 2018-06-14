package org.daijie.api.data.feign.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "添加用户")
public class AddUserResponse {

	@ApiModelProperty(name = "status", value = "是否添加成功", required = true)
	private boolean status;

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
