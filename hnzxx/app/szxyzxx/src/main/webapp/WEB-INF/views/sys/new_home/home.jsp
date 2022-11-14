<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%--	<%@ include file="/views/embedded/common.jsp"%>--%>
<%@include file="/views/embedded/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/res/plugin/falgun/ico/favicon.ico">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/autocomplete/autocomplete.css">
<link href="${pageContext.request.contextPath}/res/css/sygb/new_default_head.css" rel="stylesheet" class="skincsslittle">
<link href="${pageContext.request.contextPath}/res/css/sygb/new_left.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/date/lunar_calendar.js"></script>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
<title>${sca:getDefaultSchoolName()}</title>
</head>
<body id="main_body" style="overflow: hidden">
<div class="layout">
<jsp:include page="new_head.jsp"></jsp:include>
<div class="man_right">
	<iframe src="${ctp}/new_index?newWallpaperPath=${newWallpaperPath}"  marginheight="0" marginwidth="0" frameborder="0" scrolling="yes" width="100%" id="core_iframe" name="core_iframe"></iframe>
</div>
</div>
<script type="text/javascript">

	$(function(){
		var h=$(parent.window).height();
		$('#core_iframe').height(h-60);
		$(window).resize(function(){
			var h=$(parent.window).height();
			$('#core_iframe').height(h-60);
		})
		 var newWallpaperPath = '${newWallpaperPath}';
	        //alert(newWallpaperPath)
	        if(newWallpaperPath != ''){
	        	if(newWallpaperPath == 'new_default'){
	        		 $('.skin_list span').siblings().removeClass('on');
	        	     $('.skin_list span.span1').addClass('on');
	        	    // $('.skincsslittle').attr('href','${pageContext.request.contextPath}/res/css/sygb/'+cssPath+'_head.css');
	        	}else if(newWallpaperPath == 'new_night'){
		       		 $('.skin_list span').siblings().removeClass('on');
		       	     $('.skin_list span.span2').addClass('on');
	       		}else if(newWallpaperPath == 'new_three'){
		       		 $('.skin_list span').siblings().removeClass('on');
		       		$('.skin_list span.span3').addClass('on');
	       		}else if(newWallpaperPath == 'new_four'){
		      		 $('.skin_list span').siblings().removeClass('on');
		      		 $('.skin_list span.span4').addClass('on');
	  			}
	        }else{
	        	newWallpaperPath = 'new_default';
	        }
	        
	        $('.skincsslittle').attr('href','${pageContext.request.contextPath}/res/css/sygb/'+newWallpaperPath+'_head.css');
           // $('#core_iframe').contents().find('.index_css').attr('href','${pageContext.request.contextPath}/res/css/sygb/'+newWallpaperPath+'_index.css');

	})
	
</script>
</body>
</html>