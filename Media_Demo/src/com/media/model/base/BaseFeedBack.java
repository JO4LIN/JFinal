package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseFeedBack <M extends BaseFeedBack<M>> extends Model<M> implements IBean{
	
	public void setFeedId(java.lang.Integer feedId) {
		set("feedId", feedId);
	}
	
	public java.lang.Integer getFeedId() {
		return get("feedId");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	public java.lang.String getContent() {
		return get("content");
	}
	
	public void setContact(java.lang.String contact) {
		set("contact", contact);
	}
	public java.lang.String getContact() {
		return get("contact");
	}
	
}