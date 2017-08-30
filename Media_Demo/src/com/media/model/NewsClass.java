package com.media.model;

import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseNews;

@SuppressWarnings("serial")
public class NewsClass  extends BaseNews<NewsClass>{
	
	public static final NewsClass me = new NewsClass();
	
//	查询，分页，全部干货
	public Page<NewsClass> newsList(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from tb_news_class order by newsId desc");
	}
}
