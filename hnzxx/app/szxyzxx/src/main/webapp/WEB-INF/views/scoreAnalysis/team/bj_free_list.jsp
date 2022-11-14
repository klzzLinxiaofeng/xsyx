<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>个人考务列表</title>	
		<meta name="apple-touch-fullscreen" content="YES">
	    <meta name="format-detection" content="telephone=no">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta http-equiv="Expires" content="-1">
	    <meta http-equiv="pragram" content="no-cache">
	    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">  
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/all.css "/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/swiper.min.css "/>
		<!-- <script type="text/javascript" src="js/jquery-1.9.1.js"></script> -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/swiper.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layer/layer.js"></script>
		<style>
		.layui-layer-loading .layui-layer-content{margin:0 auto;}
		</style>
	</head>
	<body>
		<div class="cjfx">
			 <div class="swiper-container">
			 	<input type="hidden" id ="teamId" value="${teamId }"/>
			 	<input type="hidden" id ="termCode" value="${termCode }"/>
			 	<input type="hidden" id ="schoolYear" value="${schoolYear }"/>
			 	
			    <div class="swiper-wrapper">
			    <c:forEach items="${schoolCurrets}" var="schoolCurret">
			    <!-- 选中当前学年学期 -->
			    <c:choose>
			    	<c:when test="${schoolCurret.is_current == '1'}">
			    		<div class="swiper-slide term">
			      			<p class="choose">
								<span schoolYear="${schoolCurret.schoolYear }">${schoolCurret.schoolName }</span>
								<span termCode="${schoolCurret.termCode }">${schoolCurret.NAME}</span>
							</p>
			      		</div>
			    	</c:when>
			    	<c:otherwise>
			    		<div class="swiper-slide term">
			      			<p>
								<span schoolYear="${schoolCurret.schoolYear }">${schoolCurret.schoolName }</span>
								<span termCode="${schoolCurret.termCode }">${schoolCurret.NAME}</span>
							</p>
			      		</div>
			    	</c:otherwise>
			    </c:choose>
			    </c:forEach>
			    </div>
			 </div>
			
			<div class="term1">
				<div class="xzfw">
					<span class="on">全部</span>
					<span>年级统考</span>
					<span>班级测试</span>
				</div>
					<!-- 需要填充的DIV -->
					<div class="liebiao "> 
					
					</div>
			</div>
		</div>
		<script>
		
		var swiper = new Swiper('.swiper-container', {
				initialSlide :${slideIndex},
		      	slidesPerView: 3,
		      	slideToClickedSlide: true,
		      	centeredSlides : true,
		      	pagination: {
		        el: '.swiper-pagination',
		        clickable: true,
		      },
		    });
		var loader=layer.load();
			$(function(){
				//开始加载默认选中全部考务
				var teamId = $("#teamId").val(); //获取班级ID
				var termCode = $("#termCode").val(); //获取学期CODE
				var schoolYear = $("#schoolYear").val(); //获取学年
				
// 				var loader = new loadDialog(); //实例化对象
// 		 		loader.show(); //显示
		 		//return false;
				$(".liebiao").load("/scoreAnalysis/WeChat/loadData/"+teamId+"/"+termCode+"/"+schoolYear+"/"+0+"/${userId}/${schoolId}/${parentId}",function(){
					//loader.close(); //关闭
					layer.close(loader);
				});
				
				$('.cjfx .term p').click(function(){
					$(this).addClass('choose');
					$(this).parent('.swiper-slide').siblings().children('p').removeClass('choose');
					var index = $(this).parent('.swiper-slide').index();
					$('.cjfx .term1').siblings('.term1').hide();
					$('.cjfx .term1').eq(index).show();
					schoolYear = $(this).find('span').eq(0).attr('schoolYear');
					termCode = $(this).find('span').eq(1).attr('termCode');
					var index = $(".xzfw span.on").index();
					$(".liebiao").load("/scoreAnalysis/WeChat/loadData/"+teamId+"/"+termCode+"/"+schoolYear+"/"+index+"/${userId}/${schoolId}/${parentId}",function(){
						//loader.close(); //关闭
						layer.close(loader);
					});
				});
				$('.xzfw span').click(function(){
					var index;
					if($('.cjfx .term p:first-child').hasClass('choose')){
						index = 0;
					}else{
						index=1;
					}
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
					i = $(this).index();
					$(".liebiao").empty();
					$(".liebiao").load("/scoreAnalysis/WeChat/loadData/"+teamId+"/"+termCode+"/"+schoolYear+"/"+i+"/${userId}/${schoolId}/${parentId}",function(){
						layer.close(loader);
					});
					
				});
			})
		</script>
	</body>
</html>