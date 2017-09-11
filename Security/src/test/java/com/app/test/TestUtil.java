package com.app.test;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Novica Nikolic <nole0223@gmail.com>
 * 
 * Unit test for simple App.
 */


public class TestUtil {
	public static String json(Object object) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		return mapper.writeValueAsString(object);
	}
}
