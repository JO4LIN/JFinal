package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHomeCarousel <M extends BaseHomeCarousel<M>> extends Model<M> implements IBean{

	public void setCarouselId(java.lang.Integer carouselId) {
		set("carouselId", carouselId);
	}

	public java.lang.Integer getCarouselId() {
		return get("carouselId");
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
	
}
