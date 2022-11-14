<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
table th, table td {
	height: 29px;
	line-height: 29px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="年级科目管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>年级科目管理
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
<!-- 										<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();"> -->
<!-- 										<i class="fa fa-plus"></i>添加科目</a> -->
										<a href="javascript:void(0)" class="a4" onclick="loadCreateBatchPage();">
										<i class="fa fa-plus"></i>批量添加年级科目</a>
								    </c:if>
							</p>
						</h3>
						<div class="light_grey"></div>
						<div class="content-widgets">
							<input type="hidden" id="gradeId" name="gradeId" value=""/>
							<div class="select_c" id="grade_select">
<%-- 								<c:forEach items="${gradeList}" var="grade" varStatus="status"> --%>
<%-- 									<a data-obj-id="${grade.id}" data-obj-name="${grade.name }" --%>
<!-- 										href="javascript:void(0)" -->
<%-- 										onclick="ajaxFunction('${grade.id }','${grade.fullName }')" --%>
<%-- 										<c:if test='${status.index==0}'>class="on"</c:if>>${grade.fullName }</a> --%>
<%-- 								</c:forEach> --%>
							</div>
							<div class="widget-container" id="code_Nation">
								<!-- <div class="widget-head" style="border-bottom:0 none;">
									<h3>添加科目
										<p style="float:right;margin-bottom:3px;" class="btn_link">
											<a class="a3" href="javascript:void(0)" onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加科目</a>
										</p>
									</h3>
								</div> -->
								<div style="position: relative; width: 100%;">
									<table class="table  table-striped responsive">
										<thead>
											<tr role="row">
												<th>学段</th>
												<th>年级全称</th>
		                            			<th>科目</th>
		                            			<th class="caozuo" style="padding-right: 22px;">操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	$(function() {
		$(".select_c").on("click", "a", function() {
			$(".select_c a").removeClass("on");
			$(this).addClass("on");
			
			ajaxFunction($(this).attr("data-obj-id"), $(this).attr("data-obj-name"))
		})
		
		
		var $requestData = {"schoolYear" : "${sessionScope[sca:currentUserKey()].schoolYear}"};
		$.getGrade($requestData, function(data) {
			if(data.length > 0) {
				$.each(data, function(index, value) {
					$("#grade_select").append('<a data-obj-name="' + value.name + '" data-obj-id="' + value.id + '" href="javascript:void(0);">' + value.fullName + '</a>')
				});
				$("#grade_select").find("a:first").addClass("on");
				var gradeId = $(".select_c .on:first").attr("data-obj-id");
				var gradeName = $(".select_c .on:first").attr("data-obj-name");
				$("#gradeId").val(gradeId);
				ajaxFunction(gradeId, gradeName);
			} else {
				$.alert("当前学年为初始化年级，请于【年级信息管理】栏目进行初始化");
			}	
		});
	});

	function ajaxFunction(gradeId, gradeName) {
		$("#gradeId").val(gradeId);
		var url = "${pageContext.request.contextPath}/teach/subjectGrade/getSubjectGradeListByGradeCode";
		var aj = $.ajax({
			url : url,
			data : 'gradeId=' + gradeId,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				loadTable(data);
			},
			error : function() {
				$.alert("异常！");
			}
		});
	}
	function loadTable(data) {
		
		var tbodyId = $("#module_list_content");
		if (data.length == 0) {
			tbodyId.html("<tr ><td colspan='4'>暂无科目数据！</td></tr>");
			return false;
		} else {
			var subjectTrs = "";
			for (var i = 0, len = data.length; i < len; i++) {
 				subjectTrs += "<tr><td>"+data[i].stageName+"</td><td>"+data[i].gradeName+"</td><td>"+data[i].subjectName+"</td><td class='caozuo'></td></tr>";
//  				subjectTrs += "<tr><td>"+data[i].stageName+"</td><td>"+data[i].gradeName+"</td><td>"+data[i].subjectName+"</td><td class='caozuo'><button class='btn btn-blue' type='button' onclick='loadModifyPage("+data[i].id+")'>编辑</button></td></tr>";
			}
			tbodyId.html(subjectTrs);
		}
	}
	
	function loadCreatePage(){
		var gradeId = $("#gradeId").val();
		$.initWinOnTopFromLeft("新增科目年级", "${pageContext.request.contextPath}/teach/subjectGrade/addSubjectGradePage?gradeId="+gradeId, '600', '460');
	}
	
	//加载批量创建年级科目列表
	function loadCreateBatchPage(){
		var gradeId = $("#gradeId").val();
		$.initWinOnTopFromLeft("新增年级科目", "${pageContext.request.contextPath}/teach/subjectGrade/addSubjectGradeBatchPage?gradeId="+gradeId, '1000', '600');
	}
	
	function loadModifyPage(subjectGradeId){
		var gradeId = $("#gradeId").val();
		$.initWinOnTopFromLeft("修改年级科目", "${pageContext.request.contextPath}/teach/subjectGrade/modifySubjectGradePage?subjectGradeId="+subjectGradeId+"&gradeId="+gradeId, '600', '460');
	}
</script>
</html>
