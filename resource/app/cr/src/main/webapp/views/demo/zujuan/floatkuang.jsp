<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>浮动框</title>
</head>
<body>
	<div class="floatKuang">
		<div class="leftIcon"> 
			<ul class="icon">
				<li class="sjk on"></li>
				<li class="stk"></li>
				<li class="stl"></li>
				<li class="our"></li>		
			</ul>
		</div>
		<div class="central-content">
		sfddsa
		<div>
		<div class="kuang">
			<div class="stl">
				<i class="right" style="display:none;"></i>
				<i class="left"></i>
				<p>试题蓝</p>
				<span>0</span>
			</div>
			<div class="stl-content" style="display:none;">
				<a href="javascrip:void(0)" class="startZj btn-orange"><i></i>开始组卷</a>
				<br>
				<a href="javascrip:void(0)" class="recoveZj">恢复上一次组卷记录</a>
			</div>
			<div class="stl-content" style="display:none;">
				<p class="stl-content-top">
					<a href="javascrip:void(0)" class="btn-blue add-dt"><i></i>新增大题</a>
					<a href="javascrip:void(0)" class="btn-orange volume-set">批量设置</a>
					<a href="javascrip:void(0)" class="all-delete"><i></i></a>
				</p>
				<div class="stl-content-center">
					<ul>
						 <li>
							<span class="tm-title">
								<b class="th">而是三</b>、
								<b class="bt">单选题判断题填空题好多题耶耶耶</b>
							</span>
							<span class="del"></span>
							<span class="tm-num"><b style="float: left;color:#2299ee;font-weight: normal;margin-right: 3px;">200</b>题</span>
						</li>
						<li>
							<span class="tm-title"><b class="th">二</b>、<b class="bt">单选题判断题填空题好多题耶耶耶</b></span>
							<span class="del"></span>
							<span class="tm-num"><b style="float: left;color:#2299ee;font-weight: normal;margin-right: 3px;">200</b>题</span>
						</li> 
					
						
					</ul>
				</div>
				<p class="stl-content-bottom">
					<a href="javascrip:void(0)" class="btn-green">进入试题蓝<i></i></a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	//滚动条
	$(document).ready(function() {  
		$(".stl-content-center").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	}); 
	//左边选项
		$('ul.icon li').click(function(){
			$(this).addClass('on');
			$(this).siblings("li").removeClass('on');
		});
	
	//点击试题蓝弹出弹出。。。
		 $('.kuang .stl').click(function(){
			 
			 if($(".stl i.left").is(":hidden")){
					$(".stl i.left").show();
					$(".stl i.right").hide();
					$('.kuang').animate({right:'-303px'},"slow");
				}else{
					$(".stl i.left").hide();
					$(".stl i.right").show();
					$('.kuang').animate({right:'0'},"slow");
				}
		//有题目显示eq(1),没题目显示eq(2)	 
			var aa = 1;
			if(aa==0){
				$('.stl-content').eq(0).show();
			}else{
				$('.stl-content').eq(1).show();
			
			}
			
			
		}); 
		 var chinaeseOrder = ["一","二","三","四","五","六","七","八","九","十","十一",
		                      "十二","十三","十四","十五","十六","十七","十八","十九","二十"];
		
		 //删除小题
		 $('body').on('click','.del',function(){
			 $(this).parent('li').remove();
			
			 //重新排序
			 var xtm_num = $('.stl-content-center').find('li').length;
			 if(xtm_num>0){
				
				 $('.stl-content-center ul li').each(function(i){
					 $(this).find('.th').text(chinaeseOrder[i]);
					
				 })
			 }
		 });
		 //删除所有
		 $('.all-delete').click(function(){
			 $('.stl-content-center').children('ul').find('li').remove();
		 });
		 //新增大题
		 $('.add-dt').click(function(){
			 var aa = $('<li><span class="tm-title" style="display:none;"><b class="th"></b>、<b class="bt"></b></span>\n'+
			 			' <input type="text" value="" style="display: inline-block" class="sr-title"/>'+
						'<span class="del"></span>\n'+
						'<span class="tm-num"><b style="float: left;color:#2299ee;font-weight: normal;margin-right: 3px;">0</b>题</span>\n'+
					+'</li>');
			 var bb = $('.stl-content-center').children('ul').children('li').length;
			 if(bb>0){
				 $('.stl-content-center').children('ul').children(':last-child').after(aa);
			 }else{
				 $('.stl-content-center').children('ul').append(aa);
			 }
			 
			 $('.sr-title').focus();
		 });
		 
		 //新增大题文本框失去焦点
		  $('body').on('blur','.sr-title',function(){
			 
			    var newTitle = $(this).val();
			    if(newTitle==null || newTitle==""){
					layer.confirm("请输入1~20个字符", 
							{ btn: '确定',cancel:function(){
								$(".sr-title").focus();
								layer.closeAll();
							}},function () {
						$(".sr-title").focus();
						layer.closeAll();
			 		});
					return false;
				} 
			    $(this).hide();
			    $(this).prev('.tm-title').show();
			    //排序
			    var xtm_num = $('.stl-content-center').find('li').length;
				 if(xtm_num>0){
					 $(this).prev('.tm-title').children('.th').text(chinaeseOrder[xtm_num-1]);
					    $(this).prev('.tm-title').children('.bt').text(newTitle);
				 }else {
					 $(this).prev('.tm-title').children('.th').text(chinaeseOrder[0]);
					    $(this).prev('.tm-title').children('.bt').text(newTitle);
				 }
				
			} );
		 
		
		 
		 
	</script>
</body>
</html>