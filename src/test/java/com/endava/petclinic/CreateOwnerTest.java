package com.endava.petclinic;

import static com.endava.petclinic.models.RoleName.OWNER_ADMIN;
import static com.endava.petclinic.models.RoleName.VET_ADMIN;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.RoleName;
import com.endava.petclinic.models.User;

import io.restassured.response.Response;

public class CreateOwnerTest extends TestBaseClass {

	@Test
	public void shouldCreateOwnerGivenOwnerAdminUser() {

		//GIVEN
		User user = dataGenerator.getUser( OWNER_ADMIN );
		Response createUserResponse = userClient.createUser( user );
		createUserResponse.then().statusCode( HttpStatus.SC_CREATED );

		Owner owner = dataGenerator.getOwner();

		//WHEN
		Response createOwnerResponse = ownerClient.createOwner( owner, user );

		//THEN
		createOwnerResponse.then().statusCode( HttpStatus.SC_CREATED )
				.body( "id", not( nullValue() ) )
				.body( "firstName", is( owner.getFirstName() ) );

	}

	@Test
	public void shouldFailToCreateOwnerGivenVetAdminUser() {
		//GIVEN
		User user = dataGenerator.getUser( VET_ADMIN );
		Response createUserResponse = userClient.createUser( user );
		createUserResponse.then().statusCode( HttpStatus.SC_CREATED );

		Owner owner = dataGenerator.getOwner();

		//WHEN
		Response createOwnerResponse = ownerClient.createOwner( owner, user );

		//THEN
		createOwnerResponse.then().statusCode( HttpStatus.SC_FORBIDDEN );
	}
}
