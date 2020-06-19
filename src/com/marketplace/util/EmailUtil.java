package com.marketplace.util;

import com.marketplace.main.MarketPlace;

/** 
 * Class that supports E-mail service.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class EmailUtil {

	/**
	 * Method to send email.
	 * 
	 * @param userId - User id to receive email.
	 * @param email - Email address to receive email.
	 */
	public String send(String userId, String email) {
		String message = "구매자 " + MarketPlace.my.getId() + " E-mail : " + MarketPlace.my.getEmail() + "\n"
				+ "판매자 " + userId + " E-mail : " + email + "\n메일이 발송되었습니다.";
		
		return message;
	}
}
