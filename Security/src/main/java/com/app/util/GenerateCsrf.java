package com.app.util;

import org.springframework.security.web.csrf.CsrfToken;

public class GenerateCsrf {

	CsrfToken token = null;

	public CsrfToken generate(CsrfToken token1){
		System.out.println(token1);
		token = token1;
		return token1 ;
	}
}
