package com.media.controller;

import com.jfinal.core.Controller;
import com.media.model.ReportMedia;
import com.media.model.ReportUser;

public class ReportController extends Controller{

	public void index(){
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	//举报自媒体
	public void saveReportMedia(){
		boolean flag;
		try {
			ReportMedia reportMedia = getModel(ReportMedia.class,"report");
			flag = reportMedia.save();
			judge(flag);
		} catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//举报用户
	public void saveReportUser(){
		boolean flag;
		try {
			ReportUser reportUser = getModel(ReportUser.class,"report");
			flag = reportUser.save();
			judge(flag);
		} catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	private void judge(boolean flag) {
		if(flag){
			renderJson("{\"CAllBACK_STATUS\":100}");
		}else {
			renderJson("{\"CAllBACK_STATUS\":200}");
		}
	}
}
