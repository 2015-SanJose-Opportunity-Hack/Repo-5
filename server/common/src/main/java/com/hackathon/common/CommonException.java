package com.paypal.psc.rs.common;

import java.io.Serializable;

public class CommonException extends Exception implements Serializable {



	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -7736136818993966929L;
	
	public CommonException() {
		super();
	}
	
	public CommonException(String message) {
		super(message);
	}
	
	public CommonException(Throwable t) {
		super(t);
	}
	
	public CommonException(String message, Throwable t) {
		super(message, t);
	}
}
