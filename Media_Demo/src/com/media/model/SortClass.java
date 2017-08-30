package com.media.model;

import com.media.model.base.BaseSortClass;

@SuppressWarnings("serial")
public class SortClass  extends BaseSortClass<SortClass> {
	
	public static final SortClass me = new SortClass();
	
	//根据类别名寻找类别ID
	public int findSortId(String sort) {
		return findFirst("select sortId from tb_sort_class where sortName='"+sort+"'").get("sortId");
	}
	
	//根据类别ID寻找类别名
	public String findSortName(int sortId) {
		return findFirst("select sortName from tb_sort_class where sortId='"+sortId+"'").getStr("sortName");
	}
}
