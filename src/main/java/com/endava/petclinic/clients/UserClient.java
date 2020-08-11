package com.endava.petclinic.clients;

import static io.restassured.RestAssured.given;

import com.endava.petclinic.auth.AuthFilter;
import com.endava.petclinic.logging.LogFilter;
import com.endava.petclinic.models.User;
import com.endava.petclinic.util.EnvReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {

	public Response createUser( User user ) {
		return given().filters( new AuthFilter( EnvReader.getAdminUserName(), EnvReader.getAdminPassword() ), new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.contentType( ContentType.JSON )
				.body( user )
				.post( "/api/users" );
	}
}
