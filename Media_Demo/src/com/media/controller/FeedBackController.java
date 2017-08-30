package com.media.controller;

import com.jfinal.core.Controller;
import com.media.model.Back;
import com.media.model.FeedBack;

public class FeedBackController extends Controller{
	
	public void index(){
		renderJson("{\"KEY_STATUS\":100}");
	}

	//保存反馈
	public void saveFeedBack(){
		try {
			FeedBack feedBack = getModel(FeedBack.class);
			boolean flag;
			flag = feedBack.save();
			if(flag){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		} catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}

	}
	
}
