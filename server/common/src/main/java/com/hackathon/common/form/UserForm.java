package com.paypal.psc.rs.common.form;

import java.io.Serializable;

public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6396092355424367171L;
	
	private long[] id;
	
	private String[] loginId;
	
	private String[] name;
	
	private String[] location;
	
	private String[] role;
	
	private long[] managerId;
	
	private double[] credit;
	
	private String action;
	
	private String errorMessage;
	
	private String successMessage;
	
	private int status;
	
	private int[] active;
	
	private boolean newUser;
	
	private String[] managerLoginId;
	
	private String[] managerName;

	public long[] getId() {
		return id;
	}

	public void setId(long[] id) {
		this.id = id;
	}
	
	public String[] getLoginId() {
		return loginId;
	}

	public void setLoginId(String[] loginId) {
		this.loginId = loginId;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getLocation() {
		return location;
	}

	public void setLocation(String[] location) {
		this.location = location;
	}

	public String[] getRole() {
		return role;
	}

	public void setRole(String[] role) {
		this.role = role;
	}

	public long[] getManagerId() {
		return managerId;
	}

	

	public double[] getCredit() {
		return credit;
	}

	public void setCredit(double[] credit) {
		this.credit = credit;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setManagerId(long[] managerId) {
		this.managerId = managerId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int[] getActive() {
		return active;
	}

	public void setActive(int[] active) {
		this.active = active;
	}

	public boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	public String[] getManagerLoginId() {
		return managerLoginId;
	}

	public void setManagerLoginId(String[] managerLoginId) {
		this.managerLoginId = managerLoginId;
	}

	public String[] getManagerName() {
		return managerName;
	}

	public void setManagerName(String[] managerName) {
		this.managerName = managerName;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

}
