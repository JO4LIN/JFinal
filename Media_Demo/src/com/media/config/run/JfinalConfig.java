package com.media.config.run;

import org.apache.log4j.Logger;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.media.config.mapping._MappingKit;
import com.media.config.routes.*;
import com.media.controller.IndexController;
import com.media.interceptor.AutoLoginInterceptor;




/**
 * @author 谢维安
 * 描述：Jfinal API 引导式配置，系统的核心配置，负责生产、加载所有核心组件
 */
public class JfinalConfig extends JFinalConfig {

	private static Logger log = Logger.getLogger(JfinalConfig.class);
	
	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub

		log.info("configConstant 缓存 properties");
    	PropKit.use("init.properties");
//    	log.info("blog的例子");
//    	PropKit.use("a_little_config.txt");

//		log.info("configConstant 设置字符集");
//		constants.setEncoding(ToolString.encoding); 

		log.info("configConstant 设置是否开发模式");
//		constants.setDevMode(PropKit.getBoolean(ConstantInit.config_devMode, false));
		constants.setDevMode(PropKit.getBoolean("devMode", false));

		log.info("configConstant 设置视图类型");
		//constants.setViewType(ViewType.JSP);
		
		//log.info("configConstant 设置json工厂类");
		//constants.setJsonFactory(JFinalJsonFactory.me()); // 默认无需设置
		//constants.setJsonFactory(FastJsonFactory.me()); // FastJson
		//constants.setJsonFactory(JacksonFactory.me()); // Jackson

		log.info("configConstant 设置path相关");
	//	constants.setBaseUploadPath("C:\\Users\\jh\\Desktop\\Jfinal_demo_file");
	//	constants.setBaseUploadPath(PathKit.getWebRootPath() + "/files"); // 上传公共路径
		constants.setBaseDownloadPath(PathKit.getWebRootPath() + "/files"); // 下载公共路径
		constants.setEncoding("utf-8");
		//constants.setBaseViewPath("/jf"); //设置路由公共路径
		
//		log.info("configConstant 视图Beetl设置");
//		constants.setMainRenderFactory(new BeetlRenderFactory());
//		ToolBeetl.regiseter();
		log.info("configConstant 视图设置");
		//constants.setBaseViewPath("/WEB-INF/view");
		
		log.info("configConstant 视图error page设置");
//		constants.setError404View("/common/404.html");
//		constants.setError500View("/common/500.html");

//		log.info("configConstant i18n文件前缀设置设置");
//		constants.setI18nDefaultBaseName(PropKit.get(ConstantInit.config_i18n_filePrefix));
		//constants.setI18nDefaultLocale("zh_CN");
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub

//		log.info("configRoute 注解注册路由");
//		new ControllerPlugin(routes).start(); // 注解路由扫描
		
		log.info("configRoute 手动注册路由");
		routes.add("/", IndexController.class);
		routes.add(new MediaRoutes());
	}

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
		
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new AutoLoginInterceptor());
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers) {
		// TODO Auto-generated method stub

//		log.info("configHandler 全局配置处理器，主要是记录日志和request域值处理");
//		handlers.add(new GlobalHandler());
	}
	
	/**
	 * 系统启动完成后执行
	 */
	public void afterJFinalStart() {
		System.out.println("JFinal启动后 启动自动调度线程 ");
//		ThreadSysLog.startSaveDBThread();
//
//		log.info("afterJFinalStart 系统负载");
//		TimerResources.start();
//		
//		log.info("afterJFinalStart 数据清理");
//		DataClear.start();
	}
	
	/**
	 * 系统关闭前调用
	 */
	public void beforeJFinalStop() {
		System.out.println("调度线程释放");
//		log.info("beforeJFinalStop 释放日志入库线程");
//		ThreadSysLog.setThreadRun(false);
//	
//		log.info("beforeJFinalStop 释放系统负载抓取线程");
//		TimerResources.stop();
//	
//		log.info("beforeJFinalStop 数据清理");
//		DataClear.stop();
	}	
	
	/**
	 * 运行此 main 方法可以启动项目
	 * 说明：
	 * 1. linux 下非root账户运行端口要>1024
	 * 2. idea 中运行webAppDir路径可能需要适当调整，可以设置为WebRoot的绝对路径
	 */
	public static void main(String[] args) {
		//JFinal.start("WebRoot", 80, "/", 5);
		//JFinal.start("D:\\DevelopmentTool\\IntelliJIDEA14.1.4\\IdeaProjects\\JFinalUIBV2\\JFinalUIBV2\\WebContent", 99, "/", 5);
	}
}
