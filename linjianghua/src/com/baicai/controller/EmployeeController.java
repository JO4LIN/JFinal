package com.baicai.controller;

import java.util.List;

import com.baicai.interceptor.EmployeeInterceptor;
import com.baicai.interceptor.PicInterceptor;
import com.baicai.model.Employee;
import com.baicai.model.HomeBottom;
import com.baicai.model.OrderInfo;
import com.baicai.model.Reserver;
import com.baicai.model.UserInfo;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

public class EmployeeController extends Controller{

	@Clear
	public void index() {
		//setSessionAttr("isAdminLogin", false);
		render("../admin/login.html");
	}
	
	@Before(EmployeeInterceptor.class)
	public void checkEmployee(){
		try{
			List<Employee> list = Employee.me.findEmployee();
			setAttr("list", list);
			render("../admin/employee_manage.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}	

	}
	
	@Before(EmployeeInterceptor.class)
	public void checkEmployeeAdd() {
		render("../admin/employee_add.html");
	}
	
	public void addEmployee(){
		try{
			Employee employee = getModel(Employee.class, "e");		
			if(employee.save()){	
				setAttr("result", "1");
				setAttr("msg", "添加成功");			
			}else
			{
				setAttr("result", "0");
				setAttr("msg", "添加失败");	
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
			
	}
	
	public void updateEmployee(){
		try{
			int employeeId = getParaToInt("e.employeeId");
			Employee employee =Employee.me.findById(employeeId);
			
			String realName = getPara("e.realName");
			String phone = getPara("e.phone","");
			String post = getPara("e.post");
			 		System.out.println(employeeId+""+realName+phone+post);
			String order_manage = getPara("e.order_manage");
			String pic_manage = getPara("e.pic_manage");
			String user_manage = getPara("e.user_manage");
			String appointment_manage=getPara("e.appointment_manage");
			
			employee.setRealName(realName);
			employee.setPhone(phone);
			employee.setPost(post);
			employee.setOrder_manage(false);
			employee.setPic_manage(false);
			employee.setUser_manage(false);
			employee.setAppointment_manage(false);
			if(order_manage!=null)
			{
				employee.setOrder_manage(Boolean.valueOf(order_manage));
			}
			if(pic_manage!=null)
			{
				employee.setPic_manage(Boolean.valueOf(pic_manage));		
			}if(user_manage!=null)
			{
				employee.setUser_manage(Boolean.valueOf(user_manage));
			}
			if(appointment_manage!=null)
			{
				employee.setAppointment_manage(Boolean.valueOf(appointment_manage));
			}	
			if(employee.update())
			{
				setAttr("result", "1");
				setAttr("msg", "修改成功");	
			}else
			{
				setAttr("result", "0");
				setAttr("msg", "修改失败");
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

		
	}
	
	
	public void updatebeforeEmployee(){
		try{
			String employeeId=getPara("employeeId");
			Employee e=Employee.me.findById(employeeId);
			setAttr("e", e);
			render("../admin/employee_updatebefore.html");
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}
		
	}
	public void updatepwdbefore()
	{
		render("../admin/password_update.html");
	}
	public void updatepwd()
	{
		//try{
			int currentid=getSessionAttr("employeeId");
			String oldpwd=getPara("oldpwd");
			String newpwd=getPara("newpwd");
			System.out.println(currentid+"---"+oldpwd+"---"+newpwd);
			Employee e=Employee.me.findById(currentid);
			if(e.getPassword().equals(oldpwd))
			{
				e.setPassword(newpwd);
				if(e.update())
				{
					setAttr("result", "1");
					setAttr("msg", "修改成功");	
				}else
				{
					//renderText("修改失败");
					setAttr("result", "0");
					setAttr("msg", "删除失败");
				}
			}else
			{
				setAttr("result", "0");
				setAttr("msg", "密码输入错误");
			}
			renderJson();
			
//		}catch(Exception e){
//			renderText("错误提示：请求失败，请重试");
//		}
//	
	}
	
	public void delEmployee(){
		try{
			String employeeId=getPara("employeeId");
			if(Employee.me.deleteById(employeeId)){	
				setAttr("result", "1");
				setAttr("msg", "删除成功");			
			}else
			{
				setAttr("result", "0");
				setAttr("msg", "删除失败");	
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	

	@Clear
	public void login() {
		try{
			String phone = getPara("phone");
			String pwd = getPara("adminPwd");
			int employeeId = Employee.me.findEmployeeId(phone);
			String realPwd = Employee.me.findById(employeeId).getPassword();
			if(pwd.equals(realPwd)){
				boolean order_manage = Employee.me.findById(employeeId).getOrder_manage();
				boolean pic_manage = Employee.me.findById(employeeId).getPic_manage();
				boolean user_manage = Employee.me.findById(employeeId).getUser_manage();
				boolean appointment_manage = Employee.me.findById(employeeId).getAppointment_manage();
				setSessionAttr("order_manage", order_manage);
				setSessionAttr("pic_manage", pic_manage);
				setSessionAttr("user_manage", user_manage);
				setSessionAttr("reserve_manage", appointment_manage);
				if("admin".equals(phone)){
					setSessionAttr("employee_manage", true);
				}else {
					setSessionAttr("employee_manage", false);
				}
				setSessionAttr("employeeId", employeeId);
				setAttr("result", 1);
				setAttr("msg", "登录成功");
			//	render("../admin/index.html");
			}else {
				renderText("账号或密码错误");
				setAttr("result", 0);
				setAttr("msg", "输入的账号或密码有误，请重新输入");
			}
			renderJson();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void quit() {
		try{
			removeSessionAttr("order_manage");
			removeSessionAttr("pic_manage");
			removeSessionAttr("user_manage");
			removeSessionAttr("reserve_manage");
			//setSessionAttr("isAdminLogin", false);
			index();
		}catch(Exception e){
			renderText("错误提示：请求失败，请重试");
		}

	}
	
	public void updateReserver() {
		try{
			String phone = getPara("phone");
			Reserver e = Reserver.me.findById(1);
			e.setPhone(phone);
			if(e.update()){
				setAttr("phone", phone);
				render("../admin/updateReserver.html");
			}
		}catch(Exception e){
			renderText("请求失败，请重试");
		}
	}
	
	public void checkReserver() {
		try{
			setAttr("phone", Reserver.me.findById(1).getPhone());
			render("../admin/updateReserver.html");
		}catch(Exception e){
			renderText("请求失败，请重试");
		}
	}
	
}
