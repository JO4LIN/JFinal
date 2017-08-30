package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseUserInfo  <M extends BaseUserInfo<M>> extends Model<M> implements IBean{
	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	public java.lang.Integer getUserId() {
		return get("userId");
	}
	
	public void setUserHead(java.lang.String userHead) {
		set("userHead", userHead);
	}
	public java.lang.String getUserHead() {
		return get("userHead");
	}
	
	public void setUserName(java.lang.String userName) {
		set("userName", userName);
	}
	public java.lang.String getUserName() {
		return get("userName");
	}
	
	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	public java.lang.String getMobile() {
		return get("mobile");
	}
	
	public void setRealName(java.lang.String realName) {
		set("realName", realName);
	}
	public java.lang.String getRealName() {
		return get("realName");
	}
	
	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}
	public java.lang.String getSex() {
		return get("sex");
	}
	
	public void setTel(java.lang.String tel) {
		set("tel", tel);
	}
	public java.lang.String getTel() {
		return get("tel");
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
	
	public void setWechat(java.lang.String wechat) {
		set("wechat", wechat);
	}
	public java.lang.String getWechat() {
		return get("wechat");
	}
	
	public void setUserPassword(java.lang.String userPassword) {
		set("userPassword", userPassword);
	}
	public java.lang.String getUserPassword() {
		return get("userPassword");
	}
	
	public void setCollectNum(java.lang.Integer collectNum) {
		set("collectNum", collectNum);
	}
	public java.lang.Integer getCollectNum() {
		return get("collectNum");
	}
}
