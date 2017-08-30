package com.baicai.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.baicai.interceptor.ReserveInterceptor;
import com.baicai.model.HomeBottom;
import com.baicai.model.ReserveInfo;
import com.baicai.model.Reserver;
import com.baicai.model.UserInfo;
import com.baicai.utils.CommonUtils;
import com.baicai.validate.ReserveValidate;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.handler.Handler;
import com.jfinal.upload.UploadFile;
import com.oreilly.servlet.multipart.FilePart;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class ReserveInfoController extends Controller {
	
	
	public void index() {	

	}
	
	//发送短信验证码
	public void sendCode() throws ApiException{
		try{
			String phone = getPara("phone");
			setAttr("code", CommonUtils.alidayuCode(phone));;
			render("../personal.html");
		}catch(Exception e){
			renderText("错误提示：发送失败，请重试");
		}

	}
	
	//查看手机号是否注册
	public boolean checkMobileRegister(String phone){
		try{
			List<UserInfo> user = UserInfo.me.checkPhone(phone);
			if(user.isEmpty()){
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			return false;
		}
	}
	
	
	//自动注册
	@Before(ReserveValidate.class)
	public void autoRegister() throws ApiException {
		boolean flag1,flag2;
		try{
			UserInfo userInfo = new UserInfo();
			String phone = getPara("phone");
			String realName = getPara("realName");
			boolean cost = getParaToBoolean("cost",false);
			int area = getParaToInt("area",0);
			if(checkMobileRegister(phone)){
				String psd = CommonUtils.getStringRandom();
				userInfo.setPhone(phone);
				userInfo.setUserPsd(psd);
				userInfo.setRealName(realName);
				userInfo.setEmail("");
				userInfo.setQq("");
				flag1 = userInfo.save();
				if(flag1){
					ReserveInfo reserveInfo = new ReserveInfo();
					reserveInfo.setPhone(phone);
					reserveInfo.setRealName(realName);
					reserveInfo.setReserveTime(CommonUtils.getDate());
					flag2 = reserveInfo.save();
					if(flag2){
						CommonUtils.alidayuPsd(phone, psd);
						CommonUtils.alidayuTip(Reserver.me.findById(1).getPhone(), phone);
						if (cost) {
							if(area<90){
								setSessionAttr("price", (90-area)*499+area*899*1.072);
							}else if (area>90||area==90) {
								setSessionAttr("price", area*899*1.072);
							}
//							setSessionAttr("price", area*899.56);
							setSessionAttr("area", area);
							List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
							setAttr("listBottom", listBottom.toArray());
							setAttr("CAllBACK_STATUS", 100);
							renderJson();							
						}else {
							setAttr("CAllBACK_STATUS", 100);
							renderJson();
						}
					}
				}else{
					setAttr("CAllBACK_STATUS", -100);
					render("");
				}	
			}else {
				ReserveInfo reserveInfo = new ReserveInfo();
				reserveInfo.setPhone(phone);
				reserveInfo.setRealName(realName);
				reserveInfo.setReserveTime(CommonUtils.getDate());
				if(reserveInfo.save()){
					CommonUtils.alidayuTip(Reserver.me.findById(1).getPhone(), phone);					
					if (cost) {
						setSessionAttr("price", area*899*1.04);
						setSessionAttr("area", area);
						List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
						setAttr("listBottom", listBottom.toArray());
						setAttr("CAllBACK_STATUS", 100);
						renderJson();
					}else {
						setAttr("CAllBACK_STATUS", 100);
						renderJson();
					}
				}
			}
		}catch(Exception e){
			renderText("错误提示：失败，请重试");
		}

	}
	
	
	//查看预约
	@Before(ReserveInterceptor.class)
	public void checkReserve() {
		try{
			List<ReserveInfo> list = ReserveInfo.me.findAllInfo();
			setAttr("list", list);
			render("../admin/appointment.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//删除预约
	public void deteleReserve() {
		try{
			int reserveId = getParaToInt("reserveId");
			if(ReserveInfo.me.deleteById(reserveId)){
			/*	List<ReserveInfo> list = ReserveInfo.me.findAllInfo();
				setAttr("list", list);
				render("../admin/appointment.html");*/
				setAttr("result", 1);
				setAttr("msg", "删除成功");
			}else{
				setAttr("result", 0);
				setAttr("msg", "删除失败");
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
		 
	}

	
}
