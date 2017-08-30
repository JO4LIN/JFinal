package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHomeConstruct <M extends BaseHomeConstruct<M>> extends Model<M> implements IBean{
	
	public void setConstructId(java.lang.Integer constructId) {
		set("constructId", constructId);
	}

	public java.lang.Integer getConstructId() {
		return get("constructId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setContextHref(java.lang.String contextHref) {
		set("contextHref", contextHref);
	}
	
	public java.lang.String getContextHref() {
		return get("contextHref");
	}
	
	public void setLocation(java.lang.String location) {
		set("location", location);
	}
	
	public java.lang.String getLocation() {
		return get("location");
	}
	
	public void setHouseType(java.lang.String houseType) {
		set("houseType", houseType);
	}
	
	public java.lang.String getHouseType() {
		return get("houseType");
	}
	
	public void setArea(java.lang.Integer area) {
		set("area", area);
	}
	
	public java.lang.Integer getArea() {
		return get("area");
	}
	
	public void setSerialNum(java.lang.Integer serialNum) {
		set("serialNum", serialNum);
	}
	
	public java.lang.Integer getSerialNum() {
		return get("serialNum");
	}
}
