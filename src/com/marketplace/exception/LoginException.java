package com.marketplace.exception;

/** 
 * Class responsible for login exception.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LoginException extends Exception {
	/**
	 * message - String variable to stores the error message to be displayed in the console.
	 */
	private String message;
	
	public LoginException(String message) {
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
