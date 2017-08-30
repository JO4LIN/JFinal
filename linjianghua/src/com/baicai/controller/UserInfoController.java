package com.baicai.controller;

import java.util.List;

import com.baicai.interceptor.UserInterceptor;
import com.baicai.model.Back;
import com.baicai.model.HomeBottom;
import com.baicai.model.OrderInfo;
import com.baicai.model.UserInfo;
import com.baicai.utils.CommonUtils;
import com.baicai.validate.LoginValidate;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


public class UserInfoController extends Controller {
	
	public void index() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../loginInfo/login.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	public void personal() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../personalInfo/personal.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void personal_info() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../personalInfo/personal_info.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	public void personal_psd() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../personalInfo/personal_psd.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	public void personal_team() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../personalInfo/personal_team.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void registerI() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../loginInfo/register.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	public void forgetPasswordI() {
		try{
			List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
			setAttr("listBottom", listBottom.toArray());
			render("../loginInfo/forgetPassword.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//登录
//	@Before(LoginValidate.class)
	public void login() {
	 try{
			 
			String phone = getPara("phone");
			String password = getPara("userPsd");
			String userPassword = null ;
			System.out.println(phone+"-----"+password);
			userPassword = UserInfo.me.loginInfo(phone);
			if(UserInfo.me.loginInfo(phone).equals(password)){
				setSessionAttr("isLogin", true);
				setSessionAttr("phone", phone);
				setSessionAttr("isOrder", OrderInfo.me.isOrderId(phone));
				setAttr("phone", phone);
				setAttr("CAllBACK_STATUS", 100);				
				renderJson();
			}else {
				//密码错误
				setSessionAttr("isLogin", false);
				setAttr("result", 0);
				setAttr("msg", "输入的账号或密码错误");
				renderJson();				
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
		
	}
	
	//注册
	public void register() {
		boolean flag;
		try{
			UserInfo userInfo = new UserInfo();
			String phone = getPara("phone");
			String userPsd = getPara("userPsd");
			String code = getPara("code");
			if(code.equals(getSessionAttr("code"))){
				userInfo.setPhone(phone);
				userInfo.setUserPsd(userPsd);
				userInfo.setEmail("");
				userInfo.setQq("");
				userInfo.setRealName("白菜用户");
				flag = userInfo.save();
				if(flag){
					setAttr("CAllBACK_STATUS", 100);
					renderJson();
				}else{
					//注册失败
					setAttr("CAllBACK_STATUS", -100);
					renderJson();
				}	
			}else {
				//验证码错误
				setAttr("CAllBACK_STATUS", 200);
				renderJson();
			}
			
		}catch (Exception e) {
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//忘记密码
	public void forgetPassword(){
		boolean flag;
		try{
			String phone = getPara("phone");
			String code = getPara("code");
			if(code.equals(getSessionAttr("code"))){
				UserInfo userInfo = UserInfo.me.findById(phone);
				String userPassword = getPara("userPassword");
				userInfo.setUserPsd(userPassword);
				flag = userInfo.update();
				if(flag){
					setAttr("CAllBACK_STATUS", 100);
					renderJson();
				}else{
					//更改失败
					setAttr("CAllBACK_STATUS", -100);
					renderNull();
				}
			}else {
				//验证码错误
				renderNull();
			}
		}catch (Exception e) {
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//查看手机号是否注册
	public void checkMobileRegister(){
		try{
			String phone = getPara("phone");
			List<UserInfo> user = UserInfo.me.checkPhone(phone);
			System.out.println("---->"+user.isEmpty());
			if(user.size()==0){
				setAttr("CAllBACK_STATUS", 100);
				setAttr("result", 1);
				setAttr("msg", "该用户未注册");
			}else
			{
				setAttr("result", 0);
				setAttr("msg", "该用户已被注册");			
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}		
	}
	
	//发送短信验证码
	public void sendCode() throws ApiException{
		try{
			String phone = getPara("phone");
			setSessionAttr("code", CommonUtils.alidayuCode(phone));
			renderNull();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//修改用户信息
	public void updateUserInfo() {
		try{
			String realName = getPara("realName","");
			String email = getPara("email","");
			String qq = getPara("qq","");
			String phone = getPara("phone");
			UserInfo userInfo = UserInfo.me.findById(phone);
			userInfo.setRealName(realName);
			userInfo.setEmail(email);
			userInfo.setQq(qq);
			if(userInfo.update()){
				setAttr("CAllBACK_STATUS", 100);
				render("../personal.html");
			}else{
				renderText("错误提示：请求失败，请重试");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//修改密码
	public void updatePsd() {
		try{
			String phone = getPara("phone");
			String password = getPara("userPsd");
			String newPassword = getPara("newUserPsd");
			String userPassword = null ;
			userPassword = UserInfo.me.loginInfo(phone);
			if(userPassword.equals(password)){
				UserInfo userInfo = UserInfo.me.findById(phone);
				userInfo.setUserPsd(newPassword);
				userInfo.update();
				setAttr("CAllBACK_STATUS", 100);
				render("../personal.html");
			}else{
				renderText("错误提示：请求失败，请重试");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//用户查询个人信息
	public void checkUserInfo() {
		try{
			String phone = getPara("phone");
			List<UserInfo> userList = UserInfo.me.findUserInfo(phone);
			setAttr("userList", userList);
			render("../personal.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//全部用户信息
	@Before(UserInterceptor.class)
	public void checkAllUserInfo() {
		try{
			if(UserInfo.me.findEmpty()){
				renderText("现无用户");
			}else{
				List<UserInfo> userList = UserInfo.me.findAllUserInfo();
				setAttr("userList", userList);
				System.out.println(userList.get(0).getEmail());
				render("../admin/user_manager.html");
			}
			
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//删除用户信息
	public void deleteUserInfo() {
		try{
			String phone = getPara("phone");
			if(UserInfo.me.deleteById(phone)){
				List<UserInfo> userList = UserInfo.me.findAllUserInfo();
				setAttr("userList", userList);
			//	render("../admin/user_manager.html");
				setAttr("result", "1");
				setAttr("msg", "删除成功");	
				renderJson();
			}else{
				//删除失败			
				setAttr("result", "0");
				setAttr("msg", "删除失败");
				renderJson();				
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void isLogin() {
		try{
			setAttr("result", getSessionAttr("isLogin"));
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void quit() {
		try{
			setSessionAttr("isLogin", false);
			removeSessionAttr("remark");
			removeSessionAttr("phone");
			redirect("/");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
}
