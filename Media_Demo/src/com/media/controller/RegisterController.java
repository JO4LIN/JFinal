package com.media.controller;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.media.model.Back;
import com.media.model.MediaInfo;
import com.media.model.UserInfo;
import com.media.utils.CommonUtils;
import com.media.utils.CookieUtils;
import com.media.utils.JsoupGET;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Clear
public class RegisterController extends Controller{
	
	public static String url="http://gw.api.taobao.com/router/rest";
	public static String appkey="23692418";
	public static String secret="3e88fb2d4e248039f0569f5b28dddde2";
	
	public void index(){
		System.out.println(JsoupGET.stringMD5("612943"));
		renderJson("{\"KEY_STATUS\":100}");
	}
	
	//登录
	public void login() {
		Back back = new Back();
		try{
			String mobile = getPara("mobile");
			String password = getPara("userPassword");
			String userPassword = null ;
			try{
				userPassword = UserInfo.me.loginInfo(mobile);
				if(userPassword.equals(password)){
					int userId = UserInfo.me.findUserId(mobile);
					int maxAgeInSeconds = 60*60*24;
					String saveTime = System.currentTimeMillis() + "";
					String encrypt_value = CookieUtils.encrypt(CookieUtils.encrypt_key, saveTime, maxAgeInSeconds+"" , mobile);
					
					String cookieValue = encrypt_value + "#" + saveTime + "#" 
							+ maxAgeInSeconds + "#" + mobile;
					setCookie("user", cookieValue, maxAgeInSeconds, true);
					System.out.println(cookieValue);
					back.put("userId", userId);
					back.put("CAllBACK_STATUS", 100);
				}else{
					back.put("CAllBACK_STATUS", 200);
				}
			}catch (Exception e) {
				back.put("CAllBACK_STATUS", 200);
			}
		}catch (Exception e) {
			back.put("CAllBACK_STATUS", -100);
		}finally{
			renderJson(back);
		}	
	}
	
	//注册
	public void register() {
		boolean flag;
		try{
			UserInfo userInfo = getModel(UserInfo.class);
			flag = userInfo.save();
			if(flag){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":200}");
			}	
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//忘记密码
	public void forgetPassword(){
		boolean flag;
		try{
			String mobile = getPara("mobile");
			int userId = UserInfo.me.findUserId(mobile);
			UserInfo userInfo = UserInfo.me.findById(userId);
			String userPassword = getPara("userPassword");
			userInfo.setUserPassword(userPassword);
			flag = userInfo.update();
			if(flag){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//查看手机号是否注册
	public void checkMobileRegister(){
		try{
			String mobile = getPara("mobile");
			List<UserInfo> user = UserInfo.me.checkMobile(mobile);
			if(user.isEmpty()){
				renderJson("{\"CAllBACK_STATUS\":100}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":200}");
			}
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//查看手机号是否注册
	public void checkMobileForget(){
		try{
			String mobile = getPara("mobile");
			List<UserInfo> user = UserInfo.me.checkMobile(mobile);
			if(user.isEmpty()){
				renderJson("{\"CAllBACK_STATUS\":200}");
			}else{
				renderJson("{\"CAllBACK_STATUS\":100}");
			}
		}catch (Exception e) {
			renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}
	
	//发送短信验证码
	public void sendCode(){
		Back back = new Back();
		try{
			String mobile = getPara("mobile");
			try {
				back.put("code",HashKit.md5(alidayuSMS(mobile)));
			} catch (ApiException e) {
				back.put("CAllBACK_STATUS",200);
				e.printStackTrace();
			}
			back.put("CAllBACK_STATUS",100);
		}catch (Exception e) {
			back.put("CAllBACK_STATUS",-100);
		}finally{
			renderJson(back);
		}
	}
	
	//发送短信验证码并返回验证码
	public String alidayuSMS(String mobile) throws ApiException {
		String codeString;
		String code = CommonUtils.makeCode();
		codeString = "{" + "\"code\":\"" + code + "\",\"product\":\"汇联移动\"}";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
	//	req.setExtend("123456");          //公共回传参数 
		req.setSmsType("normal");         //短信类型，传入值请填写normal
		req.setSmsFreeSignName("注册验证"); //短信签名
		req.setSmsParamString("{\"code\":\"1234\"}");    //短信模板变量
		req.setRecNum(mobile);     //短信接收号码
		req.setSmsTemplateCode("SMS_53715108");    //短信模板ID
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		return code;
	}
	
}
