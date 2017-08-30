package com.baicai.controller;

import java.util.List;

import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.HomeBottom;
import com.baicai.model.NewHouse;
import com.baicai.model.Wechat;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class WechatController extends Controller{

	public void index() {
		try{
			render("../admin/index.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
		
	}
	
	@Before(PicInterceptor.class)
	public void checkWechat() {
		try{
			List<Wechat> wechatList = Wechat.me.findWechat();
			setAttr("wechatList", wechatList);
			render("../admin/wechat.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
}
