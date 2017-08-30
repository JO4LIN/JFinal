package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseOrderInfo <M extends BaseOrderInfo<M>> extends Model<M> implements IBean {

	public void setOrderId(java.lang.Integer orderId) {
		set("orderId", orderId);
	}
	public java.lang.Integer getOrderId() {
		return get("orderId");
	}
	
	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}
	public java.lang.String getPhone() {
		return get("phone");
	}
	
	public void setProduct(java.lang.String product) {
		set("product", product);
	}
	public java.lang.String getProduct() {
		return get("product");
	}
	
	public void setProgress(java.lang.String progress) {
		set("progress", progress);
	}
	public java.lang.String getProgress() {
		return get("progress");
	}
	
	public void setDesigner(java.lang.Integer designer) {
		set("designer", designer);
	}
	public java.lang.Integer getDesigner() {
		return get("designer");
	}
	
	public void setEngineer(java.lang.Integer engineer) {
		set("engineer", engineer);
	}
	public java.lang.Integer getEngineer() {
		return get("engineer");
	}
	
	public void setService(java.lang.Integer service) {
		set("service", service);
	}
	public java.lang.Integer getService() {
		return get("service");
	}
	
	public void setState(java.lang.String state) {
		set("state", state);
	}
	public java.lang.String getState() {
		return get("state");
	}
	
	public void setMaterial(java.lang.String material) {
		set("material", material);
	}
	public java.lang.String getMaterial() {
		return get("material");
	}
	
	public void setHouseAdress(java.lang.String houseAdress) {
		set("houseAdress", houseAdress);
	}
	public java.lang.String getHouseAdress() {
		return get("houseAdress");
	}
	
	public void setOrderTime(java.lang.String orderTime) {
		set("orderTime", orderTime);
	}
	public java.lang.String getOrderTime() {
		return get("orderTime");
	}
}
