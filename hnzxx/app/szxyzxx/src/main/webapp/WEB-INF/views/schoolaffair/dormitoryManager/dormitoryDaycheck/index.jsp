<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet"/>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="日常检查" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>  
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							日常检查列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3" style="position:absolute;right:244px;top:3px;border-radius:0; height:30px;l"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加日常检查记录</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/dormitoryDayCheckOut" method="get" style="position:relative;">
						<button type="submit" class="btn btn-info  a2 fa fa-plus" style="  position:absolute;right:122px;top:-58px;border-radius:0; background-color:#EFAD4D; height:30px;l">&nbsp;&nbsp;导出数据 </button>
							<div class="select_b">
								 <div class="select_div" hidden >
									<span>学年：</span>
									<select id="xn" name="schoolYearId" style="width: 120px;"></select>
								</div>
						        <div class="select_div">
									<span>年级：</span>
									<select id="nj" name="gradeId" style="width: 120px;"></select>
						        </div>
						        <div class="select_div">
									<span>班级：</span>
									<select id="bj" name="teamNumber" style="width: 120px;"></select>
						        </div> 
						        <div class="select_div">
								    <span>检查类型：</span>
								    <select id="checkType" name="checkType" style="width:120px;">
								    </select>
								</div>
							
								<div class="select_div">
									<span>关键字：</span>
									<input type="text" id="dormitoryCode" name="dormitoryCode" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="寝室编号" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							</form>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>日期</th>
											<th>检查类型</th>
											<th>宿舍楼号</th>
											<th>寝室编号</th>
											<th>检查结果</th>
											<th>检查说明</th>
										<th class="caozuo" style="max-width: 250px;"></th>
									</tr>
								</thead>
								<tbody id="dormitoryDaycheck_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="dormitoryDaycheck_list_content" />
								<jsp:param name="url" value="/schoolaffair/dormitorydaycheck/list?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
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
	$.initCascadeSelector({"type" : "team", "teamCallback" : function($this){search()}});
	$.jcGcSelector("#checkType", {"tc" : "XY-YH-RCJCLX"});
});
	function search() {
		 var val = {
				 "schoolYearId":$("#xn").val(),
				 "gradeId":$("#nj").val(),
				 "teamNumber":$("#bj").val(),
				 "checkType":$("#checkType").val(),
				 "dormitoryCode":$("#dormitoryCode").val()
		 };
		
		var id = "dormitoryDaycheck_list_content";
		var url = "/schoolaffair/dormitorydaycheck/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('添加记录', '${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/creator', '650', '550');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/editor?id=' + id, '650', '550');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('查看', '${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/viewer?id=' + id, '650', '550');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			window.location.reload();
		});
	}
</script>
</html>