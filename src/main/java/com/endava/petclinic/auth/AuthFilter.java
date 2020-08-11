package com.endava.petclinic.auth;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthFilter implements Filter {

	private String username;
	private String password;

	public AuthFilter( String username, String password ) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Response filter( FilterableRequestSpecification filterableRequestSpecification,
			FilterableResponseSpecification filterableResponseSpecification,
			FilterContext filterContext ) {

		filterableRequestSpecification.auth().preemptive().basic( username, password );

		Response response = filterContext.next( filterableRequestSpecification, filterableResponseSpecification );

		return response;
	}
}
