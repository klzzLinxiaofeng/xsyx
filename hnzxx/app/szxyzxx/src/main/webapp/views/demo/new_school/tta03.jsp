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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-ui.min.js" ></script>
<title></title>
<style>
    tr{cursor: pointer;}
</style>
</head>
<body style="background-color: #e3e3e3;"">
	<div class="sjcsh_xx_detail">
	<div class=" content_main">
		<div class="kmlb">
	 		<div class='f_left'>
	 			<p style="color: #666666;font-size:18px;font-weight:bold">科目列表</p>
	 		</div>
	 		<div class="f_right">
	 			<button class="btn btn-green" >刷新列表</button>
	 			<button class="btn btn-blue" >新增科目</button>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
	 	<div style="padding:15px 20px 5px">
	 		<div class='f_left'>
	 			<div class="select_div">
					<input type="text" placeholder="请输入科目名称">
					<button class="btn btn-blue" style="margin-top: -6px;">检索</button>
				</div>
	 		</div>
	 		<div class="f_right">
	 			<span style="color: #f44336;line-height: 35px;">拖动科目可以进行排序</span>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
	 	<div style="margin:0 20px;">
		<table class="table"  id="sort" style="border: 1px solid #dddddd;">
			<thead>
				<tr><th class="index">序号</th><th>科目名称</th><th>课程范围</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td class="index">1</td><td>语文</td><td>公共课程</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">2</td><td>数学</td><td>课程0</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">3</td><td>英语</td><td>课程1</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">4</td><td>物理</td><td>课程2</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">5</td><td>毛泽东思想</td><td>课程3</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">6</td><td>邓小平理论</td><td>公共课程</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
		</div>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
	</div>
<script>
//拖动
$(document).ready(function(){
    var fixHelperModified = function(e, tr) {
                var $originals = tr.children();
                var $helper = tr.clone();
                $helper.children().each(function(index) {
                    $(this).width($originals.eq(index).width())
                });
                return $helper;
            },
            updateIndex = function(e, ui) {
                $('td.index', ui.item.parent()).each(function (i) {
                    $(this).html(i + 1);
                });
            };
    $("#sort tbody").sortable({
        helper: fixHelperModified,
        stop: updateIndex
    }).disableSelection();
});
$( "tr" ).disableSelection();
</script>
</body>
</html>