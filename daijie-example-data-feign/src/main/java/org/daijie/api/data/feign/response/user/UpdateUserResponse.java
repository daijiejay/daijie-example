package org.daijie.api.data.feign.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "修改用户")
public class UpdateUserResponse {

	@ApiModelProperty(name = "status", value = "是否修改成功", required = true)
	private boolean status;

	public UpdateUserResponse(boolean status) {
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
