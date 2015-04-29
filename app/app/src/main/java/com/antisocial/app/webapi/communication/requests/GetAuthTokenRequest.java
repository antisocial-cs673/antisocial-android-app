package com.antisocial.app.webapi.communication.requests;

import android.content.Context;

import com.antisocial.app.webapi.communication.RestConstants;
import com.antisocial.app.webapi.communication.parsers.AuthTokenParser;
import com.antisocial.app.webapi.domain.AuthToken;

import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.RequestMethod;
import com.dg.libs.rest.requests.RestClientRequest;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

public class GetAuthTokenRequest extends RestClientRequest<AuthToken>{

	public GetAuthTokenRequest(Context context, String email, String password, HttpCallback<AuthToken> callback) {
		setRequestMethod(RequestMethod.POST,
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                "grant_type=password&username=" + email + "&password=" + password));
        setUrl(RestConstants.TOKEN_URL);
		addHeader("Content-Type", "application/x-www-form-urlencoded");
        setParser(new AuthTokenParser());
        setCallback(callback);
	}

}
