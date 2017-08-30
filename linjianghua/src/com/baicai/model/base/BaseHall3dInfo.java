package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHall3dInfo <M extends BaseHall3dInfo<M>> extends Model<M> implements IBean{

	public void setHall3dId(java.lang.Integer hall3dId) {
		set("hall3dId", hall3dId);
	}

	public java.lang.Integer getHall3dId() {
		return get("hall3dId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setFontHref(java.lang.String fontHref) {
		set("fontHref", fontHref);
	}
	
	public java.lang.String getFontHref() {
		return get("fontHref");
	}
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	}
	
	public void setContextHref(java.lang.String contextHref) {
		set("contextHref", contextHref);
	}
	
	public java.lang.String getContextHref() {
		return get("contextHref");
	}
	
	public void setConstructHref(java.lang.String constructHref) {
		set("constructHref", constructHref);
	}
	
	public java.lang.String getConstructHref() {
		return get("constructHref");
	}
}
