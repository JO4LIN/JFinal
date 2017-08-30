package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseCollectMedia<M extends BaseCollectMedia<M>> extends Model<M> implements IBean  {
	
	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}

	public java.lang.Integer getUserId() {
		return get("userId");
	}
	
	public void setColMediaId(java.lang.Integer colMediaId ) {
		set("colMediaId", colMediaId);
	}

	public java.lang.Integer getColMediaId() {
		return get("colMediaId");
	}
	
	public void setCollectId(java.lang.Integer collectId ) {
		set("collectId", collectId);
	}

	public java.lang.Integer getCollectId() {
		return get("collectId");
	}
}