package com.antisocial.app.webapi.communication.parsers;

import com.dg.libs.rest.parsers.HttpResponseParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Koishybayev on 4/24/2015.
 */
public abstract class BaseJacksonMapperResponseParser<T> implements HttpResponseParser<T> {

    public static final String TAG = BaseJacksonMapperResponseParser.class.getSimpleName();

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

}