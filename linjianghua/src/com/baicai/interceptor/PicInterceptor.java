package com.baicai.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class PicInterceptor  implements Interceptor{

	@Override
	public void intercept(Invocation inv){
		if(inv.getController().getSessionAttr("pic_manage")){
			inv.invoke();
		}else{
			inv.getController().renderText("权限不足");
		}
	}
}
