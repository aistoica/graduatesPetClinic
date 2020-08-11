package com.endava.petclinic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.RoleName;
import com.endava.petclinic.models.User;

import io.restassured.response.Response;

public class GetOwnerByIdTest extends TestBaseClass {

	@Test
	public void shouldGetOwnerByIdGivenOwnerAdminUser() {

		//GIVEN
		User user = dataGenerator.getUser( RoleName.OWNER_ADMIN );
		Response createUserResponse = userClient.createUser( user );
		createUserResponse.then().statusCode( HttpStatus.SC_CREATED );

		Owner owner = dataGenerator.getOwner();
		Response createOwnerResponse = ownerClient.createOwner( owner, user );
		createOwnerResponse.then().statusCode( HttpStatus.SC_CREATED );

		int ownerId = createOwnerResponse.jsonPath().getInt( "id" );

		//WHEN
		Response getOwnerByIdResponse = ownerClient.getOwnerById( ownerId, user );

		//THEN
		getOwnerByIdResponse.then().statusCode( HttpStatus.SC_OK );

		Owner actualOwner = getOwnerByIdResponse.as( Owner.class );
		assertThat( actualOwner, is( owner ) );

	}
}
