<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学校管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学校列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">	
									<a href="javascript:void(0)" class="a2" onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增学校信息</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
					<%-- <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
					<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" /> --%>
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学校名称：</span><input type="text" id="name" name="name" data-id-container="name" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									 <th>学校名称</th>
			                         <th>英文名称</th>
			                         <th>学校代码</th>
			                         <th>统一代码</th>
			                         <th>学校类别</th>
			                         <th>办学类型</th>
			                         <th>创建时间</th>
			                         <th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url" value="/teach/school/schoolList?sub=list" />
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
	$(function(){
		search();
	})
	
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "module_list_content";
		var url = "/teach/school/schoolList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	//	加载创建角色对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('新增学校信息', '${pageContext.request.contextPath}/teach/school/schoolAddPage', '1000', '600');
	}
	
	function loadModifyPage(id){
		$.initWinOnTopFromLeft('修改学校信息', '${pageContext.request.contextPath}/teach/school/schoolModify?id='+id, '1000', '600');
	}
	
	function detailSchool(id){
		$.initWinOnTopFromLeft('学校详细信息', '${pageContext.request.contextPath}/teach/school/schoolDetail?id='+id, '1000', '600');
	}
	
	function deleteSchool(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/teach/school/deleteSchool?id=" + id, {"_method" : "delete"}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>