package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseEmplyee<M extends BaseEmplyee<M>> extends Model<M> implements
		IBean {

	public void setEmployeeId(java.lang.Integer employeeId) {
		set("employeeId", employeeId);
	}

	public java.lang.Integer getEmployeeId() {
		return get("employeeId");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setRealName(java.lang.String realName) {
		set("realName", realName);
	}

	public java.lang.String getRealName() {
		return get("realName");
	}

	public void setPost(java.lang.String post) {
		set("post", post);
	}

	public java.lang.String getPost() {
		return get("post");
	}

	public void setJurisdiction(java.lang.Integer jurisdiction) {
		set("jurisdiction", jurisdiction);
	}

	public java.lang.Integer getJurisdiction() {
		return get("jurisdiction");
	}

 
	public Boolean getOrder_manage() {
		return get("order_manage");
	}

	public void setOrder_manage(Boolean order_manage) {
		set("order_manage", order_manage);
	}

	public Boolean getPic_manage() {
	 return get("pic_manage");
	}

	public void setPic_manage(Boolean pic_manage) {
		set("pic_manage", pic_manage);
	}

	public Boolean getUser_manage() {
		 return get("user_manage");		
	}

	public void setUser_manage(Boolean user_manage) {
		 
		set("user_manage", user_manage);
	}

	public Boolean getAppointment_manage() {
		return get("appointment_manage");		
	}

	public void setAppointment_manage(Boolean appointment_manage) {
	 	set("appointment_manage", appointment_manage);
	}
	 
	public String getPassword() {
		return get("password");	
	}

	public void setPassword(String password) {
		set("password", password);
	}
}
