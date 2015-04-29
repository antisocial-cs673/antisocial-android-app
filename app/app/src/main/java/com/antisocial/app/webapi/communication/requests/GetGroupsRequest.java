package com.antisocial.app.webapi.communication.requests;

import java.util.List;

import com.antisocial.app.webapi.communication.RestConstants;
import com.antisocial.app.webapi.communication.parsers.GroupListParser;
import com.antisocial.app.webapi.domain.Group;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.RequestMethod;
import com.dg.libs.rest.requests.RestClientRequest;

public class GetGroupsRequest extends RestClientRequest<List<Group>>{
	
	public GetGroupsRequest(HttpCallback<List<Group>> callback) {
        setRequestMethod(RequestMethod.GET);
        setParser(new GroupListParser());
        setUrl(RestConstants.GROUPS_URL);
        setCallback(callback);
	}
}