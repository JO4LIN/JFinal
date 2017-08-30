package com.baicai.config.routes;

import com.baicai.controller.CallBackController;
import com.baicai.controller.EmployeeController;
import com.baicai.controller.Hall3dController;
import com.baicai.controller.HomeController;
import com.baicai.controller.NewHouseController;
import com.baicai.controller.OrderInfoController;
import com.baicai.controller.ReserveInfoController;
import com.baicai.controller.SceneController;
import com.baicai.controller.UserInfoController;
import com.baicai.controller.IndexController;
import com.baicai.controller.WechatController;
import com.jfinal.config.Routes;




/**
 * 平台路由
 */
public class BaicaiRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
			// 第三个参数为该Controller的视图存放路径
		add("/userInfo", UserInfoController.class);
		add("/reserveInfo", ReserveInfoController.class);
		add("/orderInfo", OrderInfoController.class);
		add("/home", HomeController.class);
		add("/scene", SceneController.class);
		add("/callBack", CallBackController.class);
		add("/newhouse", NewHouseController.class);
		add("/hall3d", Hall3dController.class);
		add("/chat", WechatController.class);
		add("/employee", EmployeeController.class);
		add("/", IndexController.class,"/index");
	}

}
