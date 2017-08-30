package com.baicai.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class LoginInterceptor implements Interceptor{
	@Override
	public void intercept(Invocation inv){
		
		if(inv.getController().getSessionAttr("isAdminLogin")){
			inv.invoke();
		}else{
			inv.getController().render("../admin/login.html");
		}
	}
}
