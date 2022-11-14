<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考试模式" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<%@ include file="/views/embedded/bpCommonIndex.jsp" %>
				<!-- <div class="content-widgets">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg">
									<select  id="teamId" class="span4 chzn-select" style="width: 120px;" onchange="search()"></select>	
									<div class="click">
										<button class="btn btn-warning" type="button" onclick="loadCreatePage();">发布</button>
									</div>
								</div>
							</div>
						</div>												
					</div>			
				</div> -->
			</div>
			
			<div>
            	<div id="bwExamInfo_list_content">
					<jsp:include page="./list.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="bwExamInfo_list_content" />
					<jsp:param name="url" value="/bbx/bpBwExamInfo/index?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
/* $(function(){
	var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
	$.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : $requestData,
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   });
}); */

	function search() {
		var teamId = $("#bj").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		var val = {};
		val.teamId = teamId;
		/* var teamId = $("#teamId").val();
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		} */
		var id = "bwExamInfo_list_content";
		var url = "/bbx/bpBwExamInfo/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		$.initWinOnTopFromLeft_bbx('发布考试信息', '${ctp}/bbx/bpBwExamInfo/creator?teamId='+teamId, '500', '430');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/bbx/bpBwExamInfo/editor?id=' + id, '500', '430');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwExamInfo/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwExamInfo/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
// 					$("#" + id + "_tr").remove();
					search();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>