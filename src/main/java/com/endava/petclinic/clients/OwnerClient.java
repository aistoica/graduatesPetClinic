package com.endava.petclinic.clients;

import static io.restassured.RestAssured.given;

import com.endava.petclinic.auth.AuthFilter;
import com.endava.petclinic.logging.LogFilter;
import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.User;
import com.endava.petclinic.util.EnvReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnerClient {

	public Response createOwner( Owner owner, User user ) {
		return given().filters( new AuthFilter( user.getUsername(), user.getPassword() ), new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.contentType( ContentType.JSON )
				.body( owner )
				.post( "/api/owners" );
	}

	public Response getOwnerById( Integer ownerId, User user ) {
		return given().filters( new AuthFilter( user.getUsername(), user.getPassword() ), new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.pathParam( "ownerId", ownerId )
				.get( "/api/owners/{ownerId}" );
	}
}
