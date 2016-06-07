package com.abc.exceptions;

public class AccountTransferException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7198220843329153573L;
	
	private String errorMsg;
	
	public AccountTransferException(String errorMsg){
		this.errorMsg = errorMsg;
	}

}
