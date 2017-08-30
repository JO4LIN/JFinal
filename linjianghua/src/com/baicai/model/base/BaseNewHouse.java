package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseNewHouse <M extends BaseNewHouse<M>> extends Model<M> implements IBean {

	public void setNewhouseId(java.lang.Integer newhouseId) {
		set("newhouseId", newhouseId);
	}

	public java.lang.Integer getNewhouseId() {
		return get("newhouseId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	}
	
}
