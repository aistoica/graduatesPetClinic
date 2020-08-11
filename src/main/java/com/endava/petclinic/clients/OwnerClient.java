package com.endava.petclinic.clients;

import static io.restassured.RestAssured.given;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.User;
import com.endava.petclinic.util.EnvReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnerClient {

	public Response createOwner( Owner owner, User user ) {
		return given()
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.auth().preemptive().basic( user.getUsername(), user.getPassword() )
				.contentType( ContentType.JSON )
				.body( owner )
				.post( "/api/owners" );
	}

	public Response getOwnerById( Integer ownerId, User user ) {
		return given()
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.auth().preemptive().basic( user.getUsername(), user.getPassword() )
				.pathParam( "ownerId", ownerId )
				.get( "/api/owners/{ownerId}" );
	}
}
