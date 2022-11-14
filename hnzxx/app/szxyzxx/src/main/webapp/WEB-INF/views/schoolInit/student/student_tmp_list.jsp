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
<body style="background-color: #ffffff;">
	<div class="sjcsh_xx_table content_main">
		<div id="tmp_list">
			<c:if test="${status==0 }">
				<jsp:include page="./student_tmp_ok.jsp"/>
			</c:if>
			<c:if test="${status==1 }">
				<jsp:include page="./student_tmp_warn.jsp"/>
			</c:if>
			<c:if test="${status==2 }">
				<jsp:include page="./student_tmp_error.jsp"/>
			</c:if>
		</div>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="tmp_list" />
				<jsp:param name="url" value="/tmp/student/list?status=${status }" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
	<div class="tis_jxdr" style="display:none;text-align: center;padding-top:20px;">
		<p>继续导入将覆盖当前临时数据</p>
		<p>是否继续导入</p>
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