package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseScenePic <M extends BaseScenePic<M>> extends Model<M> implements IBean{

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
	
	public void setSceneId(java.lang.Integer sceneId) {
		set("sceneId", sceneId);
	}

	public java.lang.Integer getSceneId() {
		return get("sceneId");
	}
	
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	}
}
