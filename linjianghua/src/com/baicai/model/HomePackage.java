package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomePackage;


@SuppressWarnings("serial")
public class HomePackage extends BaseHomePackage<HomePackage>{

	public static final HomePackage me = new HomePackage();
	
	public List<HomePackage> findHomePackage() {
		return find("select * from home_package order by packageId asc");
	}
}
