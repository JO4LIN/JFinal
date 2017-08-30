package com.media.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.media.model.IndentInfo;
import com.media.model.MediaInfo;
import com.media.utils.XinGe;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;

public class IndentController extends Controller{
	
	public void index(){
		renderJson("{\"KEY_STATUS\":100}");
	}

	//刷新查看订单总括页面信息
	public void refresh() {
		int mediaId;	
		IndentInfo indentBack = new IndentInfo();
		try {
			mediaId = getParaToInt("mediaId");
			MediaInfo media = MediaInfo.me.findById(mediaId);
			int indentNum = media.getIndentNum();
			String wechatHead = media.getWechatHead();
			String mediaName = media.getMediaName();
			int userId = media.getUserId();
			indentBack.put("userId", userId);
			indentBack.put("mediaName",mediaName);
			indentBack.put("wechatHead",wechatHead);
			indentBack.put("indentNum",indentNum);
			indentBack.put("CAllBACK_STATUS",100);
		}catch(Exception e){
			indentBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		}finally{
			renderJson(indentBack);
		}
		
	}
	
	//保存订单并推送指定用户
	public void send(){
		boolean flag;
		try{
			IndentInfo indentBack = getModel(IndentInfo.class);
			System.out.println(indentBack);
			flag = indentBack.save();
			if(flag){
				String account = String.valueOf(indentBack.getMediaId());
				int indentId = indentBack.getIndentId();			
				JSONObject ret;
				Message message = new Message();
				message.setType(Message.TYPE_NOTIFICATION);
				Style style = new Style(1);
				style = new Style(3,1,0,1,0);
				ClickAction action = new ClickAction();
				action.setActionType(ClickAction.TYPE_ACTIVITY);
				action.setActivity("com.shengrui.huilian.xinge_receiver.IndentDetails");
				Map<String, Object> custom = new HashMap<String, Object>();
				custom.put("progress", 3);
				custom.put("indentId", indentId);
				message.setTitle("您有新的广告任务");
				message.setContent("请查看");
				message.setStyle(style);
				message.setAction(action);
				message.setCustom(custom);
				TimeInterval acceptTime = new TimeInterval(0,0,23,59);
				message.addAcceptTime(acceptTime);
	
				ret = XinGe.demoPushSingleAccount(String.valueOf(indentBack.getUserId()),message);
				System.out.println(ret);   
				
				MediaInfo mediaInfo = MediaInfo.me.findById(indentBack.getMediaId());
				int indentNum = mediaInfo.getIndentNum()+1;
				mediaInfo.setIndentNum(indentNum);
				mediaInfo.update();
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else {
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
			e.printStackTrace();
		} 
	}
	
	//修改订单进度
	public void progress(){
		int indentId,progress;	
		boolean flag;
		IndentInfo indentBack = new IndentInfo();
		try {
			indentId = getParaToInt("indentId");
			progress = getParaToInt("progress");
			indentBack = IndentInfo.me.findById(indentId);
			indentBack.setProgress(progress);
			flag = indentBack.update();
			if(flag){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else {
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch (Exception e) {
			indentBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} 
		
	}
	
	//查看订单
	public void checkIndent() {
		IndentInfo indentBack = new IndentInfo();
		int indentId;
		try {
			indentId = getParaToInt("indentId");
			List<IndentInfo> indent = IndentInfo.me.findIndent(indentId);
			indentBack = indent.get(0);
			indentBack.put("CAllBACK_STATUS", 100);
		} catch (Exception e) {
			indentBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(indentBack);
		}
	}
	
	//查看任务
	public void checkTask() {
		IndentInfo indentBack = new IndentInfo();
		int indentId;
		try {
			indentId = getParaToInt("indentId");
			List<IndentInfo> indent = IndentInfo.me.findTask(indentId);
			indentBack = indent.get(0);
			indentBack.put("CAllBACK_STATUS", 100);
		} catch (Exception e) {
			indentBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(indentBack);
		}
	}
	
	@Clear
	//根据进度查看任务
	public void proTask(){
		int indentId,progress,pageNow , pageSize, mediaId,userId;	
		IndentInfo indentBack = new IndentInfo();
		boolean flag;
		Page<IndentInfo> indent = null;
		try{	
			//0:进行中    1:已完成    2:失败    3:全部
			progress = getParaToInt("progress");
			mediaId = getParaToInt("mediaId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			switch (progress) {
				case 0:
					indent = IndentInfo.me.paginateMediaDoing(pageNow, pageSize,mediaId);
					break;
				case 1:
					indent = IndentInfo.me.paginateMediaFinish(pageNow, pageSize,mediaId);
					break;
				case 2:
					indent = IndentInfo.me.paginateMediaFail(pageNow, pageSize,mediaId);
					break;
				case 3:
					indent = IndentInfo.me.paginateMediaWait(pageNow, pageSize,mediaId);
					break;
				default:
					break;
			}
			indentBack.put("CAllBACK_STATUS", 100);
			indentBack.put("TOTAL_COUNT", indent.getTotalRow());
			indentBack.put("GET_ARRAY_DATA", indent.getList());
		} catch (Exception e) {
				indentBack.put("CAllBACK_STATUS", -100);
				e.printStackTrace();
		} finally {
			renderJson(indentBack);
		}
	}
	
	@Clear
	//根据进度查看订单
	public void proIndent(){
		int indentId,progress,pageNow , pageSize, userId;	
		IndentInfo indentBack = new IndentInfo();
		boolean flag;
		Page<IndentInfo> indent = null;
		try{
			//0:进行中    1:已完成    2:失败    3:全部
			progress = getParaToInt("progress");
			userId = getParaToInt("userId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			switch (progress) {
				case 0:
					indent = IndentInfo.me.paginateUserDoing(pageNow, pageSize,userId);
					break;
				case 1:
					indent = IndentInfo.me.paginateUserFinish(pageNow, pageSize,userId);
					break;
				case 2:
					indent = IndentInfo.me.paginateUserFail(pageNow, pageSize,userId);
					break;
				case 3:
					indent = IndentInfo.me.paginateUserWait(pageNow, pageSize,userId);
					break;
				default:
					break;
			}
			indentBack.put("CAllBACK_STATUS", 100);
			indentBack.put("TOTAL_COUNT", indent.getTotalRow());
			indentBack.put("GET_ARRAY_DATA", indent.getList());
		} catch (Exception e) {
				indentBack.put("CAllBACK_STATUS", -100);
				e.printStackTrace();
		} finally {
			renderJson(indentBack);
		}
	}
	
}
