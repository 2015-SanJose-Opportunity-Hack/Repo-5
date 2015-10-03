package com.hackathon.common.form;

import java.io.Serializable;

public class AgentUserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8403454937328899050L;
	private long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String Name;
	private String emailAddress;
	private String managerName;
	private String domain;
	private Integer roleId;
	private String managerId;
	private Double creditBalance;
	private Integer cartCount;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Double getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(Double creditBalance) {
		this.creditBalance = creditBalance;
	}
	public Integer getCartCount() {
		return cartCount;
	}
	public void setCartCount(Integer cartCount) {
		this.cartCount = cartCount;
	}

}
