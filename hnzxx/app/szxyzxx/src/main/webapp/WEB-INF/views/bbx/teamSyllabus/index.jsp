<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style>
.biao-ul .item-right{
	margin-left:0;
}
.ke-table table th, .ke-table table td{
	border-left: none;
}
</style>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="select_top">
							<div class="s1 s1_bg" id="classMasterSearch">
								<select id="bj" class="span4 chzn-select" style="width: 120px;"
									onchange="search()"></select>
								<div class="content">
									<div class="click">
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">调课通知</button>
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
								<button class="btn btn-success" style="margin-top: -23px;"
									id="sosuo" onclick="search()">搜索</button>
								<div class="content">
									<div class="click">
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">调课通知</button>
											</c:if>
									</div>
								</div>
							</div>
							<!-- <div class="s1 s1_bg">
							<select id="team" class="span4 chzn-select" onchange="search()">
							</select>
							<div class="search"><button class="btn btn-warning" type="button" onclick="loadCreatePage();"style="font-weight: bold;">调课通知</button></div>
						</div> -->
					</div>
					</div>
					<div class="content-widgets">
						<div id="syllabus_list_content"> 
							<jsp:include page="./syllabus.jsp" />
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    /* $(function(){
    	var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
    	$.BbxRoleTeamAccountSelector({
			   "selector" : "#team",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   search();
				}	
		   });
    }); */
    
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
    
	function search() {
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		var val = {
			teamId:teamId
		};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "syllabus_list_content";
		var url = "/clazz/teamSyllabus/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").find("option:selected").val()
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
			return;
		}
		$.initWinOnTopFromLeft_bbx('调课通知', '${ctp}/clazz/teamSyllabus/creator?teamId='+teamId, '600', '400');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑通知', '${ctp}/clazz/teamSyllabus/editor?id=' + id, '600', '400');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/clazz/teamSyllabus/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#"+id+"_li").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	} 
</script>
</html>