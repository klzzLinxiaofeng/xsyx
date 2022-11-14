<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
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
								<input type="text" id="name" placeholder="请输入活动标题的关键字" style="margin-top: -15px;margin-left: 10px;"/>
								<button class="btn btn-success" style="margin-top: -23px;"
									id="sosuo" onclick="search()">搜索</button>
								<div class="content">
									<div class="click">
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">组织活动</button>
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
								<input type="text" id="name" placeholder="请输入活动标题的关键字" style="margin-top: -15px;margin-left: 10px;"/>
								<button class="btn btn-success" style="margin-top: -23px;"
									id="sosuo" onclick="search()">搜索</button>
								<div class="content">
									<div class="click">
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">组织活动</button>
									</div>
								</div>
							</div>
							<!-- <div class="s1 s1_bg">
								<select id="team" class="span4 chzn-select" onchange="search()">
								</select>
								<div class="search">
									<input type="text" id="name" placeholder="请输入活动标题的关键字"/>
									<button class="btn btn-success" id="sosuo" type="button" onclick="search();">搜索</button>
								<button class="btn btn-warning" type="button" onclick="loadCreatePage();">组织活动</button>
								</div>
							</div> -->
						</div>
					</div>
					<div class="content-widgets">
						<div>
						<div id="activity_list_content">
							<jsp:include page="./activity.jsp" />
						</div>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="activity_list_content" />
								<jsp:param name="url" value="/clazz/teamActivity/index?sub=list&dm=${param.dm}" />
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
    	$('#name').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				$("#sosuo").click();
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
		$('#name').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				$("#sosuo").click();
			}
		}); 
	});
    
	function search() {
		var val = {
				
		};
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "activity_list_content";
		var url = "/clazz/teamActivity/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").find("option:selected").val()
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
		}else
		$.initWinOnTopFromLeft_bbx('组织活动', '${ctp}/clazz/teamActivity/creator?teamId='+teamId, '700', '550');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/clazz/teamActivity/editor?id=' + id, '700', '550');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/clazz/teamActivity/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#"+id+"_li").remove();
					search();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	} 
</script>
</html>