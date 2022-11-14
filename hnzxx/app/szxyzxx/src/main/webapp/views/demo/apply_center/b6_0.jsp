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
<title>查看角色权限</title>
<style>
.input_select .select_div {
    float: none; 
}
.table thead tr th:nth-child(2){
	padding-left: 33px;
}

</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  权限管理  >   平台权限管理   ><span>查看角色权限</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>查看角色权限</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>角色：</span>
				<select class="span2"><option>校管</option></select>
			</div>
		</div>
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">角色可使用的应用：</span>
		</div>
		<table class="table" style="margin-bottom: 0px; ">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>图标</th><th>名称</th><th>状态</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="sj">上架</td></tr>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="sj">上架</td></tr>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="xj">下架</td></tr>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
		
		<div class="btn_cz" style="padding: 20px;border-top: 1px solid #dddddd;margin-top: 0px;">
			<button class="btn btn-blue">保存</button>
		</div>
		
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