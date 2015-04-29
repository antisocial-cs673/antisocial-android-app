package com.antisocial.app.webapi.communication.parsers;

import java.io.InputStream;
import com.antisocial.app.webapi.domain.UserInfo;

public class UserInfoParser extends BaseJacksonMapperResponseParser<UserInfo> {

	@Override
	public UserInfo parse(InputStream instream) throws Exception {
		return mapper.readValue(instream, UserInfo.class);
	}

}