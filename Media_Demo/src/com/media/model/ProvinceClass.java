package com.media.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseProvinceClass;

/**
 * Generated by JFinal.
 * ProvinceClass model.
 */

@SuppressWarnings("serial")
public class ProvinceClass extends BaseProvinceClass<ProvinceClass> {
	
	public static final ProvinceClass me = new ProvinceClass();

//	查询，全部省份	
	public List<ProvinceClass> ProvinceListAll() {
		return find("select * from tb_province_class order by provinceId asc");
	}
	
	
	public int findProvinceId(String province) {
		return findFirst("select provinceId from tb_province_class where provinceName='"+province+"'").get("provinceId");
	}
	
	public String findProvinceName(int province) {
		return findFirst("select provinceName from tb_province_class where provinceId='"+province+"'").get("provinceName");
	}
	
//	查询，分页，全部省份	
//	public Page<ProvinceClass> provinceList(int pageNumber, int pageSize) {
//		return paginate(pageNumber, pageSize, "select *", "from tb_province_class order by provinceId asc");
//	}
}
