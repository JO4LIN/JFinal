package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseScenePic;
import com.jfinal.plugin.activerecord.Db;

public class ScenePic extends BaseScenePic<ScenePic>{

	private static final long serialVersionUID = 1L;
	public static final ScenePic me = new ScenePic();
	
	public List<ScenePic> findScenePic(int sceneId) {
		return find("select * from scene_pic where sceneId = '"+sceneId+"' order by picId asc");
	}
	
	public int deletePic(int sceneId) {
		return Db.update("delete from scene_pic where sceneId = '"+sceneId+"'");
	}
}
