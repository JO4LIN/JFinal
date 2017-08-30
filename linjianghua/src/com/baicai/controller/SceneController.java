package com.baicai.controller;

import java.io.IOException;
import java.util.List;

import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeCarousel;
import com.baicai.model.SceneInfo;
import com.baicai.model.ScenePic;
import com.baicai.utils.CommonUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class SceneController extends Controller{
	
	public void index() {
		
		try{
			render("/personal.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}
	}
	
	
	public void addSceneFont() throws IOException {
		try{
			SceneInfo sceneInfo = getModel(SceneInfo.class,"o");
			int sceneId = 0;
			if(sceneInfo.save()){
				sceneId = SceneInfo.me.findBiggestId();	
				ScenePic scenePic = new ScenePic();
				String remark = "封面图";
				for(int i=0; i<15; i++){
					scenePic.clear();
					scenePic.setPicHref("#");
					scenePic.setSceneId(sceneId);
					scenePic.setRemark(remark);
					scenePic.save();
					remark = "展示图";
				}
				List<ScenePic> pic = ScenePic.me.findScenePic(sceneId);
				setAttr("listScenePic", pic);
				render("../admin/realcase_updatePic.html");
			}else {
				renderText("错误提示：发送失败，请重试");
			}
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}
		
	}
	
	public void deleteScene() {
		try{
			int id = getParaToInt("sceneId");
			SceneInfo sceneInfo = SceneInfo.me.findById(id);
			if(sceneInfo.delete()&&(ScenePic.me.deletePic(id)!=0)){
				setAttr("CAllBACK_STATUS", 100);
				render("../personal.html");
			}else {
				//删除失败
				setAttr("CAllBACK_STATUS", -100);
				render("../personal.html");
			}
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	public void upDateSceneFont() {
		try{
			int sceneId = getParaToInt("sceneId");
			int area = getParaToInt("area");
			String houseType = getPara("houseType");
			String houseStyle = getPara("houseStyle");
			String title = getPara("title");
			String location = getPara("location");
			String material = getPara("material");
			String unit = getPara("unit");
			String designer = getPara("designer");
			String remark = getPara("remark");
				
			SceneInfo sceneInfo = SceneInfo.me.findById(sceneId);

			sceneInfo.setSceneId(sceneId);
			sceneInfo.setArea(area);
			sceneInfo.setTitle(title);
			sceneInfo.setLocation(location);
			sceneInfo.setMaterial(material);
			sceneInfo.setUnit(unit);
			sceneInfo.setDesigner(designer);
			sceneInfo.setRemark(remark);
			sceneInfo.setHouseStyle(houseStyle);
			sceneInfo.setHouseType(houseType);
			
			if(sceneInfo.update()){
				checkSceneAll();
			}else {
				//更改失败
				setAttr("CAllBACK_STATUS", -100);
				render("../personal.html");
			}
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}
		
	}
	
	@Before(PicInterceptor.class)
	public void checkSceneAll() {
		try{
			List<SceneInfo> sceneInfo = SceneInfo.me.findSceneInfo();
			setAttr("listSceneInfo", sceneInfo);
			render("../admin/realcase.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	@Before(PicInterceptor.class)
	public void checkSceneAdd() {
		try{
			render("../admin/realcase_addFont.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}
		
	}
	
	public void checkSceneDetail() {
		try{
			int sceneId = getParaToInt("sceneId");
			List<SceneInfo> sceneInfo = SceneInfo.me.findSceneByid(sceneId);
			setAttr("listSceneInfo", sceneInfo);
			List<ScenePic> scenePic = ScenePic.me.findScenePic(sceneId);
			setAttr("listScenePic", scenePic);
			render("../admin/realcase_details.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	public void checkScenePic() {
		try{
			int sceneId = getParaToInt("sceneId");
			List<ScenePic> scenePic = ScenePic.me.findScenePic(sceneId);
			setAttr("listScenePic", scenePic);
			render("../admin/realcase_updatePic.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	public void checkSceneFont() {
		try{
			int sceneId = getParaToInt("sceneId");
			List<SceneInfo> sceneFont = SceneInfo.me.findSceneByid(sceneId);
			setAttr("listSceneFont", sceneFont);
			render("../admin/realcase_updateFont.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
		
	
	
	//网站分类显示实景
	public void WebScene() {
		try{
			String houseStyle = getPara("houseStyle");
			List<SceneInfo> scene = SceneInfo.me.findSceneByHouseStyle(houseStyle);
			setAttr("listSceneFont", scene);
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	//网站显示实景详细内容
	public void WebSceneDetail() {
		try{
			int sceneId = getParaToInt("sceneId");
			List<SceneInfo> sceneInfo = SceneInfo.me.findSceneByid(sceneId);
			setAttr("listSceneInfo", sceneInfo);
			List<ScenePic> scenePic = ScenePic.me.findScenePic(sceneId);
			setAttr("listScenePic", scenePic);
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
}
