jQuery(document).ready(function ($) {
    //$(".Nav>ul>li").hover(function () {
    //    $(this).find(".Drop_Down").stop(false, true).slideToggle();
    //});

    //$(".sub-Nav>ul>li").hover(function () {
    //    $(this).find(".Drop_Down").stop(false, true).slideToggle();
    //});
	jQuery(".Nav>ul>li").hover(function () {
		jQuery(this).find(".Drop_Down").stop(false, true).slideDown();
    }, function () {
    	jQuery(this).find(".Drop_Down").stop(false, true).slideUp();
    });

    jQuery(".sub-Nav>ul>li").hover(function () {
        jQuery(this).find(".Drop_Down").stop(false, true).slideDown();
    }, function () {
        jQuery(this).find(".Drop_Down").stop(false, true).slideUp();
    });

    jQuery(window).scroll(function () {
        var wh = jQuery(window).scrollTop();
        if (wh == 0) {
            jQuery(".banner-fork").addClass("on");
            jQuery(".fork-sig").removeClass("on");
        } else {
            jQuery(".banner-fork").removeClass("on");
            jQuery(".fork-sig").addClass("on");
        }
    });

    /*index-banner*/
    jQuery('.banner-fork>i').on('click', function () {
        jQuery(this).parent().removeClass('on');
        jQuery('.fork-sig').addClass('on');
    });

    jQuery('.fork-sig').on('click', function () {
        jQuery(this).removeClass('on');
        jQuery('.banner-fork').addClass('on');
    });
    /*sub-banner*/
    jQuery('.banner-fork form>i').on('click', function () {
        jQuery(this).parent().fadeOut();
    });


    jQuery('.show .pp').on('hover', function () {
        jQuery(this).find('.hide').stop(false, true).fadeToggle();
    });
    jQuery('.about-04 .mian ul li>p').on('click', function () {
        if (jQuery(this).siblings('.join').is(":hidden")) {
            jQuery(this).siblings('.join').slideDown();
            jQuery(this).parent('li').addClass('on');
            jQuery(this).parents('li').siblings().find('.join').slideUp();
            jQuery(this).parents('li').siblings().removeClass('on');
        } else {
            jQuery(this).siblings('.join').slideUp();
            jQuery(this).parent('li').removeClass('on');
        }
    });
    jQuery('.shead-left>i').on('click', function () {
        jQuery(this).parent().addClass('on');
        jQuery(this).fadeOut(300);
    });

    jQuery('.get-Close span i').on('click', function () {
        jQuery(this).parents(".shead-left").removeClass('on');
        jQuery('.shead-left>i').fadeIn(1000);
    });

     
    jQuery('.shead-right>i').on('click', function () {
        jQuery(this).parent().addClass('on');
        jQuery(this).fadeOut(300);
    });
    jQuery('.shead-right form>i').on('click', function () {
        jQuery(this).parents(".shead-right").removeClass('on');
        jQuery('.shead-right>i').fadeIn(1000);
    });

    //投诉建议弹出
    jQuery('.tachu').on('click', function () {
        jQuery('.customer-04.div1').fadeIn();
    });
    //在线下单弹出
    jQuery('.tachu2').on('click', function () {
        jQuery('.customer-04.div2').fadeIn();
    });
    //申请设计弹出
    jQuery('.tachu3').on('click', function () {
        jQuery('.customer-04.div3').fadeIn();
    });

    //楼层滚动
    function side(el1, el2, zt, _top, prve, next) {
        var canGo = true;
        var al = jQuery(el1);//选项li
        var main = jQuery(el2);//对应层级雷鸣
        var on = zt;
        var abc = main.length;
        var _prve = jQuery(prve);
        var _next = jQuery(next);
        var i = 0;
        var cba = al.length;

        main.each(function (index) {
            if (jQuery(window).scrollTop() >= jQuery(this).offset().top) {
                al.removeClass(on);
                al.eq(index).addClass(on);
            }
        });
        al.each(function (index) {
            jQuery(this).click(function () {
                canGo = false;
                al.removeClass(on);
                jQuery(this).addClass(on);
                jQuery("html,body").stop(true, true).animate({
                    "scrollTop": main.eq(index).offset().top - _top
                }, 500, function () {
                    canGo = true;
                    i = index;
                });
            });
        });
        jQuery(window).scroll(function () {
            if (canGo) {
                for (var index = abc - 1; index >= 0; index--) {
                    if (jQuery(window).scrollTop() >= main.eq(index).offset().top - _top - 1) {
                        if (al.eq(index).hasClass(on)) {
                        }
                        else {
                            al.removeClass(on);
                            al.eq(index).addClass(on);
                        }
                        i = index;
                        break;
                    }
                }
            }
        });
        //	    function play_1(){//动画移动
        //			al.eq(i).addClass("on").siblings().removeClass("on").parent().siblings().find("font").removeClass("on");
        //			$("html,body").stop(true, true).animate({
        //              "scrollTop": main.eq(i).offset().top-_top
        //          }, 500,function(){
        //              canGo=true;
        //          });
        //		}
        _prve.click(function () {
            i--;
            i = (i < 0 ? 0 : i);
            al.eq(i).click();
        });
        _next.click(function () {
            i++;
            i = (i > (cba - 1) ? (cba - 1) : i);
            al.eq(i).click();
        });
    }
    side('.about-menu ul li', '.ab', 'on', '64');
    side('.customer-menu ul li', '.ggdd', 'on', '64');
    side('.soll_ic font', '.soll_id li', 'on', '0', '.hh_up', '.hh_down');

    //返回顶部
    jQuery('.roll-nav').hide();
    jQuery(window).scroll(function () {
        var wh = jQuery(window).scrollTop();
        if (wh > 500) {
            jQuery('.roll-nav').fadeIn();
        } else {
            jQuery('.roll-nav').fadeOut();
        }
    });

    jQuery('.roll-nav .a4').click(function () {
        if (jQuery(document).scrollTop() == 0) {
        } else {
            jQuery('body,html').animate({ scrollTop: 0 }, 1000);
        }
    });

    /*工地直播详情页*/
    jQuery('.Site-live-detail .live-a2 .mian_2 .gj>ul>li').on('click', function () {
        var gba = jQuery(this).find('img').attr("src");
        var winHH = jQuery(window).height();
        jQuery('.pohot span').find('img').attr("src", gba).parents('.pohot').fadeIn();
        var imghh = jQuery(this).find('img').height() * 3.3;
        if (winHH <= imghh) {
            jQuery('.pohot span').find('img').height(winHH - 60);
        } else {
            jQuery('.pohot span').find('img').height('auto');
        }
    });
    //$('.Site-live-detail .live-a2 .mian_2 .gj>ul>li').on('click', function () {
    //    var gba = $(this).find('img').attr("src");
    //    var winHH = $(window).height() - 60;
    //    $('.pohot span').find('img').attr("src", gba).parents('.pohot').fadeIn();
    //});
    jQuery('.pohot .p-bg').on('click', function () {
        jQuery(this).parents('.pohot').fadeOut();
    });
    jQuery('.pohot i').on('click', function () {
        jQuery(this).parents('.pohot').fadeOut();
    });
    jQuery('.pohot .mian').on('click', function () {
        jQuery(this).parents('.pohot').fadeOut();
    });

    //图片预加载
    
//	    $("img.lazyload").lazyload({
//	        effect: "fadeIn",
//	        threshold: 180,
//	        skip_invisible: false,
//	    });
//      
});