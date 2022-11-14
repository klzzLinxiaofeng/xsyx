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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>权限设置</title>
<style>
.content_main {
     box-shadow: none; 
    border: none; 
}

</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<div class="content_main white">
		<div class="input_select">
			<div class="select_div">
				<span style="font-size: 14px;">选择角色：</span>
			</div>
		</div>
		
		<table class="table">
			<thead>
				<tr style="border-top: 1px solid #dddddd;"><th><i class="ck" style="top: -11px;"></i></th><th>角色</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td>校管</td></tr>
				<tr><td><i class="ck"></i></td><td>班主任</td></tr>
				<tr><td><i class="ck"></i></td><td>学科教师</td></tr>
			</tbody>
		</table>
	</div>
	<div class="btn_cz">
		<button class="btn btn-blue">保存</button>
		<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
	</div>
</div>
<script>
$('.table tbody i.ck').click(function(){
	
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
    	   $('.table thead i.ck').removeClass('on');
       }
    }else{
        $(this).addClass('on');
        if($('tbody i.ck').length==$('tbody i.ck.on').length){
        	$('.table thead i.ck').addClass('on');
        }
    }
});
$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});

</script>
</body>
</html>