package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseIndentInfo  <M extends BaseIndentInfo<M>> extends Model<M> implements IBean{

	public void setIndentId(java.lang.Integer indentId) {
		set("indentId", indentId);
	}
	public java.lang.Integer getIndentId() {
		return get("indentId");
	}
	
	public void setMediaId(java.lang.Integer mediaId) {
		set("mediaId", mediaId);
	}
	public java.lang.Integer getMediaId() {
		return get("mediaId");
	}

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	public java.lang.Integer getUserId() {
		return get("userId");
	}
	
	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	public java.lang.String getTitle() {
		return get("title");
	}
	
	public void setIntro(java.lang.String intro) {
		set("intro", intro);
	}
	public java.lang.String getIntro() {
		return get("intro");
	}
	
	public void setPrice(java.lang.Integer price) {
		set("price", price);
	}
	public java.lang.Integer getPrice() {
		return get("price");
	}
	
	public void setGraphicsTypes(java.lang.String graphicsTypes) {
		set("graphicsTypes", graphicsTypes);
	}
	public java.lang.String getGraphicsTypes() {
		return get("graphicsTypes");
	}
	
	public void setProDate(java.lang.String proDate) {
		set("proDate", proDate);
	}
	public java.lang.String getProDate() {
		return get("proDate");
	}
	
	public void setLink(java.lang.String link) {
		set("link", link);
	}
	public java.lang.String getLink() {
		return get("link");
	}
	
	public void setProgress(java.lang.Integer progress) {
		set("progress", progress);
	}
	public java.lang.Integer getProgress() {
		return get("progress");
	}
	
	public void setDate(java.lang.String date) {
		set("date", date);
	}
	public java.lang.String getDate() {
		return get("date");
	}

}
