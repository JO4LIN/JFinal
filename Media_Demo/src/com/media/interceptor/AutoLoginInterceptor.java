package com.media.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.HashKit;
import com.media.model.Back;
import com.media.model.UserInfo;
import com.media.utils.CookieUtils;


public class AutoLoginInterceptor implements Interceptor{

	public void intercept(Invocation inv){
		if(null != CookieUtils.get(inv.getController(), "user")){
			inv.invoke();
		}else{
			inv.getController().renderJson("{\"CAllBACK_STATUS\":-100}");
		}
	}

}
