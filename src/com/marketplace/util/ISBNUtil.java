package com.marketplace.util;

import java.text.SimpleDateFormat;

import com.marketplace.main.MarketPlace;

public class ISBNUtil {

	public String create() {
		String nowTime = new SimpleDateFormat("YYYYMMDDHHmmss").format(System.currentTimeMillis());
		String isbn = MarketPlace.my.getId() + nowTime;
		
		return isbn;
	}
}
