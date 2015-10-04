package com.hackathon.common.form;

import java.io.Serializable;

public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6396092355424367171L;
	
	private int id;
	
	private String name;
	
	private String email;
	
	private int businessId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	
	
	
}
