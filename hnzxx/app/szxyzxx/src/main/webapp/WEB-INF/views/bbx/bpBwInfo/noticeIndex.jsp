<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="寻物启事" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid" style="padding-bottom: 50px;">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							寻物启事
							<p class="btn_link" style="float: right;">
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>发布</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>班级：</span>
									<select  id="teamId" class="span4 chzn-select" style="width: 120px;" onchange="search()"></select>
								</div>
								<div class="clear"></div>
							</div>
								<div id="bwInfo_list_content">
<%-- 									<jsp:include page="./list.jsp" /> --%>
								</div>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="bwInfo_list_content" />
								<jsp:param name="url" value="/bbx/bpBwInfo/index?sub=list&dm=${param.dm}" />
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
	var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
	$.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : $requestData,
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   });
});


	function search() {
		var val = {};
		var teamId = $("#teamId").val();
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		var id = "bwInfo_list_content";
		var url = "/bbx/bpBwInfo/notice/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#teamId").find("option:selected").val()
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
		}
		$.initWinOnTopFromLeft('发布寻物启事', '${ctp}/bbx/bpBwInfo/noticeCreator?teamId='+teamId, '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwInfo/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwInfo/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwInfo/notice/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>