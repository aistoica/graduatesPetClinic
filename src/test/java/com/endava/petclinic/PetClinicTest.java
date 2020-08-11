package com.endava.petclinic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.petclinic.clients.OwnerClient;
import com.endava.petclinic.clients.UserClient;
import com.endava.petclinic.data.DataGenerator;
import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.RoleName;
import com.endava.petclinic.models.User;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class PetClinicTest {

	private OwnerClient ownerClient = new OwnerClient();
	private UserClient userClient = new UserClient();
	private DataGenerator dataGenerator = new DataGenerator();

	@Test
	public void firstTest() {

		// create new User
		User user = dataGenerator.getUser( RoleName.OWNER_ADMIN );
		Response createUserResponse = userClient.createUser( user );
		createUserResponse.then().statusCode( HttpStatus.SC_CREATED );

		// create new Owner
		Owner owner = dataGenerator.getOwner();
		Response response = ownerClient.createOwner( owner, user );
		response.then().statusCode( HttpStatus.SC_CREATED );

		Integer id = response.jsonPath().getInt( "id" );

		// get owner by id
		Response getResponse = ownerClient.getOwnerById( id, user );
		getResponse.then().statusCode( HttpStatus.SC_OK );

		Owner actualOwner = getResponse.as( Owner.class );
		assertThat( actualOwner, is( owner ) );
	}
}
