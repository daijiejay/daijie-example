package org.dijie.example.feign.demo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.daijie.core.result.Page;

@ApiModel(description = "用户信息条件查询")
public class UserRequest extends Page{

	private static final long serialVersionUID = 8799762960947214728L;

	@ApiModelProperty(name = "userId", value = "用户ID")
	private Integer userId;
	
	@ApiModelProperty(name = "userName", value = "用户名")
	private String userName;
	
	@ApiModelProperty(name = "password", value = "密码")
	private String password;
	
	@ApiModelProperty(name = "salt", value = "盐")
	private String salt;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
