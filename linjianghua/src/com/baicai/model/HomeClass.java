package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeClass;


@SuppressWarnings("serial")
public class HomeClass extends BaseHomeClass<HomeClass>{
	public static final HomeClass me = new HomeClass();
	
	public List<HomeClass> findHomeClass() {
		return find("select * from home_class order by classId asc");
	}
	
	public List<HomeClass> findHomeClassRemark(int remark) {
		return find("select * from home_class where remark = '"+remark+"' order by classId asc");
	}
}
