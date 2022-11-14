<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>名称</th>
			<th>性别</th>
			<th>别名</th>
			<th>手机号码</th>
			<th>部门</th>
			<th>职务</th>
			<th>异常提示</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody style="background-color: #fff1f1;">
		<c:forEach items="${result }" var="student">
			<tr>
				<td><i class="ck"></i></td>
				<td>${student.id }</td>
				<td>${student.name }</td>
				<td>${student.sex }</td>
				<td>${student.alias }</td>
				<td>${student.phone }</td>
				<td>${student.department }</td>
				<td>${student.position }</td>
				<td class="color_f44336">${student.errorInfo }</td>
				<td class="caozuo">
					<button class="btn btn-green" onclick="editTeacher(${student.id})">编辑</button>
					<button class="btn btn-red" onclick="deleteTmp(${student.id});">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script>
function editTeacher(id){
	$.initWinHasCloseCallback("编辑教师信息",  '${pageContext.request.contextPath}/tmp/teacher/get?id='+id, '458', '453', function() {
		if(getCookie("close_tab")=="close") {
			document.cookie="close_tab=open";
			var tii_wrong_data = $(window.parent.document).find("#tii_wrong_data");
        	var size = tii_wrong_data.attr("size");
        	size=size-1;
        	tii_wrong_data.text("错误数据（"+size+"）");
        	tii_wrong_data.attr("size",size);
        	tii_wrong_data.click();
		}
	});
}

function deleteTmp(id) {
	var ids = new Array();
	ids[0] = id;
	
	var loader = new loadDialog();
    loader.show();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/teacher/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功 !");
        	var size = $(window.parent.document).find("#tii_wrong_data").attr("size");
        	size=size-1;
        	$(window.parent.document).find("#tii_wrong_data").text("错误数据（"+size+"）");
        	$(window.parent.document).find("#tii_wrong_data").attr("size",size);
        	var size = $(window.parent.document).find("#tii_wrong_data").click();
        }
    });
}
</script>