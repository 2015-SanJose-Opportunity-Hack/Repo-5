package com.paypal.psc.rs.common;

public enum Status
{
	Active("1"),   
    Inactive("0");
	
    final String value;

    Status(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public int getValue() {
        return Integer.parseInt(value);
    }
}
