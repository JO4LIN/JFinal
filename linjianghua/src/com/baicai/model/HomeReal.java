package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeReal;

@SuppressWarnings("serial")
public class HomeReal extends BaseHomeReal<HomeReal>{

	public static final HomeReal me = new HomeReal();
	
	public List<HomeReal> findHomeRealByStyle(int houseStyleNum) {
		return find("select * from home_real where houseStyleNum = '"+houseStyleNum+"' order by realId asc");
	}
	
	public List<HomeReal> findHomeReal() {
		return find("select * from home_real order by realId asc");
	}
}
