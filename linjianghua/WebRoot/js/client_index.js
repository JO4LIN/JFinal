$(function(){

	$('.title-list li').mouseover(function(){
		var liindex = $('.title-list li').index(this);
		$(this).addClass('on').siblings().removeClass('on');
		$('.product-wrap div.product').eq(liindex).fadeIn(800).siblings('div.product').hide();
		var liWidth = $('.title-list li').width();
		$('.indexTab .title-list p').stop(false,true).animate({'left' : liindex * liWidth + 'px'},300);
	});
	
	$(".indexLive ul .up").hover(function(){
		$(this).find(".txt").stop().animate({height:"295px"},200);
		$(this).find(".txt h3").stop().animate({paddingTop:"90px"},200);
	},function(){
		$(this).find(".txt").stop().animate({height:"45px"},200);
		$(this).find(".txt h3").stop().animate({paddingTop:"0px"},200);
	})
	$(".indexLive ul .up1").hover(function(){
		$(this).find(".txt").stop().animate({height:"604px"},200);
		$(this).find(".txt h3").stop().animate({paddingTop:"260px"},200);
	},function(){
		$(this).find(".txt").stop().animate({height:"45px"},200);
		$(this).find(".txt h3").stop().animate({paddingTop:"0px"},200);
	})
})