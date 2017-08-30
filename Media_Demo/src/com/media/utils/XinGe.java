package com.media.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TagTokenPair;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

public class XinGe {

		
		//单个设备下发通知消息
		public static JSONObject demoPushSingleDeviceNotification(String deviceToken,Message message)
		{	
			XingeApp xinge = new XingeApp(2100212313, "-1");	
			JSONObject ret = xinge.pushSingleDevice(deviceToken, message);
			return (ret);
		}
		
		//单个设备下发通知消息
		public static JSONObject demoPushSingleDeviceIOS(String deviceToken,MessageIOS message)
		{
		/*	MessageIOS message = new MessageIOS();
			message.setExpireTime(86400);
			message.setAlert("ios test");
			message.setBadge(1);
			message.setSound("beep.wav");
			TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
			message.addAcceptTime(acceptTime1);
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("key1", "value1");
			custom.put("key2", 2);
			message.setCustom(custom);*/
			
			XingeApp xinge = new XingeApp(2100212313, "-1");	
			JSONObject ret = xinge.pushSingleDevice(deviceToken, message, XingeApp.IOSENV_DEV);
			return (ret);
		}
		
		//单个设备下发通知Intent
		//注意：setIntent()的内容需要使用intent.toUri(Intent.URI_INTENT_SCHEME)方法来得到序列化后的Intent(自定义参数也包含在Intent内）
		//    终端收到后可通过intent.parseUri()来反序列化得到Intent
		
		//下发单个账号
		public static JSONObject demoPushSingleAccount(String account, Message message) {
			XingeApp xinge = new XingeApp(2100212313, "-1");
		/*	Message message = new Message();
			message.setExpireTime(86400);
			message.setTitle("title");
			message.setContent("content");
			message.setType(Message.TYPE_NOTIFICATION);*/
			JSONObject ret = xinge.pushSingleAccount(0, account, message);
			return (ret);
		}
		
		//下发多个账号
		public static JSONObject demoPushAccountList(List<String> accountList, Message message) {
			XingeApp xinge = new XingeApp(2100212313, "-1");
		/*	Message message = new Message();
			message.setExpireTime(86400);
			message.setTitle("title");
			message.setContent("content");
			message.setType(Message.TYPE_MESSAGE);
			List<String> accountList = new ArrayList<String>();
			accountList.add("joelliu");
			accountList.add("joelliu");*/
			JSONObject ret = xinge.pushAccountList(0, accountList, message);
			return (ret);
		}
		
		//下发IOS单个账号
		public static JSONObject demoPushSingleAccountIOS(String account, MessageIOS message) {
		/*	MessageIOS message = new MessageIOS();
			message.setExpireTime(86400);
			message.setAlert("ios test");
			message.setBadge(1);
			message.setSound("beep.wav");
			TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
			message.addAcceptTime(acceptTime1);
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("key1", "value1");
			custom.put("key2", 2);
			message.setCustom(custom);*/
			
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.pushSingleAccount(0, account, message, XingeApp.IOSENV_DEV);
			return (ret);
		}
		
		//下发IOS多个账号
		public static JSONObject demoPushAccountListIOS(List<String> accountList, MessageIOS message) {
		/*	MessageIOS message = new MessageIOS();
			message.setExpireTime(86400);
			message.setAlert("ios test");
			message.setBadge(1);
			message.setSound("beep.wav");
			
			XingeApp xinge = new XingeApp(000, "secret_key");
			List<String> accountList = new ArrayList<String>();
			accountList.add("joelliu");
			accountList.add("joelliu");*/
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.pushAccountList(0, accountList, message, XingeApp.IOSENV_DEV);
			return (ret);
		}
		
