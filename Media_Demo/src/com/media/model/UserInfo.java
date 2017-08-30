package com.media.model;


import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseUserInfo;

@SuppressWarnings("serial")
public class UserInfo extends BaseUserInfo<UserInfo>{
	public static final UserInfo me = new UserInfo();


	//获取密码
	public String loginInfo(String mobile) {
		return findFirst("select userPassword from tb_user_info where mobile = '"+mobile+"'").getUserPassword();
	}

	//获取userId
	public int findUserId(String mobile) {
		return findFirst("select userId from tb_user_info where mobile = '"+mobile+"'").getUserId();
	}
	
	//检验手机号是否重复
	public List<UserInfo> checkMobile(String mobile) {
		return find("select mobile from tb_user_info where mobile = '"+mobile+"'");
	}
}