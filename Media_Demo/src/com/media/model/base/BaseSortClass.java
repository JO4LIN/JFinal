package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseSortClass <M extends BaseSortClass<M>> extends Model<M> implements IBean {

	public void setSortId(java.lang.Integer sortId) {
		set("sortId", sortId);
	}

	public java.lang.Integer getSortId() {
		return get("sortId");
	}
	
	public void setSortName(java.lang.String sortName){
		set("sortName", sortName);
	}
	
	public java.lang.Integer getSortName() {
		return get("sortName");
	}

}
