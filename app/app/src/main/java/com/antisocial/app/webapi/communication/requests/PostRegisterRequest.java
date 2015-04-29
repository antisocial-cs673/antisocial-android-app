package com.antisocial.app.webapi.communication.requests;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.antisocial.app.webapi.communication.RestConstants;
import com.antisocial.app.webapi.communication.parsers.RequestStateParser;
import com.antisocial.app.webapi.domain.RequestState;
import com.antisocial.app.webapi.domain.UserRegisterInfo;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.RequestMethod;
import com.dg.libs.rest.domain.ResponseStatus;
import com.dg.libs.rest.handlers.DefaultResponseStatusHandler;
import com.dg.libs.rest.requests.RestClientRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

public class PostRegisterRequest extends RestClientRequest<RequestState> {
	
	private UserRegisterInfo user;
	
	public PostRegisterRequest(Context context, UserRegisterInfo user, HttpCallback<RequestState> callback) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String userJson =  mapper.writeValueAsString(user);
        setRequestMethod(RequestMethod.POST, RequestBody.create(MediaType.parse("application/json"), userJson));
        setUrl(RestConstants.USER_REGISTER_URL);
        setParser(new RequestStateParser());
        setCallback(callback);
		super.setStatusHandler(new RequestStateResponseStatusHandler());
	}

	class RequestStateResponseStatusHandler extends DefaultResponseStatusHandler {
		
		@Override
		public boolean hasErrorInStatus(ResponseStatus status) {
			
			if(status.getStatusCode() == 400) {
				return false;
			}
			
			return super.hasErrorInStatus(status);
		}
		
	}
}
