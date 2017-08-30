package com.media.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;

/**
 * IndexController
 */
@Clear
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
}





