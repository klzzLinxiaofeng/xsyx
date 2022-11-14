<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>


<!-- 班班星样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
<style type="text/css">
	.biao-ul .item-right{
		margin-left: 105px;
	}
</style>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid" style="padding-bottom: 50px;">
			<div class="span12">
				<div class="content-widgets">
					<%@ include file="/views/embedded/bpCommonIndex.jsp" %>
					<!-- <div class="widget-head">
							<div class="s1 s1_bg">
							<select id="teamId" onchange="search();" name="teamId" class="chzn-select" style="width: 120px;">
                        	</select>
							<div class="search">
								<button class="btn btn-primary" type="button">表扬统计</button>
								<button class="btn btn-warning" type="button" onclick="loadCreatePage();">添加表扬</button>
							</div>
						</div>
					</div> -->
					</div>
					<div class="content-widgets ">
						<div>
							<div id="praise_list_content">
									<%-- <jsp:include page="./list.jsp" /> --%>
							</div>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="praise_list_content" />
								<jsp:param name="url" value="/bbx/praise/index?sub=list&dm=${param.dm}" />
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
function search() {
	var val = {};
	var selectedOption = $("#bj").find("option:selected");
	if(selectedOption.val()!=''){// 选择一个班级
		val.teamId = selectedOption.val();
		val.teamName = selectedOption.text();
	}else{// 全部班级
		$.error("请选择班级");
		return;
		/* var options = $("#bj").find("option");
		var bjs = [];
		for (var i = 0; i < options.length; i++) {
			var teamId = options[i].value;
			if(teamId!=undefined && teamId!=''){
				teamIds.push(teamId);
			}
		}
		val.teamIds = teamIds.join(','); */
	}
	var id = "praise_list_content";
	var url = "/bbx/praise/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

// 	加载创建对话框
function loadCreatePage() {
	var teamId = $("#bj").find("option:selected").val();
	if(teamId == undefined || teamId=='' || teamId=='-1'){
		$.error("请选择一个班级!");
	}else
	$.initWinOnTopFromLeft_bbx('添加表扬', '${ctp}/bbx/praise/creator?teamId='+teamId, '780', '510');
}
//  加载编辑对话框
function loadEditPage(id) {
	$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/bbx/praise/editor?id=' + id, '780', '510');
}

function loadViewerPage(id) {
	$.initWinOnTopFromLeft_bbx('详情', '${ctp}/bbx/praise/viewer?id=' + id, '700', '300');
}

// 	删除对话框
function deleteObj(obj, id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(obj, id);
	});
}

// 	执行删除
function executeDel(obj, id) {
	$.post("${ctp}/bbx/praise/" + id, {"_method" : "delete"}, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除成功");
				$("#li_"+id).remove();
				/* setTimeout(function(){
					search();
				}, 1000); */
			} else if ("fail" === data) {
				$.error("删除失败，系统异常", 1);
			}
		}
		search();
	});
}

/* $(function(){
		$.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "selectOne" : false,
		   "condition" : {roleType:"${sessionScope[sca:currentUserKey()].currentRoleCode}"},
		   "selectedVal" : "",
		   "firstOptionTitle" : "全部班级",
		   "afterHandler" : function() {
			   search();
			}	
	   });
}); */
</script>
</html>