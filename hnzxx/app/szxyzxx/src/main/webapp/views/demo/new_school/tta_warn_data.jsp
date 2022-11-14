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
	<div class="select_div">
		<select class="span2"><option>年级</option></select>
		<select class="span2"><option>班级</option></select>
	</div>
	<div class="sjcsh_xx_table  content_main">
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>年级</th><th>班级</th><th>科目</th><th>任课教师</th><th>别名</th><th>手机号码</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody style="background-color: #fffdf1;">
				<tr><td><i class="ck"></i></td><td>1</td><td>三年级</td><td>（1）班</td><td>语文</td><td>李四</td><td>四儿</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>2</td><td>三年级</td><td>（1）班</td><td>数学</td><td>五三</td><td>三儿</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>3</td><td>三年级</td><td>（1）班</td><td>英语</td><td>李想</td><td>小想</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>4</td><td>三年级</td><td>（1）班</td><td>政治</td><td>李依依</td><td></td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>5</td><td>三年级</td><td>（1）班</td><td>历史</td><td>玩玩玩</td><td></td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>6</td><td>三年级</td><td>（1）班</td><td>化学</td><td>嚯嚯嚯</td><td></td><td>16757837676</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
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
	$.initWinOnTopFromLeft_qyjx("编辑任课教师",  '${pageContext.request.contextPath}/views/demo/new_school/tta_edit.jsp', '713', '619');
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