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
		<div class="select_top">
				<div class="s1 s1_bg" id="classMasterSearch">
					<select id="bj" class="span4 chzn-select" style="width: 120px;"
						onchange="search()"></select>
				</div>
				<div class="s1 s1_bg" id="schoolManagerSearch">
					<div hidden>
						<select id="xn"></select>
					</div>
					<select id="nj" name="gradeId" style="width: 160px;"></select> 
					<select id="bj" name="teamId" style="width: 160px;"></select>
					<button class="btn btn-success" style="margin-top: -23px;"
						id="sosuo" onclick="search()">搜索</button>
				</div>
				<!-- <div class="s1 s1_bg">
				<select id="team" class="span4 chzn-select" onchange="search()">
				</select>
				<div class="search">
					<input id="name" type="text" placeholder="姓名"/>
					<button class="btn btn-success" id="sosuo" onclick="find()">搜索</button>
				</div>
			</div> -->
		</div>
	</div>
		<div class="row-fluid">
			<div class="ryxz">
				<div id="classMember_list_content">
					<jsp:include page="./classMember.jsp" />
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
		// 给搜索框绑定回车事件
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
		// 给搜索框绑定回车事件
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
		var id = "classMember_list_content";
		var url = "/clazz/classMember/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
// 		find();
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").find("option:selected").val()
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		$.initWinOnTopFromLeft('组织活动', '${ctp}/clazz/teamActivity/creator?teamId='+teamId, '700', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/clazz/teamActivity/editor?id=' + id, '700', '600');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	
	function monitor(userId,teamId){
		var $requestData = {};
		$requestData.userId = userId;
		$requestData.teamId = teamId;
		var url = "${pageContext.request.contextPath}/clazz/classMember/setMonitor";
		$.post(url, $requestData, function(data, status) {
			if ("success" === status) {
				$.success("设置成功");
			} else if ("fail" === status) {
				$.error("设置失败");
			}
			search();
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/clazz/teamActivity/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#"+id).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	} 
	//查询
	function find(){
		var name = $("#name").val().trim();
		alert(name);
		if(name!=null && name!=""){
			$("#studentList ul li").each(function(){
				if($(this).children("span").html().indexOf(name)==-1){
					$(this).hide();
				}
			})
			$("#teacherList ul li").each(function(){
	// 			var name = $("#name").val().trim();
				if($(this).children("span").html().indexOf(name)==-1){
					$(this).hide();
				}
			})
			
		}else{
			search();
		}
	}
</script>
</html>