package com.antisocial.app.webapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestState implements Serializable {
	
	@JsonProperty
	private String Message;
	
	@JsonProperty
	private Map<String,List<String>> ModelState;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Map<String, List<String>> getModelState() {
		return ModelState;
	}

	public void setModelState(Map<String, List<String>> modelState) {
		ModelState = modelState;
	}
}
