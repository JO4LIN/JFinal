package com.media.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseCollectMedia;

@SuppressWarnings("serial")
public class CollectMedia extends BaseCollectMedia<CollectMedia>{
	public static final CollectMedia me = new CollectMedia();
	
//	查询，分页
	public Page<CollectMedia> collectMediaList(int pageNumber, int pageSize, int userId) {
		return paginate(pageNumber, pageSize, "select mediaId,mediaName,wechatHead", "from tb_media_info where mediaId in"
				+ "(select colMediaId from tb_collect_media where userId = '"+userId+"') order by mediaId asc");
	}
	
	public int  findCollectId(int  userId, int colMediaId) {
		return findFirst("select collectId from tb_collect_media where userId = '"+userId+"' and colMediaId = '"+colMediaId+"'").get("collectId");
	}
	
	public List<CollectMedia> judgeNull(int  userId, int colMediaId){
		return find("select collectId from tb_collect_media where userId = '"+userId+"' and colMediaId = '"+colMediaId+"'");
	}
}


