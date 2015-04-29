package com.antisocial.app.webapi.communication.requests;

import android.content.Context;

import com.antisocial.app.webapi.communication.RestConstants;
import com.antisocial.app.webapi.communication.parsers.UserDataResponseParser;
import com.antisocial.app.webapi.domain.UserDataRequest;
import com.antisocial.app.webapi.domain.UserDataResponse;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.RequestMethod;
import com.dg.libs.rest.requests.RestClientRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

/**
 * Created by Igibek on 4/23/2015.
 */
public class PostUserDataRequest extends RestClientRequest<UserDataResponse> {
    public PostUserDataRequest(Context context, UserDataRequest param, HttpCallback<UserDataResponse> callback) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String userJson =  mapper.writeValueAsString(param);
        setRequestMethod(RequestMethod.POST, RequestBody.create(MediaType.parse("application/json"), userJson));
        setUrl(RestConstants.USER_DATA_URL);
        setParser(new UserDataResponseParser());
        setCallback(callback);
        //super.setStatusHandler(new RequestStateResponseStatusHandler());

    }
}
