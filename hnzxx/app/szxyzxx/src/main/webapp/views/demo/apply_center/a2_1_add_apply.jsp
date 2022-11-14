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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<title>学校应用管理</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="content_main white" style="border-radius: 0;">
	<div class="input_select">
		<div class="select_div" style="display: inline-flex;">
			<span style="width: 80px;line-height: 30px;">主应用：</span>
			<select class="span2"><option>定邦教育云</option></select>
		</div>
		<div style="float:right">
			<input type="text" style="width: 160px;height: 18px;margin-top: 8px;">
			<button class="btn btn-blue" style="margin-top: 8px;">搜索</button>
		</div>
	</div>
	
</div>
<div class="add_apply">
	<ul>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
		<li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li>
	</ul>
</div>
<div class="btn_cz">
	<button class="btn btn-blue">添加</button>
	<button class="btn btn-lightGray">取消</button>
</div>
<script>
$(".add_apply").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
$('ul li  i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
    }else{
        $(this).addClass('on');
    }
});
</script>
</body>
</html>