package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseWechat;

@SuppressWarnings("serial")
public class Wechat extends BaseWechat<Wechat>{

	public static final Wechat me = new Wechat();
	
	public List<Wechat> findWechat() {
		return find("select * from wechat_pic order by picId asc");
	}
}
