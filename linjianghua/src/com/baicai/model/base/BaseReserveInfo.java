package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseReserveInfo <M extends BaseReserveInfo<M>> extends Model<M> implements IBean {
	public void setReserveId(java.lang.Integer reserveId) {
		set("reserveId", reserveId);
	}
	public java.lang.Integer getReserveId() {
		return get("reserveId");
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
	
	public void setReserveTime(java.lang.String reserveTime) {
		set("reserveTime", reserveTime);
	}
	public java.lang.String getReserveTime() {
		return get("reserveTime");
	}
}
