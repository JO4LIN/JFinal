package com.media.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.media.model.base.BaseIndentInfo;

@SuppressWarnings("serial")
public class IndentInfo extends BaseIndentInfo<IndentInfo>{
	
	public static final IndentInfo me = new IndentInfo();

	
	public Page<IndentInfo> paginateUserWait(int pageNumber, int pageSize, int userId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,wechatHead,mediaName,progress", 
				"from tb_indent_information,tb_media_info where tb_indent_information.mediaId=tb_media_info.mediaId "
				+"and tb_indent_information.progress = '3' and tb_indent_information.userId = '"+userId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateUserDoing(int pageNumber, int pageSize, int userId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,wechatHead,mediaName,progress", 
				"from tb_indent_information,tb_media_info where tb_indent_information.mediaId=tb_media_info.mediaId "
				+ "and tb_indent_information.progress = '0' and tb_indent_information.userId = '"+userId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateUserFinish(int pageNumber, int pageSize, int userId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,wechatHead,mediaName,progress", 
				"from tb_indent_information,tb_media_info where tb_indent_information.mediaId=tb_media_info.mediaId "
				+ "and tb_indent_information.progress = '1' and tb_indent_information.userId = '"+userId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateUserFail(int pageNumber, int pageSize, int userId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,wechatHead,mediaName,progress", 
				"from tb_indent_information,tb_media_info where tb_indent_information.mediaId=tb_media_info.mediaId "
				+ "and tb_indent_information.progress = '2' and tb_indent_information.userId = '"+userId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateMediaWait(int pageNumber, int pageSize, int mediaId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,userHead,userName,progress", 
				"from tb_indent_information,tb_media_info,tb_user_info where tb_indent_information.mediaId=tb_media_info.mediaId and tb_user_info.userId = tb_indent_information.userId "
				+ "and tb_indent_information.progress = '3' and tb_indent_information.mediaId = '"+mediaId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateMediaDoing(int pageNumber, int pageSize, int mediaId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,userHead,userName,progress", 
				"from tb_indent_information,tb_media_info,tb_user_info where tb_indent_information.mediaId=tb_media_info.mediaId and tb_user_info.userId = tb_indent_information.userId "
				+ "and tb_indent_information.progress = '0' and tb_indent_information.mediaId = '"+mediaId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateMediaFinish(int pageNumber, int pageSize, int mediaId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,userHead,userName,progress", 
				"from tb_indent_information,tb_media_info,tb_user_info where tb_indent_information.mediaId=tb_media_info.mediaId and tb_user_info.userId = tb_indent_information.userId "
				+ "and tb_indent_information.progress = '1' and tb_indent_information.mediaId = '"+mediaId+"' order by indentId desc");
	}
	
	public Page<IndentInfo> paginateMediaFail(int pageNumber, int pageSize, int mediaId) { 
		return paginate(pageNumber, pageSize, "select indentId,title,price,date,userHead,userName,progress", 
				"from tb_indent_information,tb_media_info,tb_user_info where tb_indent_information.mediaId=tb_media_info.mediaId and tb_user_info.userId = tb_indent_information.userId "
				+ "and tb_indent_information.progress = '2' and tb_indent_information.mediaId = '"+mediaId+"' order by indentId desc");
	}
	
	
	public List<IndentInfo> findIndent(int indentId) { 
		return find("select graphicsTypes,indentId,tb_indent_information.userId,tb_media_info.mediaId,title,tb_indent_information.intro,proDate,link,price,wechatHead,mediaName"+
				" from tb_indent_information,tb_media_info where tb_indent_information.mediaId=tb_media_info.mediaId "
				+ "and tb_indent_information.indentId = '"+indentId+"'");
	}
	
	public List<IndentInfo> findTask(int indentId) { 
		return find("select graphicsTypes,indentId,tb_user_info.userId,mediaId,title,tb_indent_information.intro,proDate,link,price,userHead,userName"+
				" from tb_indent_information,tb_user_info where tb_indent_information.userId=tb_user_info.userId "
				+ "and tb_indent_information.indentId = '"+indentId+"'");
	}
}
