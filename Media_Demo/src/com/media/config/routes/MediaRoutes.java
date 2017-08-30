package com.media.config.routes;

import com.jfinal.config.Routes;
import com.media.controller.CityMediaController;
import com.media.controller.FeedBackController;
import com.media.controller.IndentController;
import com.media.controller.IndexController;
import com.media.controller.MediaClassController;
import com.media.controller.MediaEnterController;
import com.media.controller.MediaInfoController;
import com.media.controller.NewsController;
import com.media.controller.RegisterController;
import com.media.controller.ReportController;
import com.media.controller.SchoolMediaController;
import com.media.controller.UserChangeController;
import com.media.model.SchoolClass;



/**
 * 平台路由
 */
public class MediaRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
			// 第三个参数为该Controller的视图存放路径
		add("/mediaInfo", MediaInfoController.class);
		add("/schoolMedia", SchoolMediaController.class);
		add("/userInfoChange",UserChangeController.class);
		add("/indentInfo",IndentController.class);
		add("/mediaClass", MediaClassController.class);
		add("/newsClass", NewsController.class);
		add("/feedBack", FeedBackController.class);
		add("/mediaEnter", MediaEnterController.class);
		add("/userEnter", RegisterController.class);
		add("/report", ReportController.class);
		add("/cityMedia", CityMediaController.class);
	}

}
