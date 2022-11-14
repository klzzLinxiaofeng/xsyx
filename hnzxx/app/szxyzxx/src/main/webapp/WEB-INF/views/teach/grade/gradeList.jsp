<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>年级列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="年级管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>年级列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<a href="javascript:void(0)" class="a2" onclick="toUpgradePage();">
											<i class="fa"></i>年级升级
										</a>
										<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();">
											<i class="fa fa-plus"></i>新增年级
										</a>
									</c:if>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
<!-- 					<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" /> -->
<!-- 					<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" /> -->
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span>
									<select id="schoolYear" name="schoolYear">
<!-- 										<c:forEach items="${schoolYearVoList }" var="schoolYearVo"> -->
<!-- 											<option value="${schoolYearVo.year }"  <c:if test="${schoolYearVo.flag==1 }">selected</c:if> >${schoolYearVo.name }</option> -->
<!-- 										</c:forEach> -->
									</select>
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped"
							id="data-table">
							<thead>
								<tr role="row">
									<th>国际名称</th>
		                            <th>校内名称</th>
		                            <th>学年</th>
		                            <th>学段</th>
<!-- 		                            <th>年级人数</th> -->
		                            <!-- <th>创建时间</th> -->
<!-- 		                            <th>结束时间</th> -->
		                            <th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url" value="/teach/grade/gradeList?sub=list" />
							<jsp:param name="pageSize" value="${page.pageSize }" />
						</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

$(function() {
	$.SchoolYearSelector({
		"selector" : "#schoolYear",
		"afterHandler" : function() {
			//search();
		}
	});
});



function search() {
	var val = {};
	//var name = $("#name").val();
	var schoolYear = $("#schoolYear").val();
	
// 	if (name != null && name != "") {
// 		val.name = name;
// 	}
	if (schoolYear != null && schoolYear != "") {
		val.schoolYear = schoolYear;
	}
	var id = "module_list_content";
	var url = "/teach/grade/gradeList?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

//	加载创建角色对话框
function loadCreatePage() {
	$.initWinOnTopFromLeft('新增年级', '${pageContext.request.contextPath}/teach/grade/addGradePage', '600', '400');
}

function loadModifyPage(id){
	$.initWinOnTopFromLeft('修改年级', '${pageContext.request.contextPath}/teach/grade/modifyGrade?id='+id, '600', '420');
}

function deleteGrade(id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(id);
	});
}

function getSubjectGradeList(gradeId){
	window.location.href="${pageContext.request.contextPath}/teach/grade/getSubjectGradeList?gradeId="+gradeId;
}

function executeDel(id) {
	$.post("${pageContext.request.contextPath}/teach/grade/deleteGrade?id=" + id, {"_method" : "delete"}, function(data, status) {
		if("success" === status) {
			if("success" === data) {
				$.success("删除成功");
				$("#" + id + "_tr").remove();
				window.location.reload();
			} else if("fail" === data) {
				$.error("删除失败，系统异常", 1);
			} else if("noDelete" === data){
				$.error("年级下面有班存在，不允许删 除");
			}
		}else{
			$.error("系统异常");
		}
	});
}

function toUpgradePage(){
	window.location.href="${pageContext.request.contextPath}/updata/upgradeIndex?dm=${param.dm}";
}
</script>
</html>