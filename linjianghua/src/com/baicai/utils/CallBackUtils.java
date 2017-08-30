package com.baicai.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.baicai.model.Contract;
import com.baicai.model.Drawing;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeBottom;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeClass;
import com.baicai.model.HomeConstruct;
import com.baicai.model.HomeHall3d;
import com.baicai.model.HomePackage;
import com.baicai.model.HomeReal;
import com.baicai.model.NewHouse;
import com.baicai.model.OrderInfo;
import com.baicai.model.ScenePic;
import com.baicai.model.Wechat;

public class CallBackUtils {
	
	public static String httpKey = "http://img.baicai-home.com/";
	
	public static Callback createBasicCallBack(String callbackUrl , int type ,int id, String picHref, String contextHref, String remark){
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        callback.setCallbackHost("oss-cn-shanghai.aliyuncs.com");
        callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},\\\"remark\\\":${x:remark},"
                + "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},\\\"contextHref\\\":${x:contextHref},"
                + "\\\"type\\\":${x:type},\\\"id\\\":${x:id},\\\"picHref\\\":${x:picHref}}");
        callback.setCalbackBodyType(CalbackBodyType.JSON);
        callback.addCallbackVar("x:type", String.valueOf(type));
        callback.addCallbackVar("x:id", String.valueOf(id));
        callback.addCallbackVar("x:picHref", picHref);
        callback.addCallbackVar("x:contextHref", contextHref);
        callback.addCallbackVar("x:remark", remark);
        return callback;
	}
	
	public static Callback createRealCallBack(String callbackUrl , int id, String picHref, String contextHref, int type){
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        callback.setCallbackHost("oss-cn-shanghai.aliyuncs.com");
        callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},"
                + "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},"
                + "\\\"id\\\":${x:id},\\\"type\\\":${x:type},\\\"contextHref\\\":${x:contextHref},"
                + "\\\"picHref\\\":${x:picHref}}");
        callback.setCalbackBodyType(CalbackBodyType.JSON);
        callback.addCallbackVar("x:id", String.valueOf(id));
        callback.addCallbackVar("x:picHref", picHref);
        callback.addCallbackVar("x:type", String.valueOf(type));
        callback.addCallbackVar("x:contextHref", contextHref);
        return callback;
	}
	
	public static Callback createConstructCallBack(String callbackUrl , int id, String picHref ,String contextHref, int type){
		Callback callback = new Callback();
		callback.setCallbackUrl(callbackUrl);
		callback.setCallbackHost("oss-cn-shanghai.aliyuncs.com");
		callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},"
		+ "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},\\\"contextHref\\\":${x:contextHref},"
		+ "\\\"id\\\":${x:id},\\\"type\\\":${x:type},"
		+ "\\\"picHref\\\":${x:picHref}}");
		callback.setCalbackBodyType(CalbackBodyType.JSON);
		callback.addCallbackVar("x:id", String.valueOf(id));
		callback.addCallbackVar("x:picHref", picHref);
		callback.addCallbackVar("x:type", String.valueOf(type));
		callback.addCallbackVar("x:contextHref", contextHref);
		return callback;
	}
	
	public static Callback create3DCallBack(String callbackUrl , int id, String contextHref,
			 String picHref, int type){
		Callback callback = new Callback();
		callback.setCallbackUrl(callbackUrl);
		callback.setCallbackHost("oss-cn-shanghai.aliyuncs.com");
		callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},"
		+ "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},"
		+ "\\\"contextHref\\\":${x:contextHref},\\\"id\\\":${x:id},"
		+ "\\\"type\\\":${x:type},"
		+ "\\\"picHref\\\":${x:picHref}}");
		callback.setCalbackBodyType(CalbackBodyType.JSON);
		callback.addCallbackVar("x:contextHref", contextHref);
		callback.addCallbackVar("x:id", String.valueOf(id));
		callback.addCallbackVar("x:picHref", picHref);
		callback.addCallbackVar("x:type", String.valueOf(type));
		callback.addCallbackVar("x:contextHref", contextHref);
		return callback;
	}
	
	public static boolean updateBasic(JSONObject obj) throws JSONException {
		int type = obj.optInt("type");
		int id = obj.optInt("id");
		String picHref = obj.optString("picHref");
		boolean flag = false;
		switch (type) {
		case 1:
			String contextHref = obj.optString("contextHref");
			HomeCarousel homeCarousel = HomeCarousel.me.findById(id);
			homeCarousel.setPicHref(picHref);
			homeCarousel.setContextHref(contextHref);
			flag = homeCarousel.update();
			break;
		case 2:
			HomePackage homePackage = HomePackage.me.findById(id);
			homePackage.setPicHref(picHref);
			flag = homePackage.update();
			break;
		case 6:
			break;
		case 7:
			HomeBottom homePhone = HomeBottom.me.findById(id);
			homePhone.setPhonePic(picHref);
			flag = homePhone.update();
			break;
		case 8:
			HomeBottom homeBottom = HomeBottom.me.findById(id);
			homeBottom.setWechatCode(picHref);
			flag = homeBottom.update();
			break;
		case 9:
			HomeBottom bottom = HomeBottom.me.findById(id);
			bottom.setConstructCode(picHref);
			flag = bottom.update();
			break;
//		case 10:
//			ScenePic scenePicSave = new ScenePic();
//			scenePicSave.setPicHref(picHref);
//			scenePicSave.setSceneId(id);
//			flag = scenePicSave.save();
//			break;
		case 11:
			ScenePic scenePic = ScenePic.me.findById(id);
			scenePic.setPicHref(picHref);
			flag = scenePic.update();
			break;
		case 12:
			NewHouse newHouse = NewHouse.me.findById(id);
			newHouse.setPicHref(picHref);
			flag = newHouse.update();
			break;
		case 13:
			String constructHref = obj.optString("remark");
			Hall3dInfo hall3dInfo = Hall3dInfo.me.findById(id);
			hall3dInfo.setConstructHref(constructHref);
			hall3dInfo.setFontHref(picHref);
			flag = hall3dInfo.update();
			break;
		case 14:
			Hall3dInfo hall3d = Hall3dInfo.me.findById(id);
			String context = obj.optString("contextHref");
			hall3d.setContextHref(context);
			hall3d.setPicHref(picHref);
			flag = hall3d.update();
			break;
		case 15:
			Wechat wechat = Wechat.me.findById(id);
			wechat.setPicHref(picHref);
			flag = wechat.update();
			break;
		default:
			break;
		}
		return flag;
	}
	
	public static boolean updateConstruct(JSONObject obj) throws JSONException{
		int id = obj.optInt("id");
		String picHref = obj.optString("picHref");
		String contextHref = obj.optString("contextHref");
		HomeConstruct homeConstruct = HomeConstruct.me.findById(id);
		homeConstruct.setPicHref(picHref);
		homeConstruct.setContextHref(contextHref);
		return homeConstruct.update();
	}
	
	public static boolean updateReal(JSONObject obj) throws JSONException{
		int id = obj.optInt("id");
		String picHref = obj.optString("picHref");
		String contextHref = obj.optString("contextHref");
		HomeReal homeReal = HomeReal.me.findById(id);
		homeReal.setPicHref(picHref);
		homeReal.setContextHref(contextHref);
		return homeReal.update();
	}
	
	public static boolean update3D(JSONObject obj) throws JSONException{
		int id = obj.optInt("id");
		String picHref = obj.optString("picHref");
		String contextHref = obj.optString("contextHref");
		HomeHall3d homeHall3d = HomeHall3d.me.findById(id);
		homeHall3d.setPicHref(picHref);
		homeHall3d.setContextHref(contextHref);

		return homeHall3d.update();
	}
	
	
	public static Callback createOrderCallBack(String callbackUrl , int type ,int id, String picHref, String remark){
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        System.out.println("2:"+remark);
        callback.setCallbackHost("oss-cn-shanghai.aliyuncs.com");
        callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},"
                + "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},\\\"remark\\\":${x:remark},"
                + "\\\"type\\\":${x:type},\\\"id\\\":${x:id},\\\"picHref\\\":${x:picHref}}");
        callback.setCalbackBodyType(CalbackBodyType.JSON);
        callback.addCallbackVar("x:type", String.valueOf(type));
        callback.addCallbackVar("x:id", String.valueOf(id));
        callback.addCallbackVar("x:picHref", picHref);
        callback.addCallbackVar("x:remark", remark);
        return callback;
	}
	
	public static boolean updateOrder(JSONObject obj) throws JSONException {
		int type = obj.optInt("type");
		int id = obj.optInt("id");
		String picHref = obj.optString("picHref");
		boolean flag = false;
		switch (type) {
		case 1:
			OrderInfo orderInfo = OrderInfo.me.findById(id);
			orderInfo.setMaterial(picHref);
			flag = orderInfo.update();
			break;
		case 2:
			Contract contractSave = new Contract();
			contractSave.setOrderId(id);
			contractSave.setPicHref(picHref);
			flag = contractSave.save();
			break;
		case 3:
			Contract contract = Contract.me.findById(id);
			contract.setPicHref(picHref);
			flag = contract.update();
			break;
		case 4:
			Drawing drawingSave = new Drawing();
			drawingSave.setOrderId(id);
			drawingSave.setPicHref(picHref);
			String remark = obj.optString("remark");
			System.out.println("3:"+remark);
			drawingSave.setRemark(remark);
			flag = drawingSave.save();
			break;
		case 5:
			Drawing drawing = Drawing.me.findById(id);
			drawing.setPicHref(picHref);
			String remark1 = obj.optString("remark");
			System.out.println("3:"+remark1);
			drawing.setRemark(remark1);
			flag = drawing.update();
			break;
		default:
			break;
		}
		return flag;
	}
}
