package com.media.utils;

public class CommonUtils {
	public static String makeCode(){
		String str = "";
		str += (int)(Math.random()*9+1);
		for(int i = 0; i < 5; i++){
			str += (int)(Math.random()*10);
		}
		System.out.println(str);
		return str;
	}
}
