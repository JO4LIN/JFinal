package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseUserInfo <M extends BaseUserInfo<M>> extends Model<M> implements IBean {
	
	public void setUserPsd(java.lang.String userPsd) {
		set("userPsd", userPsd);
	}
	public java.lang.String getUserPsd() {
		return get("userPsd");
	}
	
	public void setRealName(java.lang.String realName) {
		set("realName", realName);
	}
	public java.lang.String getRealName() {
		return get("realName");
	}
	
	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}
	public java.lang.String getPhone() {
		return get("phone");
	}
	
	public void setEmail(java.lang.String email) {
		set("email", email);
	}
	public java.lang.String getEmail() {
		return get("email");
	}
	
	public void setQq(java.lang.String qq) {
		set("qq", qq);
	}
	public java.lang.String getQq() {
		return get("qq");
	}
}
