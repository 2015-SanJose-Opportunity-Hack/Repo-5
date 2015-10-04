package com.hackathon.entities;


import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "user")
@org.hibernate.annotations.Table(
		appliesTo = "user"
		)
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "business_lent_id")
	private int businessId;

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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

