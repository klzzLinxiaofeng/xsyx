<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
        <div class="row-fluid" style="padding-bottom: 50px;">
            <div class="span12">
                <div class="content-widgets" style="margin-bottom:0">
					<div class="select_top">
						<div class="s1 s1_bg" id="classMasterSearch">
							<select id="bj" class="span4 chzn-select" style="width: 120px;"
								onchange="search()"></select>
							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>
							<c:if test="${sessionScope[sca:currentUserKey()].currentRoleCode=='CLASS_MASTER'}">
                                <button class="btn btn-warning right" type="button" onclick="loadCreatePage();">添加个性化内容</button>
                             </c:if>
						</div>
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<div hidden>
								<select id="xn"></select>
							</div>
							<select id="nj" name="gradeId" style="width: 160px;"></select> 
							<select id="bj" name="teamId" style="width: 160px;"></select>
							<button class="btn btn-success" style="margin-top: -23px;"
								id="sosuo" onclick="search()">搜索</button>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                            <button style="" class="btn btn-warning right" type="button" onclick="loadCreatePage();">添加个性化内容</button>
                            </c:if>
						</div>
                	</div>
           	 	</div>
			</div>
     		<div id="individuationDisplay_list_content">
			</div>
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="individuationDisplay_list_content" />
				<jsp:param name="url" value="/clazz/individuationDisplay/index?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){	
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

function search() {
	var teamId = $("#bj").val();
	if(teamId == "" || teamId == null){
		$.error("请选择班级");
		return;
	}
	var val = {};
	val.teamId = teamId;
	//alert(val.teamId);
	var id = "individuationDisplay_list_content";
	var url = "/bbx/individuationDisplay/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

//	加载创建对话框
function loadCreatePage() {
	$.initWinOnTopFromLeft_bbx('添加个性化内容', '${ctp}/bbx/individuationDisplay/creator', '730', '550');
}

//	删除对话框
function deleteObj(obj, id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(obj, id);
	});
}

// 	执行删除
function executeDel(obj, id) {
	$.post("${ctp}/bbx/individuationDisplay/" + id, {"_method" : "delete"}, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除成功");
				$('#count').text($('#count').text()-1);
				search();
			} else if ("fail" === data) {
				$.error("删除失败，系统异常", 1);
			}
		}
	});
}
	
//加载编辑对话框
function loadEditPage(id) {
	$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/bbx/individuationDisplay/editor?id=' + id, '730', '550');
}


	
	
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft_bbx('详情', '${ctp}/bbx/circleMessage/viewer?id=' + id, '700', '300');
	}	
</script>
</html>