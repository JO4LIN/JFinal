package com.media.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseCollectUser;

@SuppressWarnings("serial")
public class CollectUser extends BaseCollectUser<CollectUser>{
	public static final CollectUser me = new CollectUser();
	
	
//	查询，分页
	public Page<CollectUser> collectUserList(int pageNumber, int pageSize, int userId) {
		return paginate(pageNumber, pageSize, "select userId,userName,userHead", "from tb_user_info where userId in"
				+ "(select colUserId from tb_collect_user where userId = '"+userId+"') order by userId asc");
	}
	
	public int  findCollectId(int  userId, int colUserId) {
		return findFirst("select collectId from tb_collect_user where userId = '"+userId+"' and colUserId = '"+colUserId+"'").get("collectId");
	}
	
	public List<CollectUser> judgeNull(int  userId, int colUserId){
		return find("select collectId from tb_collect_user where userId = '"+userId+"' and colUserId = '"+colUserId+"'");
	}
}
