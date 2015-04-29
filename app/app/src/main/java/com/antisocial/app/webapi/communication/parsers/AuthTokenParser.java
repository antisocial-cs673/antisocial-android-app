package com.antisocial.app.webapi.communication.parsers;

import java.io.InputStream;

import com.antisocial.app.webapi.domain.AuthToken;
import com.fasterxml.jackson.core.type.TypeReference;

public class AuthTokenParser extends AntisocialParser<AuthToken> {
	@Override
	public AuthToken parse(InputStream instream) throws Exception {
    	//String response = IOUtils.toString(instream);
    	return mapper.readValue(instream, new TypeReference<AuthToken>() {
		});
	}
}
