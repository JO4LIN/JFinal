package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeBottom;

@SuppressWarnings("serial")
public class HomeBottom extends BaseHomeBottom<HomeBottom>{

	public static final HomeBottom me = new HomeBottom();
	
	public List<HomeBottom> findHomeBottom() {
		return find("select * from home_bottom order by bottomId asc");
	}
}
