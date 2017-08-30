package com.media.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BaseReportUser <M extends BaseReportUser<M>> extends Model<M> implements IBean{
	
	public void setReportId(java.lang.Integer reportId) {
		set("reportId", reportId);
	}
	public java.lang.Integer getReportId() {
		return get("reportId");
	}
	
	public void setRepUserId(java.lang.Integer repUserId) {
		set("repUserId", repUserId);
	}
	public java.lang.Integer getRepUserId() {
		return get("repUserId");
	}
	
	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	public java.lang.Integer getUserId() {
		return get("userId");
	}
	
	public void setReportType(java.lang.String reportType) {
		set("reportType", reportType);
	}
	public java.lang.String getReportType() {
		return get("reportType");
	}
	
	public void setReportIntro(java.lang.String reportIntro) {
		set("reportIntro", reportIntro);
	}
	public java.lang.String getReportIntro() {
		return get("reportIntro");
	}
	
	public void setReportEvidenceFir(java.lang.String reportEvidenceFir) {
		set("reportEvidenceFir", reportEvidenceFir);
	}
	public java.lang.String getReportEvidenceFir() {
		return get("reportEvidenceFir");
	}
	
	public void setReportEvidenceSec(java.lang.String reportEvidenceSec) {
		set("reportEvidenceSec", reportEvidenceSec);
	}
	public java.lang.String getReportEvidenceSec() {
		return get("reportEvidenceSec");
	}
	
	public void setReportEvidenceThi(java.lang.String reportEvidenceThi) {
		set("reportEvidenceThi", reportEvidenceThi);
	}
	public java.lang.String getReportEvidenceThi() {
		return get("reportEvidenceThi");
	}
	
}
