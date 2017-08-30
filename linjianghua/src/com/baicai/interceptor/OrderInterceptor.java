package com.baicai.interceptor;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class OrderInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv){
		if(inv.getController().getSessionAttr("order_manage")){
			inv.invoke();
		}else{
			inv.getController().renderText("权限不足");
		}
	}
}
