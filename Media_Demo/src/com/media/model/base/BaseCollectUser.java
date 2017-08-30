package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseCollectUser<M extends BaseCollectUser<M>> extends Model<M> implements IBean  {
	
	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}

	public java.lang.Integer getUserId() {
		return get("userId");
	}
	
	public void setColUserId(java.lang.Integer colUserId ) {
		set("colUserId", colUserId );
	}

	public java.lang.Integer getColUserId() {
		return get("colUserId");
	}
	
	public void setCollectId(java.lang.Integer collectId ) {
		set("collectId", collectId );
	}

	public java.lang.Integer getCollectId() {
		return get("collectId");
	}
}
