<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>应用桌面管理</title>
<style>
.layui-layer-btn a{
	height: 40px;
	line-height:40px;
	padding:0 ;
	width:100px;
	font-size: 14px;
	color: #ffffff;
	border-radius: 4px;
}
.layui-layer-btn a.button_blue{
	background-image: linear-gradient(48deg, 
		#1795ef 0%, 
		#2da1f8 100%), 
	linear-gradient(
		#3ac982, 
		#3ac982);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(27, 151, 241, 0.45);
}
.layui-layer-btn a.button_yellow{
	background-image: linear-gradient(48deg, 
		#f5a816 0%, 
		#ffbc00 100%), 
	linear-gradient(
		#d4d4d5, 
		#d4d4d5);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(245, 168, 22, 0.45);
}
.layui-layer-btn a.button_grey{
	background-image: linear-gradient(48deg, 
		#a8bfca 0%, 
		#c2d4dd 100%), 
	linear-gradient(
		#d4d4d5, 
		#d4d4d5);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(139, 170, 185, 0.45);
}
.layui-layer-btn a.button_red{
	background-image: linear-gradient(48deg, 
		#ff6363 0%, 
		#ff7878 100%), 
	linear-gradient(48deg, 
		#f5a816 0%, 
		#ffbc00 100%), 
	linear-gradient(
		#3ac982, 
		#3ac982);
	background-blend-mode: normal, 
		normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(214, 91, 74, 0.45);
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  桌面配置  > <span>智慧校园应用桌面标准配置</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>智慧校园应用桌面标准配置<span class="small_gray">(默认配置)</span></p></div>
			<div class="f_right">
			<button class="btn btn-forbidGray"><i class="fa fa-exchange"></i>切换桌面</button>
			<button class="btn btn-green setUp"><i class="fa fa-cog" ></i>设置</button>
			<button class="btn btn-red"><i class="fa fa-trash-o" ></i>删除</button>
			<span style="border-left: solid 1px #e3e8ec;display: inline-block;margin: 10px 16px;padding-left: 20px;">
				<button class="btn btn-blue" style="margin: 0;"><i class="fa fa-save" ></i>保存</button>
			</span>
			</div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>应用：</span>
				<input type="text" placeholder="请输入搜索关键词"  class="apply_name">
				<button class="btn btn-blue" onclick="serach_apply()">搜索</button>
			</div>
			<button class="btn btn-orange fr yyfgd">桌面应用/覆盖到</button>
			<button class="btn btn-forbidGray fr" style="display:none">桌面应用/覆盖到</button>
			<button class="btn btn-peaGreen fr">栏目管理</button>
			
		</div>
		<!-- 应用拖拽 -->
		<div class="app_drag">
		    <div class="app_dleft">
		        <div class="a_top">全部</div>
		        <div class="app_list all_list">
		            <ul>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA1</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA2</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行这是一条应用说明，加一行凑够2行这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA3</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA4</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA5</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA6</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		            </ul>
		        </div>
		        <div class="ts_red">*可拖拽应用到相应的位置/栏目中</div>
		    </div>
		    <div class="app_dright">
		        <div class="a_top">
		            <div class="nav_div" id="tabs">
		                <ul>
		                    <li><a href="javascript:void(0)" class="on" data-id='0'>管理</a></li>
		                    <li><a href="javascript:void(0)" data-id='1'>教务</a></li>
		                    <li><a href="javascript:void(0)" data-id='2'>校务</a></li>
		                </ul>
		            </div>
		            <div class="turn_btn">
		                <a href="javascript:void(0)" class="turn_left"></a>
		                <a href="javascript:void(0)" class="turn_right"></a>
		            </div>
		        </div>
		        <div class=''>
		            <div class="app_list" style="display:block"><ul class="connectedSortable ui-helper-reset"><li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA1</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA2</p>
			                    <div class="yy_detail">
			                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
			                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
			                    </div>
			                </li></ul></div>
			            <div class="app_list" ><ul class="connectedSortable ui-helper-reset"></ul></div>
			            <div class="app_list" ><ul class="connectedSortable ui-helper-reset"></ul></div>
			        </div>
			        <div class='delete_app'><p><i class=""></i>删除</p></div>
			    </div>
			</div>
	</div>
</div>
<div class="scts_setUp" style="display:none;text-align: center;padding:20px;">
	<p>名称：<input type="text"/></p>
	<p><i class="ck"></i>显示【全部】栏目</p>
	<p><i class="ck"></i>设置为默认桌面</p>
</div>

<div class="scts_setUp_save" style="display:none;text-align: center;padding-top:35px;">
	<p>当前桌面配置为默认桌面，您确定保存当前修改操作吗？</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>

<div class="scts_delete_ts" style="display:none;text-align: center;padding-top:35px;">
	<p>您确定从<span style="color:#2299ee">【管理】</span>栏目中移除已选择的小应用吗？</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>
<script src="${pageContext.request.contextPath}/res/qyjx/js/jquery-ui.min.js"></script>
<script>
//设置
$(".setUp").click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '280px'],
		  title: '设置', //不显示标题
		  content: $('.scts_setUp'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存','另存为模板','取消'],//按钮
		  btn1: function(index, layero){
			  
			  layer.open({
				  type: 1,
				  shade: false,
				  area: ['390px', '200px'],
				  title: '提示', //不显示标题
				  content: $('.scts_setUp_save'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
				  cancel: function(){
				    layer.close();
				  },
				  btn: ['确定','取消'],//按钮
				  btn1: function(index, layero){
					 
				  }
			}); 
			  $(".layui-layer-btn a.layui-layer-btn0").addClass("button_blue");
			  $(".layui-layer-btn a.layui-layer-btn1").addClass("button_grey");
		  },
		  btn2: function(index, layero){
			  
		  }
	});
	$(".layui-layer-btn a.layui-layer-btn0").addClass("button_blue");
	$(".layui-layer-btn a.layui-layer-btn1").addClass("button_yellow");
	$(".layui-layer-btn a.layui-layer-btn2").addClass("button_grey");
})
$(".yyfgd").click(function(){
	$.initWinOnTopFromLeft_qyjx('桌面应用/覆盖到','${pageContext.request.contextPath}/views/demo/apply_center/c1_4.jsp', '600', '482');
})
$(function(){
	$('.scts_setUp p i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       // $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        //$('.ck').addClass('on');
    }
	});
	var w=$('.app_drag .app_dright').width()-44;
        $(".app_drag .app_dright .a_top .nav_div").width(w);
        var li_length=$('.app_drag .app_dright .a_top ul li').length*120;
        $('.app_drag .app_dright .a_top ul').width(li_length);
        if(w<li_length){
            $(".a_top .turn_btn .turn_right").addClass('on');
        }
        $(window).resize(function(){
            w=$('.app_drag .app_dright').width()-44;
            $(".app_drag .app_dright .a_top .nav_div").width(w);
            var ul_left=parseInt($('.a_top .nav_div ul').css('left'));
            if(w-ul_left>li_length){
                console.log(ul_left)
                $(".a_top .turn_btn .turn_right").removeClass('on');
            }else{
                $(".a_top .turn_btn .turn_right").addClass('on');
            }
        });
        $('body').on('click','.a_top .turn_btn .turn_right.on',function(){
            var ul_left=parseInt($('.a_top .nav_div ul').css('left'))-120;
            $('.a_top .nav_div ul').css('left',ul_left);
            if(w-ul_left>li_length){
                console.log('w:'+w+',ul_left:'+ul_left)
                $(this).removeClass('on');
            }
            if(ul_left==-120){
                $('.a_top .turn_btn .turn_left').addClass('on')
            }
        })
        $('body').on('click','.a_top .turn_btn .turn_left.on',function(){
            var ul_left=parseInt($('.a_top .nav_div ul').css('left'))+120;
            if(w-ul_left<li_length){
                $('.a_top .turn_btn .turn_right').addClass('on');
            }
            if(ul_left==0){
                $(this).removeClass('on')
            }
            $('.a_top .nav_div ul').css('left',ul_left);
        })
        $('.app_drag .app_dright .a_top ul li a').click(function(){
            $('.app_drag .app_dright .a_top ul li a').removeClass('on');
            $(this).addClass('on');
            $('.app_dright .app_list').hide();
            var i=$(this).parent().index();
            $('.app_dright .app_list').eq(i).show();
        });
        /*拖拽*/
        $(".app_list ul").sortable().disableSelection();
        /*左边全部拖拽时复制*/
        $(".all_list li").draggable({
            helper: "clone",
            revert: "invalid", // 当未被放置时，条目会还原回它的初始位置
        });
        /*接收左边拖拽内容*/
        $( ".app_dright .app_list ul").droppable({
          accept: ".all_list li",
          drop: function( event, ui ) {
            var $item = $( this );//放置位置
            var $list=ui.draggable;//拖拽对象
            var app_name=$list.children('.title').text();
            var i=0;
            $item.children().each(function(){
                if($(this).children('.title').text()==app_name){
                    i=1;
                }
            })
            if(i==0){
                $list.clone().appendTo($item);
            }else{
                
            }
          }
        });
        /*拖拽到右边顶部nav*/
        $( "#tabs li" ).droppable({
              accept: ".connectedSortable li",
              over: function( event, ui ) {
                $( "#tabs li a" ).removeClass('hover')
                 $(this).children().addClass('hover');
              },
              drop: function( event, ui ) {
                $( "#tabs li a" ).removeClass('hover');
                var $item = $( this );
                var i=$item.find( "a" ).data('id');
                var $list = $('.app_dright .app_list').eq(i).find( ".connectedSortable" );
                var $list1 =ui.draggable;//拖拽对象
                var app_name=$list1.children('.title').text();
                var j=0;
                 $('.app_dright .app_list').eq(i).children().children().each(function(){
                    if($(this).children('.title').text()==app_name){
                        j=1;
                    }
                 })
                if(j==0){
                    ui.draggable.hide( "slow", function() {
                      $('#tabs li a').eq(i).click();
                      $( this ).appendTo( $list ).show( "slow" );
                    });
                }else{
                    alert('已包含')
                }
              }
            });
        /*删除app*/
        $(".delete_app").droppable({
            accept: ".connectedSortable li",
            drop: function(event,ui){
            	layer.open({
				  type: 1,
				  shade: false,
				  area: ['390px', '200px'],
				  title: '提示', //不显示标题
				  content: $('.scts_delete_ts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
				  cancel: function(){
				    layer.close();
				  },
				  btn: ['确定','取消'],//按钮
				  btn1: function(index, layero){
					 ui.draggable.remove();
				  }
			}); 
			  $(".layui-layer-btn a.layui-layer-btn0").addClass("button_red");
			  $(".layui-layer-btn a.layui-layer-btn1").addClass("button_grey");
            }
        });
});
function serach_apply(){
	var apply_name=$('.apply_name').val();
	$(".app_drag .app_list ul li ").removeClass('pink')
	$('.app_drag .app_dright .a_top ul li a').removeClass('hover1')
	$(".app_drag .app_list ul li .title").each(function(){
		var school_html=$(this).text();
		school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
 		$(this).html(school_html)
		if(school_html.indexOf(apply_name)!=-1){
		var reg = new RegExp("("+apply_name +")","ig");
		school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
		$(this).html(school_html);
		$(this).parent().addClass('pink');
		}
	});
	$('.app_dright .app_list ul').each(function(index){
		if($(this).children('.pink').length==1){
			$('.app_drag .app_dright .a_top ul li').eq(index).children('a').addClass('hover1')
		}
	})
}
</script>
</body>
</html>