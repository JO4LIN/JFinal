package com.media.controller;


import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.Back;
import com.media.model.CityClass;
import com.media.model.MediaInfo;
import com.media.model.ProvinceClass;
import com.media.model.base.BaseCityClass;

@Clear
public class CityMediaController extends Controller{
	public void index() {
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	//查看地区自媒体
	public void screenCityList() throws Exception{
		Back screenListBack = new Back();	
		int pageNumber = 0;
		int pageSize =0;
		String orderType,cityName,priceType;
		int price,fansNum,readNum,isVerification1,isVerification2;
		try {
			price = getParaToInt("price",1000000000);
			fansNum = getParaToInt("fansNum",0);
			readNum = getParaToInt("readNum",0);
			cityName = getPara("cityName");
			int cityId = CityClass.me.findCityId(cityName);
			String searchKey = getPara("searchKey","%");
			String likeStr = "";
			for (int i = 0; i < searchKey.length(); i++) {
				if (i < searchKey.length() - 1) {
					likeStr += "%" + searchKey.charAt(i);
				} else {
					likeStr += "%" + searchKey.charAt(i) + "%";
				}
			}
			
			boolean	firSearch = getParaToBoolean("firSearch");
			if(firSearch){
				CityClass cityClass = CityClass.me.findById(cityId);
				int citySearchNum = cityClass.getCitySearchNum() + 1;
				cityClass.setCitySearchNum(citySearchNum);
				cityClass.update();
			}
			
			isVerification1 = getParaToInt("isVerification1",1);
			isVerification2 = getParaToInt("isVerification2",0);
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			orderType = getPara("ordType","fansNum");
			priceType = getPara("priceType","hardSimplePrice");
			Page<MediaInfo> mediaInfo = MediaInfo.me.screenCityMedia(pageNumber, pageSize, price, fansNum, readNum, isVerification1, isVerification2, orderType, priceType, cityId,likeStr);
			screenListBack.put("CAllBACK_STATUS", 100);
			screenListBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			screenListBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			screenListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(screenListBack);
		}		
	}

	//地区热搜榜
	public  void hostMediaList() {
		Back back = new Back();
		try{
			String localCityName = getPara("cityName");
			int localCityId = CityClass.me.findCityId(localCityName);
			List<List> hostMediaList = new ArrayList<List>();
			hostMediaList.add(CityClass.me.hotCityList(localCityId));
			Page<CityClass> cityIdlist = CityClass.me.hotCityIdList();
			for(int i = 0,j=0;i<6;i++){
				int cityId = cityIdlist.getList().get(i).getCityId();
				if(cityId!=localCityId&&j<5){
					hostMediaList.add(CityClass.me.hotCityList(cityId));
					j++;
				}
			}
			
			back.put("CAllBACK_STATUS", 100);
			back.put("TOTAL_COUNT", 1);
			back.put("GET_ARRAY_DATA", hostMediaList);
		}catch (Exception e) {
			back.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(back);
		}	
	}
}
