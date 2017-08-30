package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHomePackage <M extends BaseHomePackage<M>> extends Model<M> implements IBean{
	
	public void setPackageId(java.lang.Integer packageId) {
		set("packageId", packageId);
	}

	public java.lang.Integer getPackageId() {
		return get("packageId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setSerialNum(java.lang.Integer serialNum) {
		set("serialNum", serialNum);
	}
	
	public java.lang.Integer getSerialNum() {
		return get("serialNum");
	}
}
