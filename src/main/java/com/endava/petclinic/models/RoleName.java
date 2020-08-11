package com.endava.petclinic.models;

public enum RoleName {

	VET_ADMIN( "VET_ADMIN" ),
	OWNER_ADMIN( "OWNER_ADMIN" );

	private String name;

	private RoleName( String name ) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
