package com.antisocial.app.webapi.communication.parsers;

import java.io.InputStream;

import com.antisocial.app.webapi.domain.RequestState;

public class RequestStateParser extends AntisocialParser<RequestState> {

	@Override
	public RequestState parse(InputStream instream) throws Exception {
		
		if(instream.available() > 0) {
			return mapper.readValue(instream, RequestState.class);
		} else {
			return null;
		}
	}

}