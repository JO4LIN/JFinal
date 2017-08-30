package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseSceneInfo;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class SceneInfo extends BaseSceneInfo<SceneInfo>{

	public static final SceneInfo me = new SceneInfo();
	
	
	public int findBiggestId() {
		return findFirst("select sceneId from scene_info order by sceneId desc").getSceneId();
	}
	
	public List<SceneInfo> findSceneInfo() {
		return find("select * from scene_info order by sceneId asc");
	}
	
	public List<SceneInfo> findSceneByid(int sceneId) {
		return find("select * from scene_info where sceneId = '"+sceneId+"' order by sceneId desc");
	}
	
	public List<SceneInfo> findSceneByHouseStyle(String houseStyle) {
		return find("select * from scene_info,scene_pic where houseStyle = '"+houseStyle+"' "
				    + "and scene_pic.sceneId = scene_info.sceneId and scene_pic.remark = '封面图'");
	}
	
	public Page<SceneInfo> findSceneByHouseStyle(String houseStyle, int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select * ", "from scene_info,scene_pic where houseStyle = '"+houseStyle+"' "
			    + "and scene_pic.sceneId = scene_info.sceneId and scene_pic.remark = '封面图'");
	}

}
