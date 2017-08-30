package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */

@SuppressWarnings("serial")
public abstract class BaseSchoolClass<M extends BaseSchoolClass<M>> extends Model<M> implements IBean {
	
	public void setSchoolId(java.lang.Integer schoolId) {
		set("schoolId", schoolId);
	}

	public java.lang.Integer getSchoolId() {
		return get("schoolId");
	}
	
	public void setProvinceId(java.lang.Integer provinceId) {
		set("provinceId", provinceId);
	}

	public java.lang.Integer getProvinceId() {
		return get("provinceId");
	}
	
	public void setCityId(java.lang.Integer cityId) {
		set("cityId", cityId);
	}

	public java.lang.Integer getCityId() {
		return get("cityId");
	}
	
	public void setSchoolName(java.lang.String schoolName) {
		set("schoolName", schoolName);
	}

	public java.lang.String getSchoolName() {
		return get("schoolName");
	}
}
