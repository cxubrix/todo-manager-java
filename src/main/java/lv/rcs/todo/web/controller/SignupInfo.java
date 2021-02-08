package lv.rcs.todo.web.controller;

import io.swagger.annotations.ApiModelProperty;

public class SignupInfo {

	@ApiModelProperty(required = false)
	private String email;

	@ApiModelProperty(required = false)
	private String password;

	@ApiModelProperty(required = false)
	private String firstname;

	@ApiModelProperty(required = false)
	private String lastname;

	public SignupInfo() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

}
