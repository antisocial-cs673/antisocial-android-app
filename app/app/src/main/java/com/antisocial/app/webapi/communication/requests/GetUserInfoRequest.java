package com.antisocial.app.webapi.communication.requests;

import android.content.Context;

import com.antisocial.app.webapi.communication.RestConstants;
import com.antisocial.app.webapi.communication.parsers.UserInfoParser;
import com.antisocial.app.webapi.domain.UserInfo;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.RequestMethod;
import com.dg.libs.rest.requests.RestClientRequest;

public class GetUserInfoRequest extends RestClientRequest<UserInfo> {
	
	public GetUserInfoRequest(Context context, HttpCallback<UserInfo> callback) {
        setRequestMethod(RequestMethod.GET);
        setUrl(RestConstants.USERINFO_URL);
        setParser(new UserInfoParser());
        setCallback(callback);
	}
}
