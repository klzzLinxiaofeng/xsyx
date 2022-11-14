<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<!-- 左侧start -->
<div class="left_mu padding_left">
    <div class="leftbar">
        <div class="panel-body" id="LEFT_HEAD_PING_SHI">
            <div class="main_modele">
                <p class="p_icon"><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"></p>
                <%--<p class="p1">评师</p>--%>
                <p class="p1"></p>
            </div>
            <ul class="first_ul">
               <%-- <li class="active">
                    <a href="javascript:void(0)"><span>班主任评价</span></a>
                    <ul>
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=1')"  class="blue_1"><span>评价记录</span></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/statistics/index')"><span>评价统计</span></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad')"><span>评价设置</span></a>
                        </li>
                        <li class="line"></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)"><span>学科教师评价</span></a>
                    <ul style="display: none;">
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=2')"><span>评价记录</span></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/statistics/index?type=2')"><span>评价统计</span></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad?type=2 ')"><span>评价设置</span></a>
                        </li>
                    </ul>
                </li>--%>
            </ul>
        </div>
    </div>
    <div class="zg_btn" style="display: none"><a id="left_close" class="left_close" href="javascript:void(0)"> <span></span> </a></div>
</div>
<!-- 左侧end -->
<script type="text/javascript">
    function show_page(obj) {
        var src=$(obj).attr('data-url');
        if(src!='undefined'&&src!=''){
            if(src.indexOf('?')==-1){
                src=src+"?dm="+$(obj).attr('data-dm');
            }else{
                src=src+"&dm="+$(obj).attr('data-dm');
            }
            $('#core_iframe').attr('src',src);
        }
    }
    $(function () {
        // 展开关闭
        $('body').on('click','#left_close',function(){
            if($(this).hasClass('left_open')){
                $(".leftbar").show()
                $('.left_mu').addClass('padding_left');
                $(this).removeClass('left_open');
                $('.man_right').css('margin-left','250px');
            }else{
                $('.leftbar').hide();
                $('.left_mu').removeClass('padding_left');
                $(this).addClass('left_open');
                $('.man_right').css('margin-left','0');
            }


        });
        var loader = new loadDialog();//实例化对象
        // loader.show();// 显示
        // var timer=setTimeout("loader.close();", 2000);
// 		loader.close(); 关闭
        // 		点击一级目录
//         $("body").on('click','.left_mu .first_ul > li>a',function(){
//             $(".left_mu .first_ul > li").removeClass("active");
//             $(".left_mu .first_ul > li >ul").hide();
//             $(this).parent().addClass('active')
//             $(this).parent().children('ul').show();
//         })
// // 		点击二级目录
//         $("body").on('click','.left_mu .first_ul > li >ul >li>a',function(){
//             $(".first_ul > li>ul>li.line").remove();
//             $(".left_mu .first_ul > li >ul >li>a").removeClass("blue_1");
//             $(this).addClass("blue_1");
//             var i=$(this).parent().index();
//             var b_height=14+36*i;
//             if($(this).parent().parent().children('.line').length==0){
//                 $(this).parent().parent().append("<li class='line'></li>")
//             }
//             $(".left_mu .first_ul > li>ul>.line").height(b_height)
//         });
        $("body").on('click','.left_mu li a',function(){
            if($(this).data('level')=='first_level'){
                $(".left_mu .first_ul > li").removeClass("active");
                $(".left_mu .first_ul > li >ul").hide();
                $(this).parent().addClass('active')
                $(this).parent().children('ul').show();
            }else if($(this).data('level')=='second_level'){
                $('.third_ul a').removeClass("blue_1");
                $('.third_ul .line').remove();
                $('.third_ul').hide();
                $(".first_ul > li>ul>li.line").remove();
                $(".left_mu .first_ul > li >ul >li>a").removeClass("blue_1");
                $(this).addClass("blue_1");
                var i=$(this).parent().index();
                var b_height=14+36*i;
                if($(this).parent().parent().children('.line').length==0){
                    $(this).parent().parent().append("<li class='line'></li>")
                }
                $(".left_mu .first_ul > li>ul>.line").height(b_height);
                $(this).parent().children('ul').show();
            }else if($(this).data('level')=='third_level'){
                console.log('three')
                // $(".first_ul > li>ul>li.line").remove();
                // $(".left_mu .first_ul > li >ul >li>a").removeClass("blue_1");
                 $(this).addClass("blue_1");
                var i=$(this).parent().index();
                var b_height=14+36*i;
                if($(this).parent().parent().children('.line').length==0){
                    $(this).parent().parent().append("<li class='line'></li>")
                }
                $(".left_mu .first_ul > li>ul>li>ul>.line").height(b_height)
            }
        });
        // var data={
        //     "name":"评师",
        //     "imageUrl":"http://test.studyo.cn/res/qyjx/css/images/ps_icon.png",
        //     "dm":"JIAOSHIPINJIA",
        //      "targetDm":"PING_JIA_JI_LU5",
        //      "target_url":"",
        //     "menu":[
        //         {
        //             "name":"班主任评价",
        //             "url":"",
        //             "dm":"BAN_ZHU_REN_PING_JIA",
        //             "menu":[
        //                 {
        //                     "name":"评价记录1",
        //                     "url":"/assessment/teacher/menu",
        //                     "dm":"PING_JIA_JI_LU",
        //                     "menu":[
        //                         {
        //                             "name":"评价记录3",
        //                             "url":"/assessment/teacher/menu",
        //                             "dm":"PING_JIA_JI_LU3",
        //                             "menu":[
        //                                 {
        //                                     "name":"评价记录4",
        //                                     "url":"/assessment/teacher/menu",
        //                                     "dm":"PING_JIA_JI_LU4",
        //                                 }
        //                             ]
        //                         },
        //                         {
        //                             "name":"评价记录4",
        //                             "url":"/assessment/teacher/menu",
        //                             "dm":"PING_JIA_JI_LU5"
        //                         }
        //                     ]
        //                 },
        //                 {
        //                     "name":"评价统计",
        //                     "url":"",
        //                     "dm":"PING_JIA_TONG_JI"
        //                 },
        //                 {
        //                     "name":"评价设置",
        //                     "url":"",
        //                     "dm":"PING_JIA_SHE_ZHE"
        //                 }
        //             ]
        //         },
        //         {
        //             "name":"科任教师评价",
        //             "url":"",
        //             "dm":"",
        //             "menu":[
        //                 {
        //                     "name":"评价记录1",
        //                     "url":"",
        //                     "dm":"PING_JIA_JI_LU2"
        //                 },
        //                 {
        //                     "name":"评价统计",
        //                     "url":"",
        //                     "dm":"PING_JIA_TONG_JI2"
        //                 },
        //                 {
        //                     "name":"评价设置",
        //                     "url":"",
        //                     "dm":"PING_JIA_SHE_ZHE2"
        //                 }
        //             ]
        //         },
        //         {
        //             "name":"校长评价",
        //             "url":"",
        //             "dm":"XIAO_ZHANG_PING_JIA",
        //             "menu":[]
        //         },
        //     ]
        // };
        <%--var data=JSON.parse(${secondIndex});--%>
        var data=${secondIndex};
        if(typeof(data.name) !="undefined")
        document.title=data.name;
        $('.left_mu .main_modele .p_icon img').attr('src',data.imageUrl);
        $('.left_mu .main_modele .p1').text(data.name);
        var num = 0;
        for(var i=0;i<data.menu.length;i++){
            $('<li><a href="javascript:void(0)" data-level="first_level" data-url="'+data.menu[i].url+'" data-dm="'+data.menu[i].dm+'"  onclick="show_page(this)"><span>'+data.menu[i].name+'</span></a></li>').appendTo($('.first_ul'));
            if(data.menu[i].menu!=''){
                $('<ul style="display: none" class="second_ul"></ul>').appendTo($('.first_ul>li').eq(i));
                for(var j=0;j<data.menu[i].menu.length;j++){
                    $('<li><a href="javascript:void(0)" data-level="second_level"  data-url="'+data.menu[i].menu[j].url+'" data-dm="'+data.menu[i].menu[j].dm+'"  onclick="show_page(this)"><span>'+data.menu[i].menu[j].name+'</span></a></li>').appendTo($('.first_ul>li').eq(i).children('ul'));
                    if(data.menu[i].menu[j].menu!=''&&data.menu[i].menu[j].menu!=undefined){
                        $('<ul style="display: none" class="third_ul"></ul>').appendTo($('.second_ul>li').eq(num));
                        for(var k=0;k<data.menu[i].menu[j].menu.length;k++){
                            $('<li><a href="javascript:void(0)" data-level="third_level"  data-url="'+data.menu[i].menu[j].menu[k].url+'" data-dm="'+data.menu[i].menu[j].menu[k].dm+'"  onclick="show_page(this)"><span>'+data.menu[i].menu[j].menu[k].name+'</span></a></li>').appendTo($('.second_ul>li').eq(num).children('ul'));
                        }
                    }
                    num++;
                }
            }
        }
        //点击第一个
        if(data.targetDm==''||data.targetDm==undefined){
            $('.left_mu .first_ul > li>a').eq(0).click();
            if($('.left_mu .first_ul > li').eq(0).children('ul').length!=0){
                $('.left_mu .second_ul > li>a').eq(0).click();
            }
            if($('.left_mu .first_ul > li').eq(0).children('ul').children('li').eq(0).children('ul').length!=0){
                $('.left_mu .first_ul > li').eq(0).children('ul').children('li').eq(0).children('ul').children('li').children('a').eq(0).click();
            }
        }else{
            var targetDm=data.targetDm;
            $(data.menu).each(function(i){
                if(data.menu[i].dm==targetDm){
                    $('.left_mu .first_ul > li>a').eq(i).click();
                }else{
                    $(data.menu[i].menu).each(function(j){
                        if(data.menu[i].menu[j].dm==targetDm){
                            $('.left_mu .first_ul > li>a').eq(i).click();
                            if($('.left_mu .first_ul > li').eq(i).children('ul').length!=0){
                                $('.left_mu .first_ul > li').eq(i).children('ul').children('li').children('a').eq(j).click();
                            }
                        }else{
                            $(data.menu[i].menu[j].menu).each(function(k){
                                if(data.menu[i].menu[j].menu[k].dm==targetDm){
                                    console.log($('.left_mu .first_ul > li').eq(i).children('ul').children('li').children('a').eq(j).children('ul').length)
                                    $('.left_mu .first_ul > li>a').eq(i).click();
                                    $('.left_mu .first_ul > li').eq(i).children('ul').children('li').children('a').eq(j).click();
                                    $('.left_mu .first_ul > li').eq(i).children('ul').children('li').eq(j).children('ul').children('li').children('a').eq(k).click();
                                    // if($('.left_mu .first_ul > li').eq(i).children('ul').children('li').children('a').eq(j).children('ul').length!=0){
                                    //     console.log($('.left_mu .first_ul > li').eq(i).children('ul').children('li').eq(j).children('ul').children('li').children('a').eq(k).test())
                                    //
                                    // }
                                }
                            })
                        }
                    })
                }
            })
        }
        var h = document.documentElement.clientHeight;
        var h1 = document.documentElement.clientHeight-256;
        $(".first_ul").css("height",h1);
        $('.left_mu').css('height',h)
        $(window).resize(function () {
            var h = document.documentElement.clientHeight;
            var h1 = document.documentElement.clientHeight-256;
            $(".first_ul").css("height",h1);
            $('.left_mu').css('height',h)
        })
        $(".first_ul").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

    })
</script>

