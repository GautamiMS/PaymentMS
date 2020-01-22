package com.banking.payment.exceptions;

public class InsufficientFundException extends RuntimeException {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundException(String errorMsg) {
	    super(errorMsg);
	  }
}