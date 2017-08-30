package com.baicai.validate;

import java.util.List;

import com.baicai.model.HomeBottom;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeClass;
import com.baicai.model.HomeConstruct;
import com.baicai.model.HomeHall3d;
import com.baicai.model.HomePackage;
import com.baicai.model.HomeReal;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ReserveValidate extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("phone", "phoneMsg", "手机号不能为空");
		validateRegex("phone", "^1[3|4|5|7|8][0-9]{9}$", "phoneMsg", "手机号格式错误");
		validateRequiredString("realName", "realNameMsg", "姓名不能为空");
		validateRegex("realName", "^[\u4e00-\u9fa5]{2,4}$", "realNameMsg", "姓名格式错误");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
//		String actionKey = getActionKey();
		c.keepPara("phone");
		c.keepPara("realName");
//		System.out.println(actionKey);
		c.renderText("错误提示：请求失败，请重试");
	}

}
