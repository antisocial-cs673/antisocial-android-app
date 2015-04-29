package com.antisocial.app.webapi.communication.parsers;

import com.antisocial.app.webapi.domain.UserDataResponse;
import com.antisocial.app.webapi.domain.UserInfo;

import java.io.InputStream;

/**
 * Created by Igibek on 4/24/2015.
 */
public class UserDataResponseParser extends AntisocialParser<UserDataResponse> {
    @Override
    public UserDataResponse parse(InputStream instream) throws Exception
    {
        return mapper.readValue(instream, UserDataResponse.class);
    }
}
