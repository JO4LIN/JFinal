jQuery().ready(function(){
	var index=window.location.href.split('/').length-1;
	var href=window.location.href.split('/')[index].substr(0,4);
	jQuery(".navCon li a[href^='"+href+"']").addClass("on");
	
	
	var li_width=jQuery(".navCon li a.on").outerWidth();
	var li_left=jQuery(".navCon li a.on").position().left;	
	jQuery(".navCon .line").css({width:li_width,left:li_left})
	
	
	jQuery(".navCon li").hover(function(){
		var li_width=jQuery(this).outerWidth();
		var li_left=jQuery(this).position().left;
		jQuery(".navCon .line").stop().animate({width:li_width,left:li_left},{duration:1500,easing:"easeOutElastic"})
		
		
		
	},function(){
		
		jQuery(".navCon .line").stop().animate({width:li_width,left:li_left},{duration:1500,easing:"easeOutElastic"})
		
	})
	
})