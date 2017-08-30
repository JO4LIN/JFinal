package com.media.controller;


import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.media.model.CityClass;
import com.media.model.CollectMedia;
import com.media.model.CollectUser;
import com.media.model.IndentInfo;
import com.media.model.MediaInfo;
import com.media.model.ProvinceClass;
import com.media.model.SchoolClass;
import com.media.model.SortClass;

public class MediaInfoController extends Controller {	
	
	public void index() {
		renderJson("{\"KEY_STATUS\":100}");
	}

//	分页，显示全部自媒体	
	@Clear
	public void mediaIdAscJson() {
//		setAttr("blogPage", Blog.me.paginate(getParaToInt(0, 1), 10));
		MediaInfo mediaBack = new MediaInfo();
		try{
			int pageNow,pageSize;
			pageNow = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page<MediaInfo> media = MediaInfo.me.mediaIdAsc(pageNow, pageSize);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", media.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", media.getList());
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
			e.printStackTrace();
		}finally{
			renderJson(mediaBack);
		}
	}
	
	//信息修改更新
	public void update() {
		boolean flag;
		int agency,mediaId;
		mediaId = getParaToInt("mediaId");
		MediaInfo mediaBack = MediaInfo.me.findById(mediaId);
		try{
			agency = getParaToInt("agency");
			switch (agency) {
			case 1:
				String wechatHead = getPara("wechatHead");
				mediaBack.setWechatHead(wechatHead);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 2:
				String mediaName = getPara("mediaName");
				mediaBack.setMediaName(mediaName);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 3:
				String wechatNum  = getPara("wechatNum");
				mediaBack.setWechatNum(wechatNum);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 4:
				String twoCode = getPara("twoCode");
				mediaBack.setTwoCode(twoCode);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 5:
				int fansNum  = getParaToInt("fansNum");
				mediaBack.setFansNum(fansNum);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 6:
				String type  = getPara("medType");
				int typeId = SortClass.me.findSortId(type);
				mediaBack.setMedTypesFir(typeId);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 7:
				String province  = getPara("province");
				String city  = getPara("city");
				int cityId = CityClass.me.findCityId(city);
				int provinceId = ProvinceClass.me.findProvinceId(province);
				mediaBack.setCityId(cityId);
				mediaBack.setProvinceId(provinceId);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 8:
				break;
			case 9:
				int schoolId;
				String school  = getPara("school");
				if(school.equals("无")){
					schoolId = 0;
				}else {
					schoolId = SchoolClass.me.findSchoolId(school);
				}
				System.out.println(schoolId);
				mediaBack.setSchoolId(schoolId);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 10:
				int readNum = getParaToInt("readNum");
				mediaBack.setReadNum(readNum);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 11:
				int softMoreFirPrice,hardMoreFirPrice;
				String softMoreFir  = getPara("softMoreFirPrice");
				String hardMoreFir  = getPara("hardMoreFirPrice");
				if(softMoreFir.equals("不接单")){
					softMoreFir="-1";
				}
				if(hardMoreFir.equals("不接单")){
					hardMoreFir="-1";
				}
				softMoreFirPrice = Integer.parseInt(softMoreFir.trim());
				hardMoreFirPrice = Integer.parseInt(hardMoreFir.trim());
				
				mediaBack.setHardMoreFirPrice(hardMoreFirPrice);
				mediaBack.setSoftMoreFirPrice(softMoreFirPrice);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 12:
				int softMoreSecPrice,hardMoreSecPrice;
				String softMoreSec  = getPara("softMoreSecPrice");
				String hardMoreSec  = getPara("hardMoreSecPrice");
				if(softMoreSec.equals("不接单")){
					softMoreSec="-1";
				}
				if(hardMoreSec.equals("不接单")){
					hardMoreSec="-1";
				}
				softMoreSecPrice = Integer.parseInt(softMoreSec);
				hardMoreSecPrice = Integer.parseInt(hardMoreSec);
				mediaBack.setHardMoreSecPrice(hardMoreSecPrice);
				mediaBack.setSoftMoreSecPrice(softMoreSecPrice);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 13:
				int softMoreOtherPrice,hardMoreOtherPrice;
				String softMoreOther  = getPara("softMoreOtherPrice");
				String hardMoreOther  = getPara("hardMoreOtherPrice");
				if(softMoreOther.equals("不接单")){
					softMoreOther="-1";
				}
				if(hardMoreOther.equals("不接单")){
					hardMoreOther="-1";
				}
				softMoreOtherPrice = Integer.parseInt(softMoreOther);
				hardMoreOtherPrice = Integer.parseInt(hardMoreOther);
				mediaBack.setSoftMoreOtherPrice(softMoreOtherPrice);
				mediaBack.setHardMoreOtherPrice(hardMoreOtherPrice);
				flag = mediaBack.update();
				judge(flag);
				break;
			case 14:
				int softSimplePrice,hardSimplePrice;
				String softSimple  = getPara("softSimplePrice");
				String hardSimple  = getPara("hardSimplePrice");
				if(softSimple.equals("不接单")){
					softSimple="-1";
				}
				if(hardSimple.equals("不接单")){
					hardSimple="-1";
				}
					
				softSimplePrice = Integer.parseInt(softSimple);
				hardSimplePrice = Integer.parseInt(hardSimple);
			
				mediaBack.setSoftSimplePrice(softSimplePrice);
				mediaBack.setHardSimplePrice(hardSimplePrice);
				flag = mediaBack.update();
				judge(flag);
				break;
			default:
				break;
			}
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
			e.printStackTrace();
		}
	}
	
	
	//查看自媒体详细信息，刷新	
	@Clear
	public void refresh() {
		MediaInfo mediaBack = new MediaInfo();
		int mediaId,cityId,schoolId,sortId,userId;
		String cityName,schoolName,sortName;
		try {
			mediaId = getParaToInt("colMediaId");
			userId = getParaToInt("userId");
			List<CollectMedia> col = null;
			
			mediaBack = MediaInfo.me.findById(mediaId);
			if((col = CollectMedia.me.judgeNull(userId, mediaId)).isEmpty()){
				mediaBack.put("judgeCol", 0);
			}else {
				mediaBack.put("judgeCol", 1);
			}
		
			cityId = mediaBack.getCityId();
			sortId = mediaBack.getMedTypesFir();
			cityName = CityClass.me.findCityName(cityId);
			sortName = SortClass.me.findSortName(sortId);
			schoolId = mediaBack.getSchoolId();
			if(schoolId==0){
				schoolName = "无";
			}else {
				schoolName = SchoolClass.me.findSchoolName(schoolId);
			}
			
			mediaBack.put("city", cityName);
			mediaBack.put("school", schoolName);
			mediaBack.put("medType", sortName);
			mediaBack.put("CAllBACK_STATUS", 100);
		} catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}
	}

	//自媒体搜索
	@Clear
	public void search(){
		MediaInfo mediaBack = new MediaInfo();
		int pageNow , pageSize, searchType, orderState;
		Page<MediaInfo> media = null;
		
		String priceType;
		int price,fansNum,readNum,isVerification1,isVerification2;
		try {
			
			price = getParaToInt("price",1000000000);
			fansNum = getParaToInt("fansNum",0);
			readNum = getParaToInt("readNum",0);
			isVerification1 = getParaToInt("isVerification1",1);
			isVerification2 = getParaToInt("isVerification2",0);
			priceType = getPara("priceType","hardSimplePrice");
			
			searchType = getParaToInt("searchType");
			String searchKey = getPara("searchKey");
			orderState = getParaToInt("ORDER_STATE", 1);
			String rankBy = getPara("rankBy");
			String order="desc";
			if(orderState==0)
				order="asc";
			String likeStr = "";
			for (int i = 0; i < searchKey.length(); i++) {
				if (i < searchKey.length() - 1) {
					likeStr += "%" + searchKey.charAt(i);
				} else {
					likeStr += "%" + searchKey.charAt(i) + "%";
				}
			}
			System.out.println(likeStr);
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			switch (searchType) {
			case 1:
				media = MediaInfo.me.searchAllMedia(pageNow, pageSize,likeStr);
				break;
			case 2:
				int schoolId = getParaToInt("schoolId");
				media = MediaInfo.me.searchSchoolMedia(pageNow, pageSize, likeStr, schoolId, rankBy,price, fansNum, readNum, isVerification1, isVerification2,priceType);
				break;
			case 3:
				int sortId = getParaToInt("sortId");
				media = MediaInfo.me.searchSortMedia(pageNow, pageSize, likeStr, sortId, rankBy,price, fansNum, readNum, isVerification1, isVerification2,priceType);
				break;
			default:
				break;
			}
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", media.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", media.getList());
		} catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}
		
	}
	
	//根据行业查看自媒体
	@Clear
	public void chooseMediaType() {
		MediaInfo mediaBack = new MediaInfo();
		int pageNow , pageSize, mediaType;
		try{
			mediaType = getParaToInt("sortId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			Page<MediaInfo> media = MediaInfo.me.medType(pageNow, pageSize, mediaType);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", media.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", media.getList());
		}catch(Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		}finally{
			renderJson(mediaBack);
		}
		
	}
	
	//根据学校查看自媒体
	@Clear
	public void chooseMediaSchool() {
		MediaInfo mediaBack = new MediaInfo();
		int pageNow , pageSize, schoolId;
		try{
			schoolId = getParaToInt("schoolId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			Page<MediaInfo> media = MediaInfo.me.medSchool(pageNow, pageSize, schoolId);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", media.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", media.getList());
		}catch(Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		}finally{
			renderJson(mediaBack);
		}
		
	}
	
	//收藏自媒体
	public void collectMedia() {
		boolean flag;
		try{
			CollectMedia colBack = getModel(CollectMedia.class);
			System.out.println(colBack);
			flag = colBack.save();
			if(flag){
				System.out.println(colBack.getColMediaId());
				MediaInfo mediaInfo = MediaInfo.me.findById(colBack.getColMediaId());
				int collectNum = mediaInfo.getCollectNum()+1;
				mediaInfo.setCollectNum(collectNum);
				mediaInfo.update();
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else {
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}	
	}
	
	//收藏自媒体列表
	public void collectMediaList(){
		CollectMedia colBack = new CollectMedia();
		int pageNow,pageSize,userId;
		try{
			userId = getParaToInt("userId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			Page<CollectMedia> collect = CollectMedia.me.collectMediaList(pageNow, pageSize, userId);
			colBack.put("CAllBACK_STATUS", 100);
			colBack.put("TOTAL_COUNT", collect.getTotalRow());
			colBack.put("GET_ARRAY_DATA", collect.getList());
		}catch(Exception e){
			colBack.put("CAllBACK_STATUS", -100);
		}finally{
			renderJson(colBack);
		}
	}
	
	//取消收藏自媒体
	public void delCollectMedia() {
		boolean flag;
		CollectMedia colBack = new CollectMedia();
		int collectId,userId,colMediaId = 0;
		try{
			userId = getParaToInt("userId");
			colMediaId = getParaToInt("colMediaId");
			collectId = CollectMedia.me.findCollectId(userId, colMediaId);
			System.out.println(collectId);
			flag = colBack.deleteById(collectId);
			if(flag){
				MediaInfo mediaInfo = MediaInfo.me.findById(colMediaId);
				int collectNum = mediaInfo.getCollectNum()-1;
				mediaInfo.setCollectNum(collectNum);
				mediaInfo.update();
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else {
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	
	//判断
	private void judge(boolean flag) {
		if(flag){
			renderJson("{\"CAllBACK_STATUS\":100}");
		}else {
			renderJson("{\"CAllBACK_STATUS\":200}");
		}
	}

}
