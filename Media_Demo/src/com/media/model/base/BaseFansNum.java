package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseFansNum  <M extends BaseFansNum<M>> extends Model<M> implements IBean {
	
	public void setFansNumId(java.lang.Integer fansNumId) {
		set("fansNumId", fansNumId);
	}
	public java.lang.Integer getFansNumId() {
		return get("fansNumId");
	}
	
	public void setFansNum(java.lang.Integer fansNum) {
		set("fansNum", fansNum);
	}
	public java.lang.Integer getFansNum() {
		return get("fansNum");
	}
	
	public void setFansNumPicture(java.lang.String fansNumPicture){
		set("fansNumPicture", fansNumPicture);
	}
	public java.lang.String getFansNumPicture() {
		return get("fansNumPicture");
	}
	
	public void setMediaName(java.lang.String mediaName) {
		set("mediaName", mediaName);
	}
	public java.lang.String getMediaName() {
		return get("mediaName");
	}

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	public java.lang.Integer getUserId() {
		return get("userId");
	}
}
