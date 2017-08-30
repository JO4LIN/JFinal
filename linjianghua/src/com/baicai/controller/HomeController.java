package com.baicai.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.buf.UDecoder;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.baicai.interceptor.OrderInterceptor;
import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeBottom;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeClass;
import com.baicai.model.HomeConstruct;
import com.baicai.model.HomeHall3d;
import com.baicai.model.HomePackage;
import com.baicai.model.HomeReal;
import com.baicai.model.NewHouse;
import com.baicai.model.ScenePic;
import com.baicai.model.Wechat;
import com.baicai.utils.CallBackUtils;
import com.baicai.utils.CommonUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class HomeController extends Controller{

	
	public void index() {
		try{
			render("../admin/index.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
	}
	
	//更新纯图片
	public void updatePic() throws IOException {
		UploadFile uFile = getFile();
		int id = getParaToInt("id");
		int type = getParaToInt("type");
		String contextHref = getPara("contextHref","#");
		int sceneId = getParaToInt("sceneId",0);
		String remark = getPara("remark","#");
		OSSClient ossClient = new OSSClient(CommonUtils.endpoint, CommonUtils.accessKeyId, CommonUtils.accessKeySecret);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String picHref = CallBackUtils.httpKey + date + ".jpg";
			PutObjectRequest putObjectRequest = new PutObjectRequest("baicai-home", date+".jpg", 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = CallBackUtils.createBasicCallBack(CommonUtils.callbackUrl, type, id, picHref, contextHref,remark);	       
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
		 } catch (OSSException oe) {
		        System.out.println("Caught an OSSException, which means your request made it to OSS, "
		                + "but was rejected with an error response for some reason.");
		        System.out.println("Error Message: " + oe.getErrorCode());
		        System.out.println("Error Code:       " + oe.getErrorCode());
		        System.out.println("Request ID:      " + oe.getRequestId());
		        System.out.println("Host ID:           " + oe.getHostId());
		        //OSS失败
		    } catch (ClientException ce) {
		        System.out.println("Caught an ClientException, which means the client encountered "
		                + "a serious internal problem while trying to communicate with OSS, "
		                + "such as not being able to access the network.");
		        System.out.println("Error Message: " + ce.getMessage());
		      //OSS失败
		    } finally {
		        ossClient.shutdown();
				switch (type) {
				case 1:
					checkCarousel();
					break;
				case 2:
					checkPackage();
					break;
				case 6:
					break;
				case 7:
					checkClass();
					break;
				case 8:
					checkBottom();
					break;
				case 9:
					checkBottom();
					break;
//				case 10:
//					List<ScenePic> scenePic = ScenePic.me.findScenePic(id);
//					setAttr("listScenePic", scenePic.toArray());
//					render("../admin/realcase_updatePic.html");
//					break;
				case 11:
					List<ScenePic> scene = ScenePic.me.findScenePic(sceneId);
					setAttr("listScenePic", scene);
					render("../admin/realcase_updatePic.html");
					break;
				case 12:
					List<NewHouse> newHouseList = NewHouse.me.findNewHouse();
					setAttr("newHouseList", newHouseList);
					render("../admin/newhouse.html");
					break;
				case 13:
					List<Hall3dInfo> hall3dList = Hall3dInfo.me.findHall3dInfo();
					setAttr("hall3dList", hall3dList);
					render("../admin/hall3d.html");
					break;
				case 14:
					List<Hall3dInfo> hall3d = Hall3dInfo.me.findHall3dInfo();
					setAttr("hall3dList", hall3d);
					render("../admin/hall3d.html");
					break;
				case 15:
					List<Wechat> wechatList = Wechat.me.findWechat();
					setAttr("wechatList", wechatList);
					render("../admin/wechat.html");
					break;
				default:
					break;
				}
		    }

	}
	
	
	//删除轮播
	public void deleteCarousel() {
		try{
			int id = getParaToInt("carouselId");
			HomeCarousel carousel = HomeCarousel.me.findById(id);
			carousel.setPicHref("#");
			if(carousel.update()){
				setAttr("CAllBACK_STATUS", 100);
				render("../personal.html");
			}else {
				setAttr("CAllBACK_STATUS", -100);
				render("../personal.html");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//显示轮播
	@Before(PicInterceptor.class)
	public void checkCarousel() {
		try{
			List<HomeCarousel> listCarousel = HomeCarousel.me.findCarousel();
			setAttr("listCarousel", listCarousel.toArray());
			render("../admin/index_banner.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//显示套餐
	@Before(PicInterceptor.class)
	public void checkPackage() {
		try{
			List<HomePackage> listPackage = HomePackage.me.findHomePackage();
			setAttr("listPackage",listPackage.toArray());
			render("../admin/index_package.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
	}
	
	//显示工地
	@Before(PicInterceptor.class)
	public void checkConstruct() {
		try{
			List<HomeConstruct> listConstruct = HomeConstruct.me.findHomeConstruct();
			setAttr("listConstruct", listConstruct.toArray());
			render("../admin/index_construce.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	
	//显示实景
	@Before(PicInterceptor.class)
	public void checkReal() {
		try{
			List<HomeReal> listReal = HomeReal.me.findHomeReal();
			List<HomeReal> listRealStyle0 = HomeReal.me.findHomeRealByStyle(0);
			List<HomeReal> listRealStyle1 = HomeReal.me.findHomeRealByStyle(1);
			List<HomeReal> listRealStyle2 = HomeReal.me.findHomeRealByStyle(2);
			setAttr("listRealStyle0", listRealStyle0.toArray());
			setAttr("listRealStyle1", listRealStyle1.toArray());
			setAttr("listRealStyle2", listRealStyle2.toArray());
			render("../admin/index_real.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//显示3D
	@Before(PicInterceptor.class)
	public void checkHall3d() {
		try{
			List<HomeHall3d> listHall3d = HomeHall3d.me.findHomeHall3d();
			setAttr("listHall3d", listHall3d.toArray());
			render("../admin/index_3D.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//显示课堂
	@Before(PicInterceptor.class)
	public void checkClass() {
		try{
			List<HomeClass> listClass = HomeClass.me.findHomeClassRemark(0);
			List<HomeClass> listLog = HomeClass.me.findHomeClassRemark(1);
			setAttr("listClass", listClass);
			setAttr("listLog", listLog);
			String phonePic = HomeBottom.me.findById(1).getPhonePic();
			setAttr("phonePic", phonePic);
			render("../admin/index_class.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//显示底部
	@Before(PicInterceptor.class)
	public void checkBottom() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../admin/bottominfo.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	//工地图
	public void updateConstruct() throws IOException {
		UploadFile uFile = getFile();
		int id = getParaToInt("constructId");
		String location = getPara("location","");
		String houseType = getPara("houseType","");
		String contextHref = getPara("contextHref","");
		int area = getParaToInt("area",0);
		HomeConstruct homeConstruct = HomeConstruct.me.findById(id);
		homeConstruct.setLocation(location);
		homeConstruct.setHouseType(houseType);
		homeConstruct.setArea(area);
		homeConstruct.update();
		OSSClient ossClient = new OSSClient(CommonUtils.endpoint, CommonUtils.accessKeyId, CommonUtils.accessKeySecret);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String picHref = CallBackUtils.httpKey + date + ".jpg";
			PutObjectRequest putObjectRequest = new PutObjectRequest("baicai-home", date+".jpg", 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = CallBackUtils.createConstructCallBack(CommonUtils.callbackUrl, id, picHref,contextHref, 3);	       
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
		 } catch (OSSException oe) {
		        System.out.println("Caught an OSSException, which means your request made it to OSS, "
		                + "but was rejected with an error response for some reason.");
		        System.out.println("Error Message: " + oe.getErrorCode());
		        System.out.println("Error Code:       " + oe.getErrorCode());
		        System.out.println("Request ID:      " + oe.getRequestId());
		        System.out.println("Host ID:           " + oe.getHostId());
		        //OSS失败
		    } catch (ClientException ce) {
		        System.out.println("Caught an ClientException, which means the client encountered "
		                + "a serious internal problem while trying to communicate with OSS, "
		                + "such as not being able to access the network.");
		        System.out.println("Error Message: " + ce.getMessage());
		        //OSS失败
		    } finally {
		        ossClient.shutdown();
		        checkConstruct();
		    }
		
	}

	
	//实景图
	public void updateReal() throws IOException {
		UploadFile uFile = getFile();
		int id = getParaToInt("realId");
		String location = getPara("location","");
		String houseType = getPara("houseType","");
		String contextHref = getPara("contextHref","");
		String target = getPara("target","");
		int area = getParaToInt("area",0);
		HomeReal homeReal = HomeReal.me.findById(id);
		homeReal.setLocation(location);
		homeReal.setHouseType(houseType);
		homeReal.setArea(area);
		homeReal.setTarget(target);
		homeReal.update();
		OSSClient ossClient = new OSSClient(CommonUtils.endpoint, CommonUtils.accessKeyId, CommonUtils.accessKeySecret);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String picHref = CallBackUtils.httpKey + date + ".jpg";
			PutObjectRequest putObjectRequest = new PutObjectRequest("baicai-home", date+".jpg", 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = CallBackUtils.createRealCallBack(CommonUtils.callbackUrl, id, picHref,contextHref,4);   
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
		 } catch (OSSException oe) {
		        System.out.println("Caught an OSSException, which means your request made it to OSS, "
		                + "but was rejected with an error response for some reason.");
		        System.out.println("Error Message: " + oe.getErrorCode());
		        System.out.println("Error Code:       " + oe.getErrorCode());
		        System.out.println("Request ID:      " + oe.getRequestId());
		        System.out.println("Host ID:           " + oe.getHostId());
		        //OSS失败
		    } catch (ClientException ce) {
		        System.out.println("Caught an ClientException, which means the client encountered "
		                + "a serious internal problem while trying to communicate with OSS, "
		                + "such as not being able to access the network.");
		        System.out.println("Error Message: " + ce.getMessage());
		      //OSS失败
		    } finally {
		        ossClient.shutdown();
		    	checkReal();
		    }
	
	}
	
	
	//3D图
	public void updateHall3d() throws IOException {
		UploadFile uFile = getFile();
		int id = getParaToInt("hall3dId");
		String contextHref = getPara("contextHref");
		OSSClient ossClient = new OSSClient(CommonUtils.endpoint, CommonUtils.accessKeyId, CommonUtils.accessKeySecret);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String picHref = CallBackUtils.httpKey + date + ".jpg";
			PutObjectRequest putObjectRequest = new PutObjectRequest("baicai-home", date+".jpg", 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = CallBackUtils.create3DCallBack(CommonUtils.callbackUrl, id, contextHref, picHref, 5);   
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
		 } catch (OSSException oe) {
		        System.out.println("Caught an OSSException, which means your request made it to OSS, "
		                + "but was rejected with an error response for some reason.");
		        System.out.println("Error Message: " + oe.getErrorCode());
		        System.out.println("Error Code:       " + oe.getErrorCode());
		        System.out.println("Request ID:      " + oe.getRequestId());
		        System.out.println("Host ID:           " + oe.getHostId());
		        //OSS失败
		    } catch (ClientException ce) {
		        System.out.println("Caught an ClientException, which means the client encountered "
		                + "a serious internal problem while trying to communicate with OSS, "
		                + "such as not being able to access the network.");
		        System.out.println("Error Message: " + ce.getMessage());
		      //OSS失败
		    } finally {
		        ossClient.shutdown();
		        checkHall3d();
		    }
		
	}
	
	//课堂
	public void updateClass() {
		try{
			HomeClass homeClass = new HomeClass();
			Integer[] classId = getParaValuesToInt("classId");
			String[] contextHref = getParaValues("contextHref");
			String[] title = getParaValues("title");
			for(int i=0;i<classId.length;i++){
				homeClass.clear();
				homeClass = HomeClass.me.findById(classId[i]);
				homeClass.setContextHref(contextHref[i]);
				homeClass.setTitle(title[i]);
				homeClass.update();
			}
			checkClass();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

		
	}
	
	//底部
	public void updateBottom() {
		try{
			int id = 1;
			String phone = getPara("phone");
			String qq = getPara("qq");
			String email = getPara("email");
			String adress = getPara("adress");
			String copyright = getPara("copyright");
			String recordNum = getPara("recordNum");
			HomeBottom homeBottom = HomeBottom.me.findById(id);
			homeBottom.setAdress(adress);
			homeBottom.setCopyright(copyright);
			homeBottom.setPhone(phone);
			homeBottom.setQq(qq);
			homeBottom.setEmail(email);
			homeBottom.setRecordNum(recordNum);
			if(homeBottom.update()){
				checkBottom();
			}else {
				//更改失败
				setAttr("CAllBACK_STATUS", -100);
				render("../personal.html");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
		
	}
	

	
	
}
