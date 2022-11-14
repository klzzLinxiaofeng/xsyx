<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<style>
.input_margin{margin-top: -15px;
padding: 0;
height: 30px;margin-left:5px;}
.button_margin{margin-top: -26px; height: 30px}
</style>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="select_top">
						<div class="s1 s1_bg" id="classMasterSearch">
							<select id="bj" class="span4 chzn-select" style=""
								onchange="search()"></select>
							<input type="text" id="name" name="name" class="button_margin" style="margin-bottom:0;margin-left:10px;">
							<button class="btn btn-success button_margin" type="button"
									onclick="search();">搜索</button>
							<div class="content">
								<div class="click">
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
										</c:if>
								</div>
							</div>
						</div>
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<div hidden>
								<select id="xn"></select>
							</div>
							<select id="nj" name="gradeId" style="width: 160px;"></select> 
							<select id="bj" name="teamId" style="width: 160px;"></select>
							<input type="text" id="name" class='input_margin' name="name">
							<button class="btn btn-success button_margin" style="margin-top: -23px;"
								id="sosuo" onclick="search()">搜索</button>
							<div class="content">
								<div class="click">
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
										</c:if>
								</div>
							</div>
						</div>
						<!-- <div class="s1 s1_bg">
							<select id="teamId" onchange="search();"></select>
							<div class="search">
								<input type="text" id="name" name="name">
								<button class="btn btn-success" type="button"
									onclick="search();">搜索</button>
								<button class="btn btn-warning" type="button"
									onclick="loadCreatePage();">添加荣誉</button>
							</div>
						</div> -->
						<!-- <div class="s2">
							获得荣誉：<span class="a1" id="totalNum">0</span> 个
						</div> -->
					</div>
					<div class="content-widgets">
						<div>
							<div id="teamAward_list_content">
							   <jsp:include page="./list.jsp" />
							</div>
							<div>
							 <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="teamAward_list_content" />
								<jsp:param name="url"
									value="/bbx/teamAward/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div> 
							<%-- <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="teamAward_list_content" />
								<jsp:param name="url"
									value="/bbx/teamAward/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div> --%>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
$(function(){		
	// alert('${sessionScope[sca:currentUserKey()].currentRoleCode}');		
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	if(currentRoleCode == "SCHOOL_LEADER"){
		$("#classMasterSearch").html("");
		$("#classMasterSearch").hide();
		$.initCascadeSelector({
			"type" : "team",
			"teamCallback" : function($this) {
			}
		});		
	}else{
		$("#schoolManagerSearch").html("");		
		$("#schoolManagerSearch").hide();		
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#bj",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   search();
				}	
		   });
	}	
});

	   /* $.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : {roleType:"CLASS_MASTER"},
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   }); */


	function search() {
		var val = {};
		
		var name = $("#name").val();
		
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		
		if(teamId!=null && teamId!=""){
			val.teamId = teamId;
		}
		if (name != null && name != "") {
			val.name = name;
		}
		
		
		var id = "teamAward_list_content";
		var url = "/bbx/teamAward/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").val();
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
			return;
		}
		$.initWinOnTopFromLeft_bbx('添加荣誉', '${ctp}/bbx/teamAward/creator?teamId='+teamId, '700', '400');
	}
	
	//  加载编辑对话框
	function loadEditPage(id) {
		var teamId = $("#bj").val();
		$.initWinOnTopFromLeft_bbx('编辑荣誉', '${ctp}/bbx/teamAward/editor?id=' + id+"&teamId="+teamId, '720', '400');
	}
	
// 	function loadViewerPage(id) {
// 		$.initWinOnTopFromLeft('详情', '${ctp}/clazz/teamAward/viewer?id=' + id, '700', '300');
// 	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/teamAward/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#li_" + id ).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			search();
		});
	}
</script>
</html>