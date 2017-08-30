package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseReserveInfo;

@SuppressWarnings("serial")
public class ReserveInfo extends BaseReserveInfo<ReserveInfo>{
	public static final ReserveInfo me = new ReserveInfo();
	
	public List<ReserveInfo> findAllInfo() {
		return find("select * from reserve_info order by reserveTime asc");
	}
}
