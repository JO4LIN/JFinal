package com.baicai.validate;

import java.util.List;

import com.baicai.model.HomeBottom;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidate extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("phone", "phoneMsg", "手机号不能为空");
		validateRegex("phone", "^1[3|4|5|7|8][0-9]{9}$", "phoneMsg", "手机号格式错误");
		validateRequiredString("password", "pwdMsg", "密码不能为空");
		validateRegex("password", "^(\"w){6,16}$", "pwdMsg", "密码格式错误");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.keepPara("phone");
		c.keepPara("password");
		List<HomeBottom> listBottom = HomeBottom.me.findHomeBottom();
		c.setAttr("listBottom", listBottom.toArray());
		c.render("../loginInfo/login.html");
	}

}
