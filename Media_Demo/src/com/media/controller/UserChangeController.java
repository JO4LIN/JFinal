package com.media.controller;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.media.model.Back;
import com.media.model.CollectMedia;
import com.media.model.CollectUser;
import com.media.model.MediaInfo;
import com.media.model.UserInfo;

public class UserChangeController extends Controller{
	public void index(){
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	//个人信息修改更新
	public void update() {
		boolean flag;
		int agency,userId;
		userId = getParaToInt("userId");
		UserInfo userBack = UserInfo.me.findById(userId);
		try{
			agency = getParaToInt("agency");
			switch (agency) {
			case 1:
				String userHead = getPara("userHead");
				userBack.setUserHead(userHead);
				flag = userBack.update();
				judge(flag);
				break;
			case 2:
				String userName = getPara("userName");
				userBack.setUserName(userName);
				flag = userBack.update();
				judge(flag);
				break;
			case 3:
				String mobile = getPara("mobile");
				userBack.setMobile(mobile);
				flag = userBack.update();
				judge(flag);
				break;
			case 4:
				String realName = getPara("realName");
				userBack.setRealName(realName);
				flag = userBack.update();
				judge(flag);
				break;
			case 5:
				String sex = getPara("sex");
				userBack.setSex(sex);
				flag = userBack.update();
				judge(flag);
				break;
			case 6:
				String tel = getPara("tel");
				userBack.setTel(tel);
				flag = userBack.update();
				judge(flag);
				break;
			case 7:
				String email = getPara("email");
				userBack.setEmail(email);
				flag = userBack.update();
				judge(flag);
				break;
			case 8:
				String qq = getPara("qq");
				userBack.setQq(qq);
				flag = userBack.update();
				judge(flag);
				break;
			case 9:
				String wechat = getPara("wechat");
				userBack.setWechat(wechat);
				flag = userBack.update();
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
	
	//收藏用户
	public void collectUser() {
		boolean flag;
		try{
			CollectUser colBack = getModel(CollectUser.class);
			if(colBack.getColUserId() == colBack.getUserId()){
				renderJson("{\"CAllBACK_STATUS\":200}");
			}else {
				flag = colBack.save();
				if(flag){
					UserInfo userInfo = UserInfo.me.findById(colBack.getColUserId());
					int collectNum = userInfo.getCollectNum()+1;
					userInfo.setCollectNum(collectNum);
					userInfo.update();
					renderJson("{\"CAllBACK_STATUS\":100}");
				}else {
					renderJson("{\"CAllBACK_STATUS\":200}");
				}
			}
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}	
	}
	
	//取消收藏用户
	public void delCollectUser() {
		boolean flag;
		CollectUser colBack = new CollectUser();
		int collectId,userId,colUserId = 0;
		try{
			userId = getParaToInt("userId");
			colUserId = getParaToInt("colUserId");
			collectId = CollectUser.me.findCollectId(userId, colUserId);
			System.out.println(collectId);
			flag = colBack.deleteById(collectId);
			if(flag){
				UserInfo userInfo = UserInfo.me.findById(colUserId);
				int collectNum = userInfo.getCollectNum()-1;
				userInfo.setCollectNum(collectNum);
				userInfo.update();
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else {
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//收藏用户列表
	public void collectUserList(){
		CollectUser colBack = new CollectUser();
		int pageNow,pageSize,userId;
		try{
			userId = getParaToInt("userId");
			pageNow = getParaToInt("KEY_PAGE_NOW", 1);
			pageSize = getParaToInt("KEY_PAGE_SIZE", 5);
			Page<CollectUser> collect = CollectUser.me.collectUserList(pageNow, pageSize, userId);
			colBack.put("CAllBACK_STATUS", 100);
			colBack.put("TOTAL_COUNT", collect.getTotalRow());
			colBack.put("GET_ARRAY_DATA", collect.getList());
		}catch(Exception e){
			colBack.put("CAllBACK_STATUS", -100);
		}finally{
			renderJson(colBack);
		}
	}
	
	//信息刷新	
	@Clear
	public void refresh() {
		List<CollectUser> col = null;
		UserInfo userBack  = new UserInfo();
		int userId,colUserId;
		userId = getParaToInt("userId");
		colUserId = getParaToInt("colUserId");
		try {
			userBack = UserInfo.me.findById(colUserId);
			if((col = CollectUser.me.judgeNull(userId, colUserId)).isEmpty()){
				userBack.put("judgeCol", 0);
			}else {
				userBack.put("judgeCol", 1);
			}
			userBack.put("CAllBACK_STATUS", 100);
		} catch (Exception e) {
			userBack.put("CAllBACK_STATUS", -100);
			e.printStackTrace();
		} finally {
			renderJson(userBack);
		}
	}
	
	//自动登录
	public void autoLogin(){
		Back back = new Back();
		try {
			String cookieValue = getCookie("user");
			String cookieStrings[] = cookieValue.split("#");
			String mobile = cookieStrings[3];
			int userId = UserInfo.me.findUserId(mobile);
			UserInfo userInfo = UserInfo.me.findById(userId);
			back.put("userId", userId);
			back.put("userName",userInfo.getUserName());
			back.put("userHead",userInfo.getUserHead());
			back.put("CAllBACK_STATUS", 100);
		} catch (Exception e) {
			back.put("CAllBACK_STATUS", -100);
		}finally{
			renderJson(back);
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
