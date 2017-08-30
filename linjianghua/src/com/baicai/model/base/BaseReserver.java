package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseReserver  <M extends BaseReserver<M>> extends Model<M> implements IBean {
	public void setReserverId(java.lang.Integer reserverId) {
		set("reserverId", reserverId);
	}
	public java.lang.Integer getReserverId() {
		return get("reserverId");
	}
	
	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}
	public java.lang.String getPhone() {
		return get("phone");
	}
	
}
