var isLogin = function() {
	var flag = false;
	jQuery.ajax({
		url : "/userInfo/isLogin",
		type : "post",
		dataType : "json",
		async : false,
		cache : false,
		success : function(data) {
			var result = data["result"];
			if (result && result == 1) {
				flag = true;
			}
		}
	});
	return flag;
}
var initHeadNav = function(thiz) {
	jQuery(thiz).append(
			jQuery('<h2></h2>').addClass('headWelcome').append('欢迎来到白菜适家'));
	if (isLogin()) {
		jQuery(thiz).append(jQuery('<a></a>').addClass('headLogin').prop({
			'href' : '/checkOrderDetail'
		}).append('个人中心'));
		jQuery(thiz).append(jQuery('<a></a>').addClass('headLogin').prop({
			'href' : '/userInfo/quit'
		}).append('退出'));
	} else {
		jQuery(thiz).append(jQuery('<a></a>').addClass('headLogin').prop({
			'href' : '/userInfo'
		}).prop({
			'rel' : 'nofollow'
		}).append('登录'));
		jQuery(thiz).append(jQuery('<a></a>').addClass('headLogin').prop({
			'href' : '/userInfo/registerI'
		}).prop({
			'rel' : 'nofollow'
		}).append('注册'));
	}

	jQuery(thiz).append(jQuery('<a></a>').addClass('headWechat').prop({
		'href' : '#'
	}).append('关注送好礼'));
	
	jQuery(thiz).append(jQuery('<a></a>').addClass('headTribe').prop({
		'href' : '#'
	}).append('咨询热线：'));
	
	jQuery(thiz).append(jQuery('<h2></h2>').addClass('headPhone').prop({
		'href' : '#'
	}).append('0756-8529090'));
}

function show(obj, id) {
	var objDiv = jQuery("#" + id + "");
	jQuery(objDiv).css("display", "block");
	jQuery(objDiv).css("left", event.clientX);
	jQuery(objDiv).css("top", event.clientY + 10);
}
function hide(obj, id) {
	var objDiv =jQuery("#" + id + "");
	jQuery(objDiv).css("display", "none");
}
 
jQuery(document).ready(function() {
	 
	var $headFunction = jQuery('.headFunction');
	$headFunction.each(function(i, item) {
		initHeadNav(item);
	}); 
	jQuery(".headWechat").mouseout(function(){
		hide(this,"mydiv1")
	});
	jQuery(".headWechat").mouseover(function(){
		show(this,"mydiv1")
	});
})
