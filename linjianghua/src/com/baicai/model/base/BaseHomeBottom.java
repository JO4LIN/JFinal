package com.baicai.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BaseHomeBottom  <M extends BaseHomeBottom<M>> extends Model<M> implements IBean{

	public void setBottomId(java.lang.Integer bottomId) {
		set("bottomId", bottomId);
	}

	public java.lang.Integer getBottomId() {
		return get("bottomId");
	}
	
	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}
	
	public java.lang.String getPhone() {
		return get("phone");
	}
	
	public void setQq(java.lang.String qq) {
		set("qq", qq);
	}
	
	public java.lang.String getEmail() {
		return get("email");
	}
	public void setEmail(java.lang.String email) {
		set("email", email);
	}
	
	public java.lang.String getQq() {
		return get("qq");
	}
	public void setAdress(java.lang.String adress) {
		set("adress", adress);
	}
	
	public java.lang.String getAdress() {
		return get("adress");
	}
	
	public void setCopyright(java.lang.String copyright) {
		set("copyright", copyright);
	}
	
	public java.lang.String getCopyright() {
		return get("copyright");
	}
	
	public void setRecordNum(java.lang.String recordNum) {
		set("recordNum", recordNum);
	}
	
	public java.lang.String getRecordNum() {
		return get("recordNum");
	}
	
	public void setPhonePic(java.lang.String phonePic) {
		set("phonePic", phonePic);
	}
	
	public java.lang.String getPhonePic() {
		return get("phonePic");
	}
	
	public void setWechatCode(java.lang.String wechatCode) {
		set("wechatCode", wechatCode);
	}
	
	public java.lang.String getWechatCode() {
		return get("wechatCode");
	}
	
	public void setConstructCode(java.lang.String constructCode) {
		set("constructCode", constructCode);
	}
	
	public java.lang.String getConstructCode() {
		return get("constructCode");
	}
}
