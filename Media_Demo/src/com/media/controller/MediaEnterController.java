package com.media.controller;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.Back;
import com.media.model.CityClass;
import com.media.model.FansNum;
import com.media.model.MediaInfo;
import com.media.model.ProvinceClass;
import com.media.model.SchoolClass;
import com.media.model.SortClass;
import com.media.model.UserInfo;
import com.media.utils.JsoupGET;

public class MediaEnterController extends Controller{
	
	public void index() {
		renderJson("{\"KEY_STATUS\":100}");
	}

	
	//入驻验证码成功后，获取对应数据返回
	public void initMedia() {
		String wechatHeadHref,wechatHead,url,title,code;
		Back back = new Back();
		try {
			url = getPara("url");
			code = getPara("code");
			title = JsoupGET.article(url);
			System.out.println(title);
			if(title.equals(code)){
				String[] mediaInti = JsoupGET.wechat(url);
				String twoCode = "http://open.weixin.qq.com/qr/code/?username="+mediaInti[1];
				wechatHeadHref = JsoupGET.wechatHeadHref("http://weixin.sogou.com/weixin?type=1&ie=utf8&query="+mediaInti[1]);
				wechatHead = JsoupGET.wechatHead(wechatHeadHref);
				System.out.println(wechatHead);
				List<MediaInfo> user = MediaInfo.me.checkWechatNum(mediaInti[1]);
				if(user.isEmpty()){
					back.put("mediaName",mediaInti[0]);
					back.put("wechatNum",mediaInti[1]);
					back.put("intro",mediaInti[2]);
					back.put("twoCode",twoCode);
					back.put("wechatHead",wechatHead);
					back.put("CAllBACK_STATUS", 100);
				}else{
					back.put("CAllBACK_STATUS", 300);
				}
			}else{
				back.put("CAllBACK_STATUS", 200);
			}
		}  catch (Exception e) {
			back.put("CAllBACK_STATUS", -100);
		}finally{
			renderJson(back);
		}
		
		
	}
	
	//入驻
	public void enterMedia(){
		boolean flag,flagCity;
		int schoolId;
		try{
			MediaInfo mediaInfo = getModel(MediaInfo.class);
			FansNum fansNum = new FansNum();
			fansNum.setFansNum(mediaInfo.getFansNum());
			fansNum.setFansNumPicture(getPara("fansNumPicture"));
			fansNum.setMediaName(mediaInfo.getMediaName());
			fansNum.setUserId(mediaInfo.getUserId());
			fansNum.save();
			
			String province  = getPara("provinceName");
			String city  = getPara("cityName");
			String type  = getPara("sort");
			String school  = getPara("schoolName");
			if(school.equals("无")){
				schoolId = 0;
			}else {
				schoolId = SchoolClass.me.findSchoolId(school);
			}
			int cityId = CityClass.me.findCityId(city);
			int provinceId = ProvinceClass.me.findProvinceId(province);
			int typeId = SortClass.me.findSortId(type);
			mediaInfo.setMedTypesFir(typeId);
			mediaInfo.setCityId(cityId);
			mediaInfo.setProvinceId(provinceId);
			mediaInfo.setSchoolId(schoolId);
			flag = mediaInfo.save();
			
			CityClass cityClass = CityClass.me.findById(cityId);
			int cityMediaNum = cityClass.getCityMediaNum() + 1;
			cityClass.setCityMediaNum(cityMediaNum);
			flagCity = cityClass.update();
			if(flag&&flagCity){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//该用户已入驻自媒体
	public void mediaList(){
		MediaInfo mediaBack = new MediaInfo();
		int userId;
		try{
			userId = getParaToInt("userId");
			List<MediaInfo> media = MediaInfo.me.findMediaId(userId);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", media.size());
			mediaBack.put("GET_ARRAY_DATA", media);
		}catch(Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		}finally{
			renderJson(mediaBack);
		}
	}
	
	
	
}
