package com.media.controller;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.*;

@Clear
public class SchoolMediaController extends Controller {
	
	public void index() {
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	/**
	 * 显示全部省份
	 * @return renderJson
	 */		
	public void ProvinceListAllJson() throws Exception{	
		Back provinceBack = new Back();
		try {
			List<ProvinceClass> mediaList = ProvinceClass.me.ProvinceListAll();
			provinceBack.put("CAllBACK_STATUS", 100);
			provinceBack.put("GET_ARRAY_DATA", mediaList);
		}catch (Exception e) {
			provinceBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(provinceBack);
		}		
	}
	
//	分页，显示全部省份	
//	public void provinceListJson() {		
//		renderJson(ProvinceClass.me.provinceList(getParaToInt(0, 1), 10));
//	}	

//	分页，显示全部城市
//	public void cityListAllJson() {
//		renderJson(CityClass.me.cityList(getParaToInt(0, 1), 10));
//	}
	
	/**
	 * 显示全部城市，provinceId为限定条件
	 * @Json provinceId
	 * @return renderJson
	 */		
	public void cityListJson() throws Exception{	
		Back cityBack = new Back();
		String provinceId = null;
		try {
			provinceId = getPara("PROVINCE_ID");
			List<CityClass> mediaList = CityClass.me.cityListAll(provinceId);
			cityBack.put("CAllBACK_STATUS", 100);
			cityBack.put("GET_ARRAY_DATA", mediaList);
		}catch (Exception e) {
			cityBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(cityBack);
		}		
	}

//	分页，显示全部学校	
//	public void schoolListAllJson() {
//		renderJson(SchoolClass.me.schoolListAll(getParaToInt(0, 1), 10));
//	}
	
	/**
	 * 分页，显示全部学校，cityId为限定条件
	 * @Json pageNumber,pageSize,cityId
	 * @return renderJson
	 */		
	public void schoolListJson() throws Exception{	
		Back schoolBack = new Back();
		int pageNumber = 0;
		int pageSize = 0;
		String cityId = null;
		try {
			cityId = getPara("CIRY_ID");
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page <SchoolClass> mediaInfo = SchoolClass.me.schoolList(pageNumber, pageSize, cityId);
			schoolBack.put("CAllBACK_STATUS", 100);
			schoolBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			schoolBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			schoolBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(schoolBack);
		}		
	}
	
	/**
	 * 根据地区获取学校名
	 * @Json pageNumber,pageSize,cityName
	 * @return renderJson
	 */		
	public void AreaSchoolName() throws Exception{	
		SchoolClass schoolBack = new SchoolClass();
		int pageNumber = 0;
		int pageSize = 0;
		String[] schoolStrings;
		String cityName = null;
		try {
			cityName = getPara("CITY_NAME");
			List <SchoolClass> schoolList = SchoolClass.me.areaSchoolName(cityName);
			schoolBack.put("GET_ARRAY_DATA",schoolList);
			schoolBack.put("CAllBACK_STATUS", 100);
		}catch (Exception e) {
			schoolBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(schoolBack);
		}		
	}
	
	
	/**
	 * 分页，显示全部学校，cityName为限定条件
	 * @Json pageNumber,pageSize,cityName
	 * @return renderJson
	 */		
	public void schoolNameJson() throws Exception{	
		Back schoolBack = new Back();
		int pageNumber = 0;
		int pageSize = 0;
		String cityName = null;
		try {
			cityName = getPara("CITY_NAME");
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page <SchoolClass> mediaInfo = SchoolClass.me.schoolNameList(pageNumber, pageSize, cityName);
			schoolBack.put("CAllBACK_STATUS", 100);
			schoolBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			schoolBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			schoolBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(schoolBack);
		}		
	}
	
	/**
	 * 分页，显示自媒体，schoolId为限定条件
	 * @Json pageNumber,pageSize,schoolId
	 * @return renderJson
	 */		
	public void mediaSchoolListJson() throws Exception{	
		Back mediaBack = new Back();
		int pageNumber = 0;
		int pageSize = 0;
		String schoolId = null;
		try {
			schoolId = getPara("SCHOOL_ID");
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page <MediaInfo> mediaInfo = MediaInfo.me.mediaSchoolList(pageNumber, pageSize, schoolId);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}		
	}
	
	/**
	 * 分页，显示自媒体，mediaName为限定条件，中文参数需要带“引号”	
	 * @Json pageNumber,pageSize,schoolId,mediaName
	 * @return renderJson
	 */		
	public void mediaNameSchoolListJson() throws Exception{	
		Back mediaBack = new Back();
		int pageNumber = 0;
		int pageSize = 0;
		String schoolId = null;
		String mediaName = null;
		try {
			schoolId = getPara("SCHOOL_ID");
			mediaName = getPara("MEDIA_NAME");
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page <MediaInfo> mediaInfo = MediaInfo.me.mediaNameSchoolList(pageNumber, pageSize, schoolId, mediaName);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}		
	}
	
	/**
	 * 分页，显示自媒体，fansNum和schoolId限定条件排序
	 * @Json pageNumber,pageSize,schoolId,orderState
	 * @return renderJson
	 */		
	public void fansNumSchoolListJson() throws Exception{	
		MediaInfo mediaBack = new MediaInfo();		
		int pageNumber = 0;
		int pageSize =0;
		String schoolId = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			schoolId = getPara("schoolId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.fansNumSchoolList(pageNumber, pageSize, schoolId, order);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}		
	}	

	/**
	 * 分页，显示自媒体，readNum和schoolId为限定条件排序
	 * @Json pageNumber,pageSize,schoolId,orderState
	 * @return renderJson
	 */		
	public void readNumSchoolListJson() throws Exception{	
		MediaInfo mediaBack = new MediaInfo();		
		int pageNumber = 0;
		int pageSize =0;
		String schoolId = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			schoolId = getPara("schoolId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.readNumSchoolList(pageNumber, pageSize, schoolId, order);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}		
	}	
	
	/**
	 * 分页，显示自媒体，softMoreOtherPrice和schoolId为限定条件排序
	 * @Json pageNumber,pageSize,schoolId,orderState
	 * @return renderJson
	 */		
	public void priceSchoolListJson() throws Exception{	
		MediaInfo mediaBack = new MediaInfo();		
		int pageNumber = 0;
		int pageSize =0;
		String schoolId = null;
		int orderState = 1;		
		try {
			pageNumber = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			schoolId = getPara("schoolId");
			orderState = getParaToInt("ORDER_STATE", 1);	
			String order="desc";
			if(orderState==0)
				order="asc";
			Page <MediaInfo> mediaInfo = MediaInfo.me.priceSchoolList(pageNumber, pageSize, schoolId, order);
			mediaBack.put("CAllBACK_STATUS", 100);
			mediaBack.put("TOTAL_COUNT", mediaInfo.getTotalRow());
			mediaBack.put("GET_ARRAY_DATA", mediaInfo.getList());
		}catch (Exception e) {
			mediaBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(mediaBack);
		}		
	}
}
