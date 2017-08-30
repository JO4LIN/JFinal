package com.baicai.controller;

import java.util.List;

import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.NewHouse;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class Hall3dController extends Controller{

	public void index() {
		try{
			render("../admin/index.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
		
	}
	
	@Before(PicInterceptor.class)
	public void checkHall3d() {
		try{
			List<Hall3dInfo> hall3dList = Hall3dInfo.me.findHall3dInfo();
			setAttr("hall3dList", hall3dList);
			render("../admin/hall3d.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
}
