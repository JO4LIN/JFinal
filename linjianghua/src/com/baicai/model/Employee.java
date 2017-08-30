package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseEmplyee;


@SuppressWarnings("serial")
public class Employee extends BaseEmplyee<Employee>{

	public static final Employee me = new Employee();
	
	public List<Employee> findEmployee() {
		return find("select * from employee_info order by employeeId asc");
	}
	
	public String findEmployeeName(int id) {
		return findFirst("select realName from employee_info where employeeId = '"+id+"'").get("realName");
	}

	public String findEmployeePhone(int id) {
		return findFirst("select phone from employee_info where employeeId = '"+id+"'").get("phone");
	}
	
	public int findEmployeeId(String phone) {
		return findFirst("select employeeId from employee_info where phone = '"+phone+"'").getInt("employeeId");
	}
	
	public List<Employee> findPost(String post) {
		return find("select *from employee_info where post='"+post+"'");
	}

}
