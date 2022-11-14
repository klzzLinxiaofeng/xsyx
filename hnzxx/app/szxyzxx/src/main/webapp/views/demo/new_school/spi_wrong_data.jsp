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
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>班内学号</th><th>学生姓名</th><th>年级</th><th>班级</th><th>监护人</th><th>监护人手机</th><th>异常提示</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody style="background-color: #fff1f1;">
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">姓名重复，无别名</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">已存在</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">姓名重复，无别名</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">手机号码重复</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">已存在</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="color_f44336">姓名重复，无别名</td><td class="caozuo"><button class="btn btn-green" onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
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
function editStudent(){
	$.initWinOnTopFromLeft_qyjx("编辑学生",  '${pageContext.request.contextPath}/views/demo/new_school/spi_edit.jsp', '458', '429');
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