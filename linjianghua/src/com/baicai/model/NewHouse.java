package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseNewHouse;

@SuppressWarnings("serial")
public class NewHouse extends BaseNewHouse<NewHouse>{
	public static final NewHouse me = new NewHouse();
	
	public List<NewHouse> findNewHouse() {
		return find("select * from newhouse_info order by newhouseId asc");
	}
}
