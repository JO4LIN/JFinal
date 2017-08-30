package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

public class BaseSceneInfo  <M extends BaseSceneInfo<M>> extends Model<M> implements IBean{

	public void setSceneId(java.lang.Integer sceneId) {
		set("sceneId", sceneId);
	}

	public java.lang.Integer getSceneId() {
		return get("sceneId");
	}
	
	public void setPicHref(java.lang.String picHref) {
		set("picHref", picHref);
	}
	
	public java.lang.String getPicHref() {
		return get("picHref");
	}
	
	public void setHouseType(java.lang.String houseType) {
		set("houseType", houseType);
	}
	
	public java.lang.String getHouseType() {
		return get("houseType");
	}
	
	public void setHouseStyle(java.lang.String houseStyle) {
		set("houseStyle", houseStyle);
	}
	
	public java.lang.String getHouseStyle() {
		return get("houseStyle");
	}
	
	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return get("title");
	}
	
	public void setLocation(java.lang.String location) {
		set("location", location);
	}
	
	public java.lang.String getLocation() {
		return get("location");
	}
	
	public void setMaterial(java.lang.String material) {
		set("material", material);
	}
	
	public java.lang.String getMaterial() {
		return get("material");
	}
	
	public void setUnit(java.lang.String unit) {
		set("unit", unit);
	}
	
	public java.lang.String getUnit() {
		return get("unit");
	}
	
	public void setDesigner(java.lang.String designer) {
		set("designer", designer);
	}
	
	public java.lang.String getDesigner() {
		return get("designer");
	}
	
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return get("remark");
	}
	
	public void setArea(java.lang.Integer area) {
		set("area", area);
	}

	public java.lang.Integer getArea() {
		return get("area");
	}
}
