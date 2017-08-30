package com.media.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.Back;
import com.media.model.MainMediaClass;
import com.media.model.MediaInfo;
import com.media.model.MinorMediaClass;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;

@Clear
public class MediaClassController extends Controller {

	public void index() {
		JSONObject ret;
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	/**
	 * 显示全部主分类
	 * @return renderJson
	 */		
	public void mainMediaClassListAllJson() throws Exception{	
		Back ListBack = new Back();
		try {
			List<MainMediaClass> mediaList = MainMediaClass.me.mainMediaClassListAll();
			ListBack.put("CAllBACK_STATUS", 100);
			ListBack.put("ARRAY_DATA", mediaList);
		}catch (Exception e) {
			ListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(ListBack);
		}		
	}
		
	/**
	 * 分页，显示全部主分类
	 * @Json pageNumber,pageSize
	 * @return renderJson
	 */		
	public void mainMediaClassListJson() throws Exception{	
		Back mediaListBack = new Back();
		int pageNumber = 0;
		int pageSize =0;
		try {
			pageNumber = getParaToInt("PAGE_NUMNER",1);
			pageSize = getParaToInt("PAGE_SIZE",10);
			Page <MainMediaClass> mediaInfo = MainMediaClass.me.mainMediaClassList(pageNumber, pageSize);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_ROW", mediaInfo.getTotalRow());
			mediaListBack.put("ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}		
	
//	分页，显示全部次分类，返回json的方法，不能带参数，如果带参数，访问不了
//	public void minorMediaClassListJson(java.lang.String mainTypesId,int pageNumber) {
////		java.lang.String mainTypesId;int pageNumber;
//		mainTypesId=getPara("mainTypesId", "1");
//		pageNumber=getParaToInt("pageNumber", 1);
//		renderJson(MinorMediaClass.me.minorMediaClassList(pageNumber, 10, mainTypesId));
//	}
	
//	分页，显示全部次分类，mainTypesId为限定条件
//	public void minorMediaClassListJson() {
////		参数获取，使用“0-1”的方式
////		String mainTypesId=getPara(0);
////		int pageNumber=getParaToInt(0, 1);
//		
////		参数获取，使用属性名的方式		
//		String mainTypesId=getPara("mainTypesId", "1");
//		int pageNumber=getParaToInt("pageNumber", 1);
//		renderJson(MinorMediaClass.me.minorMediaClassList(pageNumber, 10, mainTypesId));
//	}
	
	/**
	 * 分页，显示全部次分类，mainTypesId为限定条件
	 * @Json pageNumber,pageSize,mainTypesId
	 * @return renderJson
	 */		
	public void minorMediaClassListJson() throws Exception{	
		Back mediaListBack = new Back();
		int pageNumber = 0;
		int pageSize = 0;
		String mainTypesId = null;
		try {
			mainTypesId = getPara("MAIN_TYPES_ID", "2");
			pageNumber = getParaToInt("PAGE_NUMNER",1);
			pageSize = getParaToInt("PAGE_SIZE",10);
			Page <MinorMediaClass> mediaInfo = MinorMediaClass.me.minorMediaClassList(pageNumber, pageSize, mainTypesId);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_ROW", mediaInfo.getTotalRow());
			mediaListBack.put("ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}
		
	/**
	 * 分页，显示自媒体，mediaName和medTypesFir为限定条件，中文参数需要带“引号”
	 * @Json pageNumber,pageSize,medTypesFir,mediaName
	 * @return renderJson
	 */		
	public void mediaNameClassListJson() throws Exception{	
		Back mediaListBack = new Back();		
		int pageNumber = 0;
		int pageSize =0;
		String medTypesFir = null;
		String mediaName = null;
		try {
			pageNumber = getParaToInt("PAGE_NUMNER",1);
			pageSize = getParaToInt("PAGE_SIZE",10);
			medTypesFir = getPara("MED_TYPES_FIR");
			mediaName = getPara("MEDIA_NAME");
			Page <MediaInfo> mediaInfo = MediaInfo.me.mediaNameClassList(pageNumber, pageSize, medTypesFir, mediaName);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_ROW", mediaInfo.getTotalRow());
			mediaListBack.put("ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}	

	/**
	 * 分页，显示自媒体，fansNum和medTypesFir限定条件排序
	 * @Json pageNumber,pageSize,medTypesFir,orderState
	 * @return renderJson
	 */		
	public void fansNumClassListJson() throws Exception{	
		Back mediaListBack = new Back();		
		int pageNumber = 0;
		int pageSize =0;
		String medTypesFir = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			medTypesFir = getPara("sortId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.fansNumClassList(pageNumber, pageSize, medTypesFir, order);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaListBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}	
		
	/**
	 * 分页，显示自媒体，readNum和medTypesFir为限定条件排序
	 * @Json pageNumber,pageSize,medTypesFir,orderState
	 * @return renderJson
	 */		
	public void readNumClassListJson() throws Exception{	
		Back mediaListBack = new Back();		
		int pageNumber = 0;
		int pageSize =0;
		String medTypesFir = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			medTypesFir = getPara("sortId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.readNumClassList(pageNumber, pageSize, medTypesFir, order);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaListBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}

	/**
	 * 分页，显示自媒体，softMoreOtherPrice和medTypesFir为限定条件排序
	 * @Json pageNumber,pageSize,medTypesFir,orderState
	 * @return renderJson
	 */		
	public void priceClassListJson() throws Exception{	
		Back mediaListBack = new Back();		
		int pageNumber = 0;
		int pageSize =0;
		String medTypesFir = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			medTypesFir = getPara("sortId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.priceClassList(pageNumber, pageSize, medTypesFir, order);
			mediaListBack.put("CAllBACK_STATUS", 100);
			mediaListBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaListBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaListBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaListBack);
		}		
	}
	
	
	//行业、学校自媒体筛选
	public void Screen() throws Exception{
		Back screenListBack = new Back();	
		int pageNumber = 0;
		int pageSize =0;
		String orderType,screenType,priceType;
		int price,fansNum,readNum,isVerification1,isVerification2,screenId;
		try {
			price = getParaToInt("price",1000000000);
			fansNum = getParaToInt("fansNum",0);
			readNum = getParaToInt("readNum",0);
			isVerification1 = getParaToInt("isVerification1",1);
			isVerification2 = getParaToInt("isVerification2",0);
			screenType = getPara("screenType");
			screenId = getParaToInt("screenId");
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			orderType = getPara("ordType","fansNum");
			priceType = getPara("priceType","softSimplePrice");
			Page<MediaInfo> mediaInfo = MediaInfo.me.screenMedia(pageNumber, pageSize, price, fansNum, readNum, isVerification1, isVerification2, screenId, orderType, priceType, screenType);
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
	
	
}
