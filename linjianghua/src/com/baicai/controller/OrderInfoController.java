package com.baicai.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baicai.interceptor.OrderInterceptor;
import com.baicai.interceptor.ReserveInterceptor;
import com.baicai.model.Contract;
import com.baicai.model.Drawing;
import com.baicai.model.Employee;
import com.baicai.model.Hall3dInfo;
import com.baicai.model.HomeBottom;
import com.baicai.model.NewHouse;
import com.baicai.model.OrderInfo;
import com.baicai.model.ReserveInfo;
import com.baicai.model.ScenePic;
import com.baicai.model.UserInfo;
import com.baicai.utils.CallBackUtils;
import com.baicai.utils.CommonUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class OrderInfoController extends Controller{
	
	public void index() {
		try{
			render("/personal.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	
	}
	
	public void checkOrderByPhone() {
		try{
			String phone = getPara("phone");
			int pageNumber = getParaToInt("pageNumber",1);
			int pageSize = getParaToInt("pageSize",5);
			Page<OrderInfo> orderList = OrderInfo.me.findUserOrder(pageNumber, pageSize, phone);
			setAttr("orderList", orderList);
			render("../personal.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//管理员查看订单详情
	public void checkOrderDetail() {
		try{
			int orderId = getParaToInt("orderId");
			List<OrderInfo> orderList = OrderInfo.me.findOrder(orderId);
			List<Contract> contractList = Contract.me.findContractByOrder(orderId);
			List<Drawing> drawingList = Drawing.me.findDrawingByOrder(orderId);
			
			List<Employee>designerList=Employee.me.findPost("设计师");
			List<Employee>engineerList=Employee.me.findPost("工程师");
			List<Employee>kefuList=Employee.me.findPost("客服");
			
			setAttr("kefu", Employee.me.findEmployeeName(orderList.get(0).getService()));
			setAttr("designer", Employee.me.findEmployeeName(orderList.get(0).getDesigner()));
			setAttr("engineer", Employee.me.findEmployeeName(orderList.get(0).getEngineer()));
			
			setAttr("kefuList", kefuList);	
			setAttr("designerList", designerList);
			setAttr("engineerList", engineerList);
		    setAttr("orderList", orderList.toArray());
			setAttr("contractList", contractList);
			setAttr("drawingList", drawingList);
			render("../admin/order_details.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
	}
	
	//管理员增加订单
	@Before(OrderInterceptor.class)
	public void addOrder() {
		try{
			OrderInfo orderInfo = getModel(OrderInfo.class,"o");
			orderInfo.setOrderTime(CommonUtils.getDate());
			if(orderInfo.save()){
				//queryOrder();
				setAttr("result", 1);
				setAttr("msg", "添加成功");
			}else{
				//增加失败
				//renderText("错误提示：请求失败，请重试");
				setAttr("result", 1);
				setAttr("msg", "添加失败");
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//管理员修改订单
	public void updateOrder() {
		try{
			int orderId = getParaToInt("orderId");
			String product = getPara("product","");
			String progress = getPara("progress","");
			int designer = getParaToInt("designer");
			int engineer = getParaToInt("engineer");
			int service = getParaToInt("service");
			String material = getPara("material","");
			String state = getPara("state","");
			String houseAdress = getPara("houseAdress","");
		 
			OrderInfo orderInfo = OrderInfo.me.findById(orderId);
			orderInfo.setProduct(product);
			orderInfo.setProgress(progress);
			orderInfo.setDesigner(designer);
			orderInfo.setEngineer(engineer);
			orderInfo.setService(service);
			orderInfo.setMaterial(material);
			orderInfo.setState(state);
			orderInfo.setHouseAdress(houseAdress);
			if(orderInfo.update()){
				checkOrderDetail();
			}else{
				renderText("错误提示：失败，请重试");
			}
			
		}catch(Exception e){
			renderText("错误提示：失败，请重试");
		}
		
	}
	
	//查看订单
	@Before(OrderInterceptor.class)
	public void queryOrder() {
//		try{		
			List<OrderInfo> orderList = OrderInfo.me.findAllOrder();
			setAttr("orderList", orderList);
			render("../admin/order_view.html");
//		}catch(Exception e){
//			renderText("错误提示：请求失败，请重试");
//		}
	}
	
	public void deleteOrder() {
		try{
			int orderId = getParaToInt("orderId");
			OrderInfo orderInfo = OrderInfo.me.findById(orderId);
			if(orderInfo.delete()){
				setAttr("CAllBACK_STATUS", 100);
				render("/personal.html");
			}else{
				//删除失败
				renderText("错误提示：请求失败，请重试");
			}
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	//更新纯图片
	public void updatePic() throws IOException {

		UploadFile uFile = getFile();
		int id = getParaToInt("id");
		int type = getParaToInt("type");
		String docType = getPara("docType");
		String remark = getPara("remark","");
		
		int orderId = getParaToInt("orderId");
		System.out.println("1:"+remark);
		OSSClient ossClient = new OSSClient(CommonUtils.endpoint, CommonUtils.accessKeyId, CommonUtils.accessKeySecret);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String picHref = CallBackUtils.httpKey + date  + "." + docType;
			PutObjectRequest putObjectRequest = new PutObjectRequest("baicai-home", date+"."+docType, 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = CallBackUtils.createOrderCallBack(CommonUtils.callbackOrderUrl, type, id, picHref,remark);	       
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
		 } catch (OSSException oe) {
		        System.out.println("Caught an OSSException, which means your request made it to OSS, "
		                + "but was rejected with an error response for some reason.");
		        System.out.println("Error Message: " + oe.getErrorCode());
		        System.out.println("Error Code:       " + oe.getErrorCode());
		        System.out.println("Request ID:      " + oe.getRequestId());
		        System.out.println("Host ID:           " + oe.getHostId());
		        //OSS失败
		        renderText("错误提示：请求失败，请重试");
	    } catch (ClientException ce) {
	        System.out.println("Caught an ClientException, which means the client encountered "
	                + "a serious internal problem while trying to communicate with OSS, "
	                + "such as not being able to access the network.");
	        System.out.println("Error Message: " + ce.getMessage());
	        //OSS失败
	        renderText("错误提示：请求失败，请重试");
	    } finally {
	        ossClient.shutdown();
	        switch (type) {
			case 1:
				orderDetail(orderId);
				break;
			case 2:
				orderDetail(orderId);
				break;
			case 3:
				orderDetail(orderId);
				break;
			case 4:
				orderDetail(orderId);
				break;
			case 5:
				orderDetail(orderId);
				break;
			default:
				break;
			}
	    }
		
	}
	
	//管理员查看订单详情
	public void orderDetail(int orderId) {
//		try{
//			orderId = getParaToInt("orderId");
//			List<OrderInfo> orderList = OrderInfo.me.findOrder(orderId);
//			List<Contract> contractList = Contract.me.findContractByOrder(orderId);
//			List<Drawing> drawingList = Drawing.me.findDrawingByOrder(orderId);
//			setAttr("orderList", orderList.toArray());
//			setAttr("contractList", contractList);
//			setAttr("drawingList", drawingList);
//			render("../admin/order_details.html");
//		}catch(Exception e){
//			renderText("错误提示：请求失败，请重试");
//		}
		
		try{
			List<OrderInfo> orderList = OrderInfo.me.findOrder(orderId);
			List<Contract> contractList = Contract.me.findContractByOrder(orderId);
			List<Drawing> drawingList = Drawing.me.findDrawingByOrder(orderId);
			
			List<Employee>designerList=Employee.me.findPost("设计师");
			List<Employee>engineerList=Employee.me.findPost("工程师");
			List<Employee>kefuList=Employee.me.findPost("客服");
			
			setAttr("kefu", Employee.me.findEmployeeName(orderList.get(0).getService()));
			setAttr("designer", Employee.me.findEmployeeName(orderList.get(0).getDesigner()));
			setAttr("engineer", Employee.me.findEmployeeName(orderList.get(0).getEngineer()));
			
			setAttr("kefuList", kefuList);	
			setAttr("designerList", designerList);
			setAttr("engineerList", engineerList);
		    setAttr("orderList", orderList.toArray());
			setAttr("contractList", contractList);
			setAttr("drawingList", drawingList);
			render("../admin/order_details.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void addorderbefore(){
		try{
			List<Employee>designerList=Employee.me.findPost("设计师");
			List<Employee>engineerList=Employee.me.findPost("工程师");
			List<Employee>kefuList=Employee.me.findPost("客服");
			setAttr("kefuList", kefuList);
			setAttr("designerList", designerList);
			setAttr("engineerList", engineerList);
			render("../admin/order_add.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
}
