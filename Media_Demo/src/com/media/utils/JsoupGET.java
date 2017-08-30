package com.media.utils;

import java.io.IOException;
import java.security.MessageDigest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupGET {
    static String head = null;
    static String url = "http://mp.weixin.qq.com/s?timestamp=1470281197&src=3&ver=1&signature=hlFxuhS84a-I5QDVFoO67HQPl2oeEAbcmLOeYG*CHNKo7llXqhJMDcG76VxaFHMm0eCxoD9ugjQJ1q7S-UvQe2cJ9afnbpkzgjUUtU6-WxD7PsvCKaPgidDYqdLt93wenCSgwOnLPe-JmPHEjgW8SbDrLCoVmSZZf99w59pdY*I=";


    /**
     * 获取文章标题
     */
    public static String article(String url) {
        Document doc;
        String linkText = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","rich_media_area_primary");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("h2");
                for (Element link : links) {
                    linkText = link.text().trim();
                    System.out.println(linkText);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return linkText;

    }

	/**
     * 获取公众号
     */
    public static String[] wechat(String url) {
        Document doc;
        int i = 0;
        String[] media = new String[3];
        try {
            doc = Jsoup.connect(url).get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","profile_inner");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("strong");
                for (Element link : links) {
                    String linkText = link.text().trim();
                    System.out.println(linkText);
                    media[i] = linkText;
                    i++;
                }
                Elements links1 = element.getElementsByTag("span");
                for (Element link : links1) {
                    String linkText = link.text().trim();
                    System.out.println(linkText);
                    media[i] = linkText;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return media;

    }
    
    /**
     * 获取公众号头像提取网址
     */
    public static String wechatHeadHref(String url) {
        Document doc;
        String linkHref = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","results mt7");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("div");
                linkHref = links.attr("href");
                System.out.println(linkHref);
            }           
           } catch (IOException e) {
            e.printStackTrace();
        }
		return linkHref;
    }

    /**
     * 获取公众号头像
     */
    public static String wechatHead(String url) {
        Document doc;
        String linkHref = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements ListDiv1 = doc.getElementsByAttributeValue("class","radius_avatar profile_avatar");
            for (Element element :ListDiv1) {
                Elements links = element.getElementsByTag("img");
                linkHref = links.attr("src");
                System.out.println(linkHref);
            }
        } catch (IOException e) {
        	//http://open.weixin.qq.com/qr/code/?username=微信号
            e.printStackTrace();
        }
		return linkHref;
    }
    
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String stringMD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    }  
}

