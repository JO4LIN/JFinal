package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseHomeCarousel;


@SuppressWarnings("serial")
public class HomeCarousel extends BaseHomeCarousel<HomeCarousel>{
	
	public static final HomeCarousel me = new HomeCarousel();
	

	public List<HomeCarousel> findCarousel() {
		return find("select * from home_carousel order by carouselId asc");
	}
}
