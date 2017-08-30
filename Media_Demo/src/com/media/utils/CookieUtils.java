package com.media.utils;

import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.PropKit;

public class CookieUtils {
	
	public static String COOKIE_SEPARATOR = "#";
	public static final String encrypt_key = "media";
	
/*	public static void put(Controller ctr, String key, String value, int maxAgeInSeconds){
		//String encrypt_key = PropKit.get("encrypt_key");
		String saveTime = System.currentTimeMillis() + "";
		String encrypt_value = encrypt(encrypt_key, saveTime, maxAgeInSeconds + "", value);
		
		String cookieValue = encrypt_value + COOKIE_SEPARATOR + saveTime + COOKIE_SEPARATOR 
				+ maxAgeInSeconds + COOKIE_SEPARATOR + value;
		ctr.setCookie(key, cookieValue, maxAgeInSeconds, true);
	}*/
	
	public static String encrypt(String encrypt_key,String saveTime,String maxAgeInSeconds,String value) {
		return HashKit.md5(encrypt_key + saveTime + maxAgeInSeconds + value);
	}
	
	private static void remove(Controller ctr, String key){
		ctr.removeCookie(key);
	}
	
	public static String get(Controller ctr, String key){
		
		//String encrypt_key = PropKit.get("encrypt_key");
		
		if(null != ctr.getCookie(key)){
			String cookieValue = ctr.getCookie(key);
			System.out.println(cookieValue);
			String cookieStrings[] = cookieValue.split(COOKIE_SEPARATOR);
			if(null != cookieStrings && 4 == cookieStrings.length){
				String encrypt_value = cookieStrings[0];
				String saveTime = cookieStrings[1];
				String maxAgeInSeconds = cookieStrings[2];
				String value = cookieStrings[3];
			
				String encrpt = encrypt(encrypt_key, saveTime, maxAgeInSeconds, value);
				
				//保证cookie不被人修改
				if(encrypt_value != null && encrypt_value.equals(encrpt)){
					long stime = Long.parseLong(saveTime);
					long maxtime = Long.parseLong(maxAgeInSeconds)*1000;
					//查看是否超时
					if((stime+maxtime) - System.currentTimeMillis() > 0){
						return value;
					}
				}
			}	
		}

		return null;
	}
}

