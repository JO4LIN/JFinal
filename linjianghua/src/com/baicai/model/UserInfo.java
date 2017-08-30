package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseUserInfo;
import com.baicai.model.UserInfo;



@SuppressWarnings("serial")
public class UserInfo extends BaseUserInfo<UserInfo>{
	
	public static final UserInfo me = new UserInfo();
	
	//获取密码
	public String loginInfo(String phone) {
		return findFirst("select userPsd from user_info where phone = '"+phone+"'").getUserPsd();
	}
	
	//检验手机号是否重复
	public List<UserInfo> checkPhone(String phone) {
		return find("select phone from user_info where phone = '"+phone+"'");
	}
	
	//获取用户信息
	public List<UserInfo> findUserInfo(String phone) {
		return find("select * from user_info where phone = '"+phone+"'");
	}
	
	//获取全部用户信息
	public List<UserInfo> findAllUserInfo() {
		return find("select * from user_info order by phone asc");
	}
	
	//获取全部用户信息,判空
	public boolean findEmpty() {
		return find("select * from user_info order by phone asc").isEmpty();
	}

}
