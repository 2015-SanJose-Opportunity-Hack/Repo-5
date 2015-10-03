package com.paypal.psc.rs.common.form;

import java.io.Serializable;

public class ShoppingCartForm implements Serializable{
	
	private long[] id;
	
	private long[] itemId;
	
	private String[] itemCode;
	
	private String[] itemName;
	
	private int[] orderLimit;
	
	private double[] price;
	
	private int[] qty;
	
	private String[] size;
	
	private int[] purchasedQty;
	
	public long[] getId() {
		return id;
	}

	public void setId(long[] id) {
		this.id = id;
	}

	public long[] getItemId() {
		return itemId;
	}

	public void setItemId(long[] itemId) {
		this.itemId = itemId;
	}

	public String[] getItemCode() {
		return itemCode;
	}

	public void setItemCode(String[] itemCode) {
		this.itemCode = itemCode;
	}

	public String[] getItemName() {
		return itemName;
	}

	public void setItemName(String[] itemName) {
		this.itemName = itemName;
	}

	public int[] getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(int[] orderLimit) {
		this.orderLimit = orderLimit;
	}

	public double[] getPrice() {
		return price;
	}

	public void setPrice(double[] price) {
		this.price = price;
	}

	public int[] getQty() {
		return qty;
	}

	public void setQty(int[] qty) {
		this.qty = qty;
	}

	public String[] getSize() {
		return size;
	}

	public void setSize(String[] size) {
		this.size = size;
	}

	public int[] getPurchasedQty() {
		return purchasedQty;
	}

	public void setPurchasedQty(int[] purchasedQty) {
		this.purchasedQty = purchasedQty;
	}

	
}
