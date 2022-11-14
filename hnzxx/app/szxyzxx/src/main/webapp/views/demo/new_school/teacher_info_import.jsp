<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title></title>
</head>
<body style="background-color: #e3e3e3;">
	<div class="sjcsh_xx_detail">
		<div class="f_left">
			<ul>
				<li class="on" id="tii_daoru_ok">导入成功</li>
				<li id="tii_warn_data">警告数据（152）</li>
				<li id="tii_wrong_data">错误数据（89）</li>
			</ul>
		</div>
		<div class="f_right">
			<button class="btn btn-blue">继续导入</button>
			<button class="btn btn-lightGray">批量删除</button>
		</div>
		<div class="f_right" style="display:none">
			<button class="btn btn-blue">继续导入</button>
			<button class="btn btn-red">批量删除</button>
			<div class="plsc_ts">
				<span class="ts">注：确定批量导入将覆盖冲突的数据</span>
				<span class="tu"><i class="fa fa-exclamation"></i></span>
			</div>
		</div>
		<div class="f_right"  style="display:none">
			<button class="btn btn-blue">继续导入</button>
		</div>
	</div>
	<div style="background-color: #ffffff;padding:20px;margin: 0 20px 20px;">
		<iframe src="tii_daoru_ok.jsp" width="100%" frameborder="0" id="iframe_third"></iframe>
	</div>
<script>
$('.sjcsh_xx_detail ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	$('.f_right').hide();
	$('.f_right').eq($(this).index()).show();
	var id = $(this).attr('id');
	$('#iframe_third').attr('src',id+'.jsp')
});
$('#iframe_third').load(function(){  
    var iframeHeight=$(this).contents().height();
    $(this).height(iframeHeight+'px');
});
</script>
</body>
</html>