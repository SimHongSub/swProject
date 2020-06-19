package com.marketplace.util;

import java.text.SimpleDateFormat;

import com.marketplace.main.MarketPlace;

/** 
 * Class that supports ISBN service.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class ISBNUtil {

	/**
	 * Method to create ISBN number.
	 * 
	 */
	public String create() {
		String nowTime = new SimpleDateFormat("YYYYMMDDHHmmss").format(System.currentTimeMillis());
		String isbn = MarketPlace.my.getId() + nowTime;
		
		return isbn;
	}
}
