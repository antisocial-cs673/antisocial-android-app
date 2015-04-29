package com.antisocial.app.auth;

import android.content.Context;

import com.dg.libs.rest.authentication.AuthenticationProvider;
import com.dg.libs.rest.requests.RestClientRequest;

public class AntisocialAuthenticationProvider implements AuthenticationProvider {
	
	private final Context context;
	
	public AntisocialAuthenticationProvider(Context context) {
		this.context = context;
	}

    @Override
    public void authenticateRequest(RestClientRequest restClientRequest) {

        String token = AuthHelper.getAuthToken(context).getAccessToken();

        if(token != null && token != "")
        {
            restClientRequest.addHeader("Authorization", "Bearer " + token);
        }
    }
}
