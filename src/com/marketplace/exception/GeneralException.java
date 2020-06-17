package com.marketplace.exception;

@SuppressWarnings("serial")
public class GeneralException extends Exception {
	/**
	 * message - String variable to stores the error message to be displayed in the console.
	 */
	private String message;
	
	public GeneralException(String message) {
		this.message = message;
	}
	
	/**
	 * The get method to returns error message.
	 * 
	 * @return message
	 */
	public String getMessage() {
		return "\n" + message;
	}
}
