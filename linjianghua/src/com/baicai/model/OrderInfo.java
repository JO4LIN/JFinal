package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseOrderInfo;
import com.jfinal.plugin.activerecord.Page;


@SuppressWarnings("serial")
public class OrderInfo extends BaseOrderInfo<OrderInfo>{
	
	public static final OrderInfo me = new OrderInfo();
	
	//查询，分页，全部订单
	public Page<OrderInfo> findAllOrder(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from order_info order by orderId asc");
	}
	
	//查询，分页，某用户订单
	public Page<OrderInfo> findUserOrder(int pageNumber, int pageSize, String phone) {
		return paginate(pageNumber, pageSize, "select *", "from order_info where phone='"+phone+"' order by orderId asc");
	}	
	
//	//查询，分页，根据各条件
//	public Page<OrderInfo> findConditionOrder(int pageNumber, int pageSize, String orderId,String realName,String phone,String state) {
//		return paginate(pageNumber, pageSize, "select *",
//				"from order_info,user_info"
//				+ " where orderId like '"+orderId+"' and realName like '"+realName+"' and user_info.phone like '"+phone+"' and state like '"+state+"' "
//						+ "and user_info.phone = order_info.phone ");
//	}
	
	//返回全部订单
	public List<OrderInfo> findAllOrder() {
		return find("select * from order_info,user_info where order_info.phone = user_info.phone order by orderTime desc");
	}
	
	//根据订单号查订单
	public List<OrderInfo> findOrder(int orderId) {
		return find("select * from order_info,user_info where orderId = '"+orderId+"' and order_info.phone = user_info.phone "
				+"order by orderTime desc");
	}
	
	
	//获取订单号
	public int findOrderId(String phone) {
		return findFirst("select orderId from order_info,user_info where order_info.phone = '"+phone+"' and order_info.phone = user_info.phone ").getOrderId();
	}
	
	//是否订单号
	public boolean isOrderId(String phone) {
		return find("select orderId from order_info,user_info where order_info.phone = '"+phone+"' and order_info.phone = user_info.phone ").isEmpty();
	}

}
