package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHomeClass <M extends BaseHomeClass<M>> extends Model<M> implements IBean{


	public void setClassId(java.lang.Integer classId) {
		set("classId", classId);
	}

	public java.lang.Integer getClassId() {
		return get("classId");
	}
	
	public void setContextHref(java.lang.String contextHref) {
		set("contextHref", contextHref);
	}
	
	public java.lang.String getContextHref() {
		return get("contextHref");
	}
	
	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return get("title");
	}
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	}
}
