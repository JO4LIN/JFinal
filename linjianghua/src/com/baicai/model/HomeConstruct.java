package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeConstruct;

@SuppressWarnings("serial")
public class HomeConstruct extends BaseHomeConstruct<HomeConstruct>{
	
	public static final HomeConstruct me = new HomeConstruct();
	
	public List<HomeConstruct> findHomeConstruct() {
		return find("select * from home_construct order by constructId asc");
	}
}
