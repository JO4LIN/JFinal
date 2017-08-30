package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHall3dInfo;

@SuppressWarnings("serial")
public class Hall3dInfo extends BaseHall3dInfo<Hall3dInfo>{

	public static final Hall3dInfo me = new Hall3dInfo();
	
	public List<Hall3dInfo> findHall3dInfo() {
		return find("select * from hall3d_info order by hall3dId asc");
	}
}
