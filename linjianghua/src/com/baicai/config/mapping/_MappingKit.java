package com.baicai.config.mapping;

import com.baicai.model.Contract;
import com.baicai.model.Drawing;
import com.baicai.model.Employee;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeClass;
import com.baicai.model.NewHouse;
import com.baicai.model.OrderInfo;
import com.baicai.model.ReserveInfo;
import com.baicai.model.Reserver;
import com.baicai.model.SceneInfo;
import com.baicai.model.ScenePic;
import com.baicai.model.UserInfo;
import com.baicai.model.HomeBottom;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeConstruct;
import com.baicai.model.HomeHall3d;
import com.baicai.model.HomePackage;
import com.baicai.model.HomeReal;
import com.baicai.model.Wechat;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	public static void mapping(ActiveRecordPlugin arp) {
		
		arp.addMapping("user_info", "phone", UserInfo.class);
		arp.addMapping("reserve_info", "reserveId", ReserveInfo.class);
		arp.addMapping("order_info", "orderId", OrderInfo.class);
		arp.addMapping("home_carousel", "carouselId", HomeCarousel.class);
		arp.addMapping("home_package", "packageId", HomePackage.class);
		arp.addMapping("home_real", "realId", HomeReal.class);
		arp.addMapping("home_construct", "constructId", HomeConstruct.class);
		arp.addMapping("home_hall3d", "hall3dId", HomeHall3d.class);
		arp.addMapping("home_bottom", "bottomId", HomeBottom.class);
		arp.addMapping("scene_info", "sceneId", SceneInfo.class);
		arp.addMapping("scene_pic", "picId", ScenePic.class);
		arp.addMapping("hall3d_info", "hall3dId", Hall3dInfo.class);
		arp.addMapping("home_class", "classId", HomeClass.class);
		arp.addMapping("newhouse_info", "newhouseId", NewHouse.class);
		arp.addMapping("contract_pic", "picId", Contract.class);
		arp.addMapping("drawing_pic", "picId", Drawing.class);
		arp.addMapping("wechat_pic", "picId", Wechat.class);
		arp.addMapping("employee_info", "employeeId", Employee.class);
		arp.addMapping("reserver_phone", "reserverId", Reserver.class);
	}
}