		//下发所有设备
		public static JSONObject demoPushAllDevice(Message message)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			
			JSONObject ret = xinge.pushAllDevice(0, message);
			return (ret);
		}
		
		//下发标签选中设备
		public static JSONObject demoPushTags(List<String> tagList,String tagsOp,Message message)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
		/*	List<String> tagList = new ArrayList<String>();
			tagList.add("joelliu");
			tagList.add("phone");*/
			JSONObject ret = xinge.pushTags(0, tagList, "OR", message);
			return (ret);
		}

	    //大批量下发给帐号 // iOS 请构造MessageIOS消息
		public JSONObject demoPushAccountListMultiple() {
			XingeApp xinge = new XingeApp(2100212313, "-1");
			Message message = new Message();
			message.setExpireTime(86400);
			message.setTitle("title");
			message.setContent("content");
			message.setType(Message.TYPE_NOTIFICATION);

			JSONObject ret = xinge.createMultipush(message);
			if (ret.getInt("ret_code") != 0)
	            return (ret);
	        else {
	            JSONObject result = new JSONObject();

	            List<String> accountList1 = new ArrayList<String>();
	            accountList1.add("joelliu1");
	            accountList1.add("joelliu2");
	            // ...
	            result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getInt("push_id"), accountList1));

	            List<String> accountList2 = new ArrayList<String>();
	            accountList2.add("joelliu3");
	            accountList2.add("joelliu4");
	            // ...
	            result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getInt("push_id"), accountList2));
	            return (result);
	        }
		}

		//大批量下发给设备 // iOS 请构造MessageIOS消息
		public static JSONObject demoPushDeviceListMultiple(Message message,List<String> deviceList) {
			XingeApp xinge = new XingeApp(2100212313, "-1");
		/*	Message message = new Message();
			message.setExpireTime(86400);
			message.setTitle("title");
			message.setContent("content");
			message.setType(Message.TYPE_NOTIFICATION);*/

			JSONObject ret = xinge.createMultipush(message);
			if (ret.getInt("ret_code") != 0)
	            return (ret);
	        else {
	            JSONObject result = new JSONObject();

	         /*   List<String> deviceList1 = new ArrayList<String>();
	            deviceList1.add("joelliu1");
	            deviceList1.add("joelliu2");
	            // ...*/
	            result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getInt("push_id"), deviceList));

	         /*   List<String> deviceList2 = new ArrayList<String>();
	            deviceList2.add("joelliu3");
	            deviceList2.add("joelliu4");
	            // ...
	            result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getInt("push_id"), deviceList2));*/
	            return (result);
	        }
		}
		
		//查询消息推送状态
		public static JSONObject demoQueryPushStatus(List<String> pushIdList)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
		/*	List<String> pushIdList = new ArrayList<String>();
			pushIdList.add("390");
			pushIdList.add("389");*/
			JSONObject ret = xinge.queryPushStatus(pushIdList);
			return (ret);
		}
		
		//查询设备数量
		public static JSONObject demoQueryDeviceCount()
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryDeviceCount();
			return (ret);
		}
		
		//查询标签
		public static JSONObject demoQueryTags()
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryTags();
			return (ret);
		}
		
		//查询某个tag下token的数量
		public static JSONObject demoQueryTagTokenNum(String tag)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryTagTokenNum(tag);
			return (ret);
		}
		
		//查询某个token的标签
		public static JSONObject demoQueryTokenTags(String token)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryTokenTags(token);
			return (ret);
		}
		
		//取消定时任务
		public static JSONObject demoCancelTimingPush(String pushId)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.cancelTimingPush(pushId);
			return (ret);
		}

		// 设置标签
		public static JSONObject DemoBatchSetTag(String tag,String token)
	    {
	        XingeApp xinge = new XingeApp(2100212313, "-1");

	        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

	        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
		    pairs.add(new TagTokenPair(tag,token));

	        JSONObject ret = xinge.BatchSetTag(pairs);
	        return (ret);
	    }

	    // 删除标签
		public static JSONObject DemoBatchDelTag(String tag,String token)
	    {
	        XingeApp xinge = new XingeApp(2100212313, "-1");

	        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

	        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
	        pairs.add(new TagTokenPair(tag,token));
	        
	        JSONObject ret = xinge.BatchDelTag(pairs);
	        
	        return (ret);
	    }
		
		//查询某个token的信息
		public static JSONObject DemoQueryInfoOfToken(String token)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryInfoOfToken(token);
			return (ret);
		}

		//查询某个account绑定的token
		public static JSONObject DemoQueryTokensOfAccount(String account)
		{
			XingeApp xinge = new XingeApp(2100212313, "-1");
			JSONObject ret = xinge.queryTokensOfAccount(account);
			return (ret);
		}

		//删除某个account绑定的token
		public static JSONObject DemoDeleteTokenOfAccount(String account, String token)
		{
			XingeApp xinge = new XingeApp(2100208788, "-1");
			JSONObject ret = xinge.deleteTokenOfAccount(account, token);
			return (ret);
		}
		
		//删除某个account绑定的所有token
		public static JSONObject DemoDeleteAllTokensOfAccount(String account)
		{
			XingeApp xinge = new XingeApp(2100208788, "-1");
			JSONObject ret = xinge.deleteAllTokensOfAccount(account);
			return (ret);
		}
		
	/*	public Demo()
		{
			message1 = new Message();
			message1.setType(Message.TYPE_NOTIFICATION);
			Style style = new Style(1);
			style = new Style(3,1,0,1,0);
			ClickAction action = new ClickAction();
			action.setActionType(ClickAction.TYPE_ACTIVITY);
			action.setActivity("MainActivity");
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("key1", "value1");
			custom.put("key2", 2);
			message1.setTitle("title");
			message1.setContent("大小");
			message1.setStyle(style);
			message1.setAction(action);
			message1.setCustom(custom);
			TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
			message1.addAcceptTime(acceptTime1);
			
			message2 = new Message();
			message2.setType(Message.TYPE_NOTIFICATION);
			message2.setTitle("title");
			message2.setContent("通知点击执行Intent测试");
			style = new Style(1);
			action = new ClickAction();
			action.setActionType(ClickAction.TYPE_INTENT);
			action.setIntent("intent:10086#Intent;scheme=tel;action=android.intent.action.DIAL;S.key=value;end");
			message2.setStyle(style);
			message2.setAction(action);
		}
		
		private Message message1;
		private Message message2;*/
	

}
