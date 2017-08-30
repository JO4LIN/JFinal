package com.baicai.controller;

import java.util.List;

import org.json.JSONException;

import com.baicai.model.Contract;
import com.baicai.model.Drawing;
import com.baicai.model.Employee;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeBottom;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeClass;
import com.baicai.model.HomeConstruct;
import com.baicai.model.HomeHall3d;
import com.baicai.model.HomePackage;
import com.baicai.model.HomeReal;
import com.baicai.model.NewHouse;
import com.baicai.model.OrderInfo;
import com.baicai.model.SceneInfo;
import com.baicai.model.ScenePic;
import com.baicai.model.UserInfo;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class IndexController extends Controller{

	//旧房加载
	public void old_house() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			List<NewHouse> newHouseList = NewHouse.me.findNewHouse();
			setAttr("newHouseList", newHouseList.toArray());
			System.out.println(newHouseList.get(0).getPicHref());
			render("../oldHouse.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}	
	//3D加载
	public void threeD() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			List<Hall3dInfo> hall3dList = Hall3dInfo.me.findHall3dInfo();
			setAttr("hall3dList", hall3dList.toArray());
			
			render("../threeD.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}	
	
	//实景加载
	public void virtualShow() {
		try{
			int pageNumber = getParaToInt("pageNumber",1);
			int pageSize = getParaToInt("pageSize",9);
			String houseStyle = getPara("houseStyle","beiou");
			Page<SceneInfo> scene = SceneInfo.me.findSceneByHouseStyle(houseStyle,pageNumber,pageSize);
			setAttr("listSceneFont", scene.getList());
			setAttr("total", scene.getTotalPage());
			 
			setAttr("pageNumber", pageNumber);
			setAttr("houseStyle", houseStyle);
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../virtualShow.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}	
	
	//预约
	public void reserve(){
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../reserve.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	//预约成功
	public void reserveSuccess(){
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../reserveSuccess.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	//计算价格
	public void index_cost() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			 
			setAttr("price", getSessionAttr("price"));
			setAttr("area", getSessionAttr("area"));
			render("../index_cost.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}	
	//小白必读
	public void index_read() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../index_read.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}	
	//关于我们
	public void aboutUs() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			setAttr("phonePic", HomeBottom.me.findById(1).getPhonePic());
			render("../aboutUs.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void new_house() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			
			List<NewHouse> newHouseList = NewHouse.me.findNewHouse();
			setAttr("newHouseList", newHouseList.toArray());
			System.out.println(newHouseList.get(0).getPicHref());
			render("../newHouse.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

		
	}
	
	//首页加载
	public void index() {
		try{
			//轮播
			List<HomeCarousel> listCarousel = HomeCarousel.me.findCarousel();
			setAttr("listCarousel", listCarousel.toArray());
			//底部
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			//工地
			List<HomeConstruct> listConstruct = HomeConstruct.me.findHomeConstruct();
			setAttr("listConstruct", listConstruct.toArray());
			//3d
			List<HomeHall3d> listHall3d = HomeHall3d.me.findHomeHall3d();
			setAttr("listHall3d", listHall3d.toArray());
			//套餐
			List<HomePackage> listPackage = HomePackage.me.findHomePackage();
			setAttr("listPackage",listPackage.toArray());
			//实景
			List<HomeReal> listRealStyle0 = HomeReal.me.findHomeRealByStyle(0);
			List<HomeReal> listRealStyle1 = HomeReal.me.findHomeRealByStyle(1);
			List<HomeReal> listRealStyle2 = HomeReal.me.findHomeRealByStyle(2);
			setAttr("listRealStyle0", listRealStyle0.toArray());
			setAttr("listRealStyle1", listRealStyle1.toArray());
			setAttr("listRealStyle2", listRealStyle2.toArray());
			
			//课堂
			List<HomeClass> listClass = HomeClass.me.findHomeClassRemark(0);
			List<HomeClass> listLog = HomeClass.me.findHomeClassRemark(1);
			setAttr("listLog", listLog.toArray());
			setAttr("listClass", listClass.toArray());
			setAttr("phonePic", HomeBottom.me.findById(1).getPhonePic());
			
			render("../index.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//网站显示实景详细内容
	public void WebSceneDetail() throws JSONException {
		try{
			int sceneId = getParaToInt("sceneId");
			List<SceneInfo> sceneInfo = SceneInfo.me.findSceneByid(sceneId);
			
		 	setAttr("listSceneInfo", sceneInfo.toArray());
			List<ScenePic> scenePic = ScenePic.me.findScenePic(sceneId);
			String [] urls = new String[scenePic.size()];
			for(int i=0;i<scenePic.size();i++){
				urls[i] = scenePic.get(i).getPicHref();
			}	
			setAttr("urls", urls);
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//查看订单详情
	public void checkOrderDetail() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			
			String phone = getSessionAttr("phone");	
			System.out.println("-----------"+getSessionAttr("isOrder"));
			if(getSessionAttr("isOrder")){
				setAttr("isOrder", getSessionAttr("isOrder"));
				setAttr("material", "#");
			}else{
				int orderId = OrderInfo.me.findOrderId(phone);
				List<OrderInfo> orderList = OrderInfo.me.findOrder(orderId);
				setAttr("designer", Employee.me.findEmployeeName(orderList.get(0).getDesigner()));
				setAttr("designerPhone", Employee.me.findEmployeePhone(orderList.get(0).getDesigner()));
				setAttr("engineer", Employee.me.findEmployeeName(orderList.get(0).getEngineer()));
				setAttr("engineerPhone", Employee.me.findEmployeePhone(orderList.get(0).getEngineer()));
				setAttr("service", Employee.me.findEmployeeName(orderList.get(0).getService()));
				setAttr("servicePhone", Employee.me.findEmployeePhone(orderList.get(0).getService()));
				setAttr("orderList", orderList.toArray());
				String material  = OrderInfo.me.findById(orderId).getMaterial();
				setAttr("material", material);
				setAttr("isOrder", getSessionAttr("isOrder"));
			}
			setAttr("phone", phone);	
			System.out.println(phone);
			render("../personalInfo/personal.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//查看图纸
	public void checkDarwing() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String phone = getSessionAttr("phone");
			if(getSessionAttr("isOrder")){
				setAttr("isOrder", getSessionAttr("isOrder"));
				setAttr("material", "#");
			}else{
				int orderId = OrderInfo.me.findOrderId(phone);
				List<Drawing> drawingList = Drawing.me.findDrawingByOrder(orderId);
				setAttr("drawingList", drawingList);
				String material  = OrderInfo.me.findById(orderId).getMaterial();
				setAttr("material", material);
				setAttr("isOrder", getSessionAttr("isOrder"));
			}	
			setAttr("phone", phone);
			render("../personalInfo/drawing.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	

	
	
	//查看合同
	public void checkContract() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String phone = getSessionAttr("phone");
			
			if(getSessionAttr("isOrder")){
				setAttr("isOrder", getSessionAttr("isOrder"));
				setAttr("material", "#");
				setAttr("isContract", true);
			}else{
				int orderId = OrderInfo.me.findOrderId(phone);
				
				List<Contract> contractList = Contract.me.findContractByOrder(orderId);
				setAttr("contractList", contractList);
				if(Contract.me.findContractEmpty(orderId)){
					setAttr("contractPic1", "#");
				}else{
					setAttr("contractPic1", contractList.get(0).get("picHref"));
				}
				String material  = OrderInfo.me.findById(orderId).getMaterial();
				setAttr("material", material);
				setAttr("isOrder", getSessionAttr("isOrder"));
			}
			
			setAttr("phone", phone);
			render("../personalInfo/contract.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//个人基础信息
	public void personal_info() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String phone = getSessionAttr("phone");
			List<UserInfo> userList = UserInfo.me.findUserInfo(phone);
			setAttr("userList", userList.toArray());
			setAttr("phone", phone);
			
			if(getSessionAttr("isOrder")){
				setAttr("isOrder", getSessionAttr("isOrder"));
				setAttr("material", "#");
			}else{
				int orderId = OrderInfo.me.findOrderId(phone);
				String material  = OrderInfo.me.findById(orderId).getMaterial();
				setAttr("material", material);
				setAttr("isOrder", getSessionAttr("isOrder"));
			}
			render("../personalInfo/personal_info.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//修改用户信息
	public void updateUserInfo() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String realName = getPara("realName","");
			String email = getPara("email","");
			String qq = getPara("qq","");
			String phone = getSessionAttr("phone");
			UserInfo userInfo = UserInfo.me.findById(phone);
			userInfo.setRealName(realName);
			userInfo.setEmail(email);
			userInfo.setQq(qq);
			setAttr("phone", phone);
			if(userInfo.update()){
				setAttr("CAllBACK_STATUS", 100);
				List<UserInfo> userList = UserInfo.me.findUserInfo(phone);
				setAttr("userList", userList.toArray());
				
				if(getSessionAttr("isOrder")){
					setAttr("isOrder", getSessionAttr("isOrder"));
					setAttr("material", "#");
					renderJson();
//					render("../personalInfo/personal_info.html");
				}else{
					int orderId = OrderInfo.me.findOrderId(phone);
					String material  = OrderInfo.me.findById(orderId).getMaterial();
					setAttr("material", material);
					setAttr("isOrder", getSessionAttr("isOrder"));
					renderJson();
//					render("../personalInfo/personal_info.html");
				}		
			}else{
				//修改失败
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//加载修改密码页面
	public void checkPsd() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String phone = getSessionAttr("phone");
			setAttr("phone", phone);
			if(getSessionAttr("isOrder")){
				setAttr("isOrder", getSessionAttr("isOrder"));
				setAttr("material", "#");
				render("../personalInfo/personal_psd.html");
			}else{	
				int orderId = OrderInfo.me.findOrderId(phone);
				String material  = OrderInfo.me.findById(orderId).getMaterial();
				setAttr("material", material);
				setAttr("isOrder", getSessionAttr("isOrder"));
				render("../personalInfo/personal_psd.html");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
		
		
	}	
	
	//修改密码
	public void updatePsd() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			String phone = getSessionAttr("phone");
			String password = getPara("userPsd");
			String newPassword = getPara("newUserPsd");
			String userPassword = null ;
			userPassword = UserInfo.me.loginInfo(phone);
			setAttr("phone", phone);
			if(userPassword.equals(password)){
				UserInfo userInfo = UserInfo.me.findById(phone);
				userInfo.setUserPsd(newPassword);
				if(userInfo.update()){
					setAttr("CAllBACK_STATUS", 100);			
					if(getSessionAttr("isOrder")){
						setAttr("isOrder", getSessionAttr("isOrder"));
						setAttr("material", "#");
						renderJson();
					}else{
						int orderId = OrderInfo.me.findOrderId(phone);
						String material  = OrderInfo.me.findById(orderId).getMaterial();
						setAttr("material", material);
						setAttr("isOrder", getSessionAttr("isOrder"));
						renderJson();
					}		
				}else{
					//修改失败
				}
				
			}else{
				//原密码错误
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

		
	}
	
}
