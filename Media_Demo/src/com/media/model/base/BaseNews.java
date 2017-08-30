package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseNews <M extends BaseNews<M>> extends Model<M> implements IBean{
	
	public void setNewsId(java.lang.Integer newsId) {
		set("newsId", newsId);
	}
	public java.lang.Integer getNewsId() {
		return get("newsId");
	}

	public void setNewsName(java.lang.String newsName) {
		set("newsName", newsName);
	}
	public java.lang.String getNewsName() {
		return get("newsName");
	}
	
	public void setNewsHead(java.lang.String neswHead) {
		set("newsHead", neswHead);
	}
	public java.lang.String getNewsHead() {
		return get("newsHead");
	}
	
	public void setNewsDate(java.lang.String newsDate) {
		set("newsDate", newsDate);
	}
	public java.lang.String getNewsDate() {
		return get("newsDate");
	}
	
	public void setNewsUrl(java.lang.String newsUrl) {
		set("newsUrl", newsUrl);
	}
	public java.lang.String getNewsUrl() {
		return get("newsUrl");
	}
	
	public void setNewsCategory(java.lang.String newsCategory) {
		set("newsCategory", newsCategory);
	}
	public java.lang.String getNewsCategory() {
		return get("newsCategory");
	}
}
