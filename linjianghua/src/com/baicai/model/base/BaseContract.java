package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseContract <M extends BaseContract<M>> extends Model<M> implements IBean{

	public void setPicId(java.lang.Integer picId) {
		set("picId", picId);
	}

	public java.lang.Integer getPicId() {
		return get("picId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setOrderId(java.lang.Integer orderId) {
		set("orderId", orderId);
	}

	public java.lang.Integer getsetOrderId() {
		return get("orderId");
	}
	
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	} 
}
