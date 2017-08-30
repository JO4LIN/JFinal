package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseDrawing;

@SuppressWarnings("serial")
public class Drawing extends BaseDrawing<Drawing>{
	public static final Drawing me = new Drawing();
	
	public List<Drawing> findDrawing() {
		return find("select * from drawing_pic order by picId asc");
	}
	
	public List<Drawing> findDrawingByOrder(int orderId) {
		return find("select * from drawing_pic where orderId = '"+orderId+"' order by picId asc");
	}

}
