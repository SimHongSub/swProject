package com.marketplace.util;

import com.marketplace.main.MarketPlace;

public class EmailUtil {

	public String send(String user, String email) {
		String message = "구매자 " + MarketPlace.my.getId() + " E-mail : " + MarketPlace.my.getEmail() + "\n"
				+ "판매자 " + user + " E-mail : " + email + "\n메일이 발송되었습니다.";
		
		return message;
	}
}
