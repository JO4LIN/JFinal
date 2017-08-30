package com.media.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.NewsClass;

@Clear
public class NewsController extends Controller{

	public void index() {
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	//干货列表
	public void activeNews(){
		NewsClass newsBack = new NewsClass();
		int pageNow,pageSize;
		try{
			pageNow = getParaToInt("KEY_PAGE_NOW",1);
			pageSize = getParaToInt("KEY_PAGE_SIZE",10);
			Page<NewsClass> news = NewsClass.me.newsList(pageNow, pageSize);
			newsBack.put("CAllBACK_STATUS", 100);
			newsBack.put("TOTAL_COUNT", news.getTotalRow());
			newsBack.put("GET_ARRAY_DATA", news.getList());
		}catch(Exception e){
			renderJson("{\"CAllBACK_STATUS\":-100}");
			e.printStackTrace();
		}finally{
			renderJson(newsBack);
		}
		
	}
	
}
