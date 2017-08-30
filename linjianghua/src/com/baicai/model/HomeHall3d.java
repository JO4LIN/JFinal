package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeHall3d;


@SuppressWarnings("serial")
public class HomeHall3d extends BaseHomeHall3d<HomeHall3d>{
	
	public static final HomeHall3d me = new HomeHall3d();
	
	public List<HomeHall3d> findHomeHall3d() {
		return find("select * from home_hall3d order by hall3dId asc");
	}
}
