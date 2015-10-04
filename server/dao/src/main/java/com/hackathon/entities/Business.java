package com.hackathon.entities;


import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "business")
@org.hibernate.annotations.Table(
		appliesTo = "business"
		)
public class Business implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "businessName")
	private String businessName;
	
	@Column(name = "storeLink")
	private String storeLink;
	
	@Column(name = "ownerLink")
	private String ownerLink;
	
	@Column(name = "ownerName")
	private String ownerName;
	
	@Column(name = "likes")
	private int likes;
	
	@Column(name = "yelpURL")
	private String yelpURL;
	
	@Column(name = "latitude")
	private double  latitude;
	
	@Column(name = "longitude")
	private double  longitude;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String  state;
	
	@Column(name = "zipcode")
	private long  zipcode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getStoreLink() {
		return storeLink;
	}

	public void setStoreLink(String storeLink) {
		this.storeLink = storeLink;
	}

	public String getOwnerLink() {
		return ownerLink;
	}

	public void setOwnerLink(String ownerLink) {
		this.ownerLink = ownerLink;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getYelpURL() {
		return yelpURL;
	}

	public void setYelpURL(String yelpURL) {
		this.yelpURL = yelpURL;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZipcode() {
		return zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	
}
