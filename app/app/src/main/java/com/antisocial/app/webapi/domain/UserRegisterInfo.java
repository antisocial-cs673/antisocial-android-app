package com.antisocial.app.webapi.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterInfo implements Serializable {
	
	private String email;
	
	private String password;
	
	private String confirmPassword;
	
	public UserRegisterInfo(String email, String password,
			String confirmPassword) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	@JsonProperty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
