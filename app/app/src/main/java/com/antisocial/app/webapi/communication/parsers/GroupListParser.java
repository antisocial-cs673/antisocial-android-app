package com.antisocial.app.webapi.communication.parsers;

import java.io.InputStream;
import java.util.List;

import com.antisocial.app.webapi.domain.Group;
import com.fasterxml.jackson.core.type.TypeReference;

public class GroupListParser extends AntisocialParser<List<Group>> {

	@Override
	public List<Group> parse(InputStream instream) throws Exception {
		return mapper.readValue(instream, new TypeReference<List<Group>>() {
		});
	}

}