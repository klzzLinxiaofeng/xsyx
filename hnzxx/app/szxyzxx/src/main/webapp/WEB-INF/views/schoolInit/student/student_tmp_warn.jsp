<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>班内学号</th>
			<th>学生姓名</th>
			<th>年级</th>
			<th>班级</th>
			<th>监护人</th>
			<th>监护人手机</th>
			<th>异常提示</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody style="background-color: #fffdf1;">
		<c:forEach items="${result }" var="student">
			<tr>
				<td><i class="ck"></i></td>
				<td>${student.id }</td>
				<td>${student.num }</td>
				<td>${student.name }</td>
				<td>${student.grade }</td>
				<td>${student.team }</td>
				<td>${student.guardian }</td>
				<td>${student.guardianPhone }</td>
				<td class="color_f44336">${student.errorInfo }</td>
				<td class="caozuo">
					<button class="btn btn-green" onclick="editStudent(${student.id })">编辑</button>
					<button class="btn btn-red" onclick="deleteTmp(${student.id})">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script>
function editStudent(id){
	$.initWinOnTopFromLeft_qyjx("编辑教师信息",  '${pageContext.request.contextPath}/tmp/teacher/get?id='+id, '458', '493');
}

function deleteTmp(id) {
	var ids = new Array();
	ids[0] = id;
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/student/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	$.success("删除成功 !");
        	var size = $(window.parent.document).find("#spi_warn_data").attr("size");
        	size=size-1;
        	$(window.parent.document).find("#spi_warn_data").text("警告数据（"+size+"）");
        	$(window.parent.document).find("#spi_warn_data").attr("size",size);
        	var size = $(window.parent.document).find("#spi_warn_data").click();
        }
    });
}
</script>