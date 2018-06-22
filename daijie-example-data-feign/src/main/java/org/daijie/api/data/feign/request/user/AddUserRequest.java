package org.daijie.api.data.feign.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "添加用户")
public class AddUserRequest {
	
	@ApiModelProperty(name = "userName", value = "用户名", required = true)
	private String userName;
	
	@ApiModelProperty(name = "password", value = "密码", required = true)
	private String password;
	
	@ApiModelProperty(name = "salt", value = "盐")
	private String salt;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
