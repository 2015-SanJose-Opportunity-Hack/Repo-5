package com.paypal.psc.rs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paypal.psc.rs.common.crypto.EncryptedPropertyPlaceholderConfigurer;

@Entity
@Table(name="app_config")
@org.hibernate.annotations.Table(
		appliesTo = "app_config",
		indexes =	{
					
				}
		)
public class AppConfig implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length=125, nullable=false)
	private String name;
	
	@Column(name = "value", length=4096, nullable=false)
	private String value;

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

	public String getValue() {
		return value;
	}

	public String getValue(EncryptedPropertyPlaceholderConfigurer encryptedPropertyPlaceholderConfigurer) {
		return encryptedPropertyPlaceholderConfigurer.convertPropertyValue(value);		
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
