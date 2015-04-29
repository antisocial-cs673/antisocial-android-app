package com.antisocial.app.webapi.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo implements Serializable {

	@JsonProperty
	private String Email;
	
	@JsonProperty
	private boolean HasRegistered;
	
	@JsonProperty
	private String LoginProvider;
	
}
