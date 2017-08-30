(function(jQuery){jQuery.fn.SmohanPopLayer=function(options){
	var Config={Shade:true,Event:"click",Content:"Content",Title:"Smohan.net"};
	var options=jQuery.extend(Config,options);
	var layer_width=jQuery('#'+options.Content).outerWidth(true);
	var layer_height=jQuery('#'+options.Content).outerHeight(true)
	var layer_top=(layer_height+40)/2;
	var layer_left=(layer_width+40)/2;
	var load_left=(layer_width-36)/2;
	var load_top=(layer_height-100)/2;
	var layerhtml="";
	if(options.Shade==true){
		layerhtml+='<div class="Smohan_Layer_Shade" style="display:none;"></div>';
	}
	layerhtml+='<div class="Smohan_Layer_box" style="width:'+layer_width+'px;height:'+layer_height+'px; margin-top:-'+layer_top+'px;margin-left:-'+layer_left+'px;display:none;" id="layer_'+options.Content+'">';
	layerhtml+='<h3><b class="text">'+options.Title+'</b><a href="javascript:void(0)" class="close"></a></h3>';
	layerhtml+='<div class="layer_content">';
	layerhtml+='<div class="loading" style="left:'+load_left+'px;top:'+load_top+'px;"></div>';
	layerhtml+='<div id="'+options.Content+'" style="display:block;">'+jQuery("#"+options.Content).html()+'</div>';
	layerhtml+='</div>';
	layerhtml+='</div>';
	jQuery('body').prepend(layerhtml);
	if(options.Event=="unload"){
		jQuery('#layer_'+options.Content).animate({opacity:'show',marginTop:'-'+layer_top+'px'},
				function(){jQuery('.Smohan_Layer_Shade').show();jQuery('.Smohan_Layer_box .loading').hide();
				});
		}
	else{
		jQuery(this).on(options.Event,null,function(e){
			jQuery('#layer_'+options.Content).animate({opacity:'show',marginTop:'-'+layer_top+'px'},function(){
				jQuery('.Smohan_Layer_Shade').show();jQuery('.Smohan_Layer_box .loading').hide();});
			});
		}

jQuery('.Smohan_Layer_box .close').click(function(e){
	jQuery('.Smohan_Layer_box').animate({opacity:'hide',marginTop:'-300px'},function(){
		jQuery('.Smohan_Layer_Shade').hide();
		jQuery('.Smohan_Layer_box .loading').show();});
	});};})(jQuery);

jQuery(document).ready(function(e) {
	var subscribe_html = "";
	subscribe_html += '<form action="/reserveInfo/autoRegister" method="post">';
	subscribe_html += '<div id="Subscribe">';
	subscribe_html += '<div class="bespeakPhone"><input placeholder="请输入您的姓名" type="text" name="realName"/></div>';
	subscribe_html += '<div class="bespeakPhone"><input placeholder="请输入手机号" type="text" name="phone"/></div>';
	subscribe_html += '<div class="bespeakBtn"><input value="预  约" type="submit"/></div>';
	subscribe_html += '</div>';
	subscribe_html += '</form>';
	jQuery('body').prepend(subscribe_html);
    jQuery('.subscribe').SmohanPopLayer({Shade : true,Event:'click',Content : 'Subscribe', Title : '欢迎预约'});

	var success_html = "";
	success_html += '<div id="Success">预约成功</div>';
	jQuery('body').prepend(success_html);
	jQuery('.bespeakBtn').click(function(e){jQuery('.Smohan_Layer_box').animate({opacity:'hide',marginTop:'-300px'},"slow",function(){jQuery('.Smohan_Layer_box .loading').show();});});
    jQuery('.bespeakBtn').SmohanPopLayer({Shade : true,Event:'click',Content : 'Success', Title : ''});
});