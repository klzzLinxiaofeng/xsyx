<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="宿舍人员" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">  
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							宿舍人员列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" style="position:absolute;right:380px;top:6px;border-radius:0; height:30px;l"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a3"
									onclick="loadPlacePage();"><i class="fa  fa-plus"></i>批量调换宿舍人员</a>
							</c:if>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>批量分配宿舍人员</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/dormitoryPersonCheck" method="get" style="position:relative;">
						<button type="submit" class="btn btn-info  a2 fa fa-plus" style="  position:absolute;right:261px;top:-55px;border-radius:0; background-color:#EFAD4D; height:30px;l">&nbsp;&nbsp;导出数据 </button>
							<div class="select_b">
								<div class="select_div" hidden>
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
								    <span>入住性别：</span>
								    <select id="sex" name="sex" style="width:120px;">
								    </select>
								</div>
								<div class="select_div">
									<span>关键字：</span>
									<input type="text" id="dormitoryCode" name="dormitoryCode" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="寝室编号" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
							</div>
								<div class="clear"></div>
							</form>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>宿舍楼号</th>
											<th>寝室编号</th>
											<th>入住性别</th>
											<th>可住人数</th>
											<th>入住人员</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="dormitoryPerson_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="dormitoryPerson_list_content" />
								<jsp:param name="url" value="/schoolaffair/dormitoryPerson/list?sub=list&dm=${param.dm}" />
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
	$.initCascadeSelector({"type" : "team"   ,  "teamCallback"  : function($this){search()}    });
	$.jcGcSelector("#sex", {"tc" : "GB-XB"});
});
	function search() {
		var val = {
				
				"schoolYearId":$("#xn").val(),
				"gradeId":$("#nj").val(),
				"teamNumber":$("#bj").val(),
 				"sex":$("#sex").val(),
				"dormitoryCode":$("#dormitoryCode").val()
		}; 

		var id = "dormitoryPerson_list_content";
		var url = "/schoolaffair/dormitoryPerson/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载批量分配对话框
	function loadCreatePage() {
		var year = $("#xn").val();
		$.initWinOnTopFromLeft('批量分配', '${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/creator?year='+year, '1200', '600');
	}
	
	// 	加载批量调换对话框
	function loadPlacePage() {
		var year = $("#xn").val();
		$.initWinOnTopFromLeft('批量调换', '${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/place?year='+year, '1200', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnCurFromLeft('编辑', '${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/editor?id=' + id, '700', '500');
	}
	
	function loadViewerPage(dormitoryId,dormitoryCode) {
		$.initWinOnTopFromLeft('查看', '${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/viewer?dormitoryId=' + dormitoryId , '700', '300');
	}
	
	// 	删除对话框
	function deleteAll(obj, schoolYearId,dormitoryId) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, schoolYearId,dormitoryId);
		});
	}

	
	// 	执行删除
	function executeDel(obj, schoolYearId,dormitoryId) {
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/deleteForDormitoryCode?schoolYearId="+schoolYearId+"&dormitoryId="+dormitoryId;
		$.post(url, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					search();
					$("#" + dormitoryCode + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			
		});
	}
</script>
</html>