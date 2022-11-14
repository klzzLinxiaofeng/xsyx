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
	<div class="sjcsh_xx_table  content_main">
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>名称</th><th>性别</th><th>别名</th><th>手机号码</th><th>部门</th><th>职务</th><th>编制</th><th>异常提示</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody style="background-color: #fff1f1;">
				<tr><td><i class="ck"></i></td><td>1</td><td>王小明</td><td>男</td><td>小明</td><td>16757837676</td><td></td><td></td><td>在编</td><td class="color_f44336">手机号录入错误</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>2</td><td>李想</td><td>女</td><td>想想</td><td>16757837676</td><td>艺术部</td><td></td><td>在编</td><td class="color_f44336">姓名录入错误</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>3</td><td>刘大旭</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="color_f44336">姓名录入错误</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>4</td><td>张不但</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="color_f44336">姓名录入错误</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>5</td><td>吴晓婷</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="color_f44336">姓名录入错误/td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>6</td><td>秋衣</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="color_f44336">姓名录入错误</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
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
	</div>
<script>
function editTeacher(){
	$.initWinOnTopFromLeft_qyjx("编辑教师信息",  '${pageContext.request.contextPath}/views/demo/new_school/tis_edit.jsp', '458', '493');
}
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