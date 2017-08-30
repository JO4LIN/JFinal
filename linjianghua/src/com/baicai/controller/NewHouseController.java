package com.baicai.controller;

import java.util.List;

import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.NewHouse;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class NewHouseController extends Controller{

	public void index() {
		try{
			render("../admin/index.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
	}
	
	@Before(PicInterceptor.class)
	public void checkNewHouse() {
		try{
			List<NewHouse> newHouseList = NewHouse.me.findNewHouse();
			setAttr("newHouseList", newHouseList);
			render("../admin/newhouse.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
}
