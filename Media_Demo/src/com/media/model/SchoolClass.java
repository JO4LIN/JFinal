package com.media.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseSchoolClass;

/**
 * Generated by JFinal.
 * SchoolClass model.
 */

@SuppressWarnings("serial")
public class SchoolClass extends BaseSchoolClass<SchoolClass> {
	
	public static final SchoolClass me = new SchoolClass();

//	查询，分页，全部学校	
	public Page<SchoolClass> schoolListAll(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from tb_school_class order by schoolId asc");
	}
	
//	查询，分页，学校，传入的cityId为限定条件
	public Page<SchoolClass> schoolList(int pageNumber, int pageSize, String cityId) {
		return paginate(pageNumber, pageSize, "select *", "from tb_school_class where cityId= "+cityId+" order by schoolId asc");
	}
	
//	查询，分页，学校，传入的cityName为限定条件
	public Page<SchoolClass> schoolNameList(int pageNumber, int pageSize, String cityName) {
		return paginate(pageNumber, pageSize, "select *", "from tb_school_class where cityId in (select cityId from tb_city_class where cityName= "+cityName+" ) order by schoolId asc");
	}
	
//	根据地区获取学校名
	public List<SchoolClass> areaSchoolName(String cityName) {
		return find("select schoolName from tb_school_class where cityId in (select cityId from tb_city_class where cityName= '"+cityName+"') order by schoolId asc");
	}
	
	//根据学校名寻找学校ID
	public int findSchoolId(String school) {
		return findFirst("select schoolId from tb_school_class where schoolName='"+school+"'").get("schoolId");
	}

	//根据学校ID寻找学校名
	public String findSchoolName(int schoolId) {
		return findFirst("select schoolName from tb_school_class where schoolId='"+schoolId+"'").getStr("schoolName");
	}
}
