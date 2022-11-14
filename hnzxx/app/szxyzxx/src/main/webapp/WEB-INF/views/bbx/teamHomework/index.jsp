<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style type="text/css">
.container-fluid {
	padding-top: 20px;
}

.entry p {
	padding-top: 15px;
	padding-bottom: 10px;
	line-height: 24px;
}

.entry {
	background: url(${ctp }/res/css/bbx/images/context.jpg) 0px 0px;
}

.select_top {
	margin-top: 0px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg" id="classMasterSearch">
									<select id="bj" class="span4 chzn-select" style="width: 120px;" onchange="search()"></select>
									<select id="xk" name="xn" class="chzn-select" style="margin-top: -23px;">
										<option>全部学科</option>
									</select> 
									<input type="text" id="content" placeholder="内容" style="margin-top: -23px;">
									<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>
									<div class="content">								
										<div class="click">
										<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
											<button class="btn btn-warning" type="button" onclick="loadCreatePage();">布置作业</button>
											</c:if>
										</div>
									</div>	
									<!-- <select id="team" class="span4 chzn-select" onchange="getItem()">
									</select>
									<div class="content">
										<select id="xk" name="xn" class="chzn-select" style="margin:0;"></select> <input
											type="text" id="content" placeholder="内容" style="margin:0;">
										<div class="click">
											<button class="btn btn-success" type="button" id="sosuo"
												onclick="search()" >搜索</button>
											<button class="btn btn-warning" type="button"
												onclick="loadCreatePage();">布置作业</button>
										</div>
									</div> -->
								</div>
								<div class="s1 s1_bg" id="schoolManagerSearch">
									<div hidden>
										<select id="xn"></select>
									</div>
									<select id="nj" name="gradeId" style="width: 160px;"></select> 
									<select id="bj" name="teamId" style="width: 160px;" onchange=" getSubject();"></select>
									<select id="xk" name="xn" class="chzn-select" style="margin-top: -23px;">
										<option>全部学科</option>
									</select> 
									<input type="text" id="content" placeholder="内容" style="margin-top: -23px;">
									<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>
									<div class="content">								
										<div class="click">
											<button class="btn btn-warning" type="button" onclick="loadCreatePage();">布置作业</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div>
				<div id="homework_list_content">
					<jsp:include page="./homework.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="homework_list_content" />
					<jsp:param name="url"
						value="/clazz/teamHomeWork/index?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		/* var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#team",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   search();
				   getSubject();
				}	
		   }); */
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
					   getSubject();
					}	
			   });
		}
		
		
		$('#content').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				$("#sosuo").click();
			}
		}); 
		//点击科目搜索
		/* $("#xk").on("change", function(){
			$("#sosuo").click();
		}); */
	});
	
	
	function getItem(){
		search();
		getSubject();
	}
	function getSubject(){
		
		/* $("#xk").html("");*/
		/* alert($("#team").val())  */
		var teamId = $("#bj").val();
		//alert(teamId);
		if(teamId == ""){
			return;
		}
		var val = {
			teamId:teamId
		};
		$.get("${ctp}/clazz/teamHomework/subjectList", val, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#xk").html("");
				$("#xk").append("<option>全部学科</option>")
				$.each(data, function(index, value) {
					$("#xk").append("<option value='" + value.subjectCode + "'>" + value.subjectName + "</option>")
				});
			}
		});
		   
	}
	function search() {
		var val = {
				teamId:$("#bj").val()
		};
		var subjectCode = $("#xk").find("option:selected").val()
		if (subjectCode != null && subjectCode != ""&&subjectCode != "全部学科") {
			val.subjectCode = subjectCode;
		}
		var content = $("#content").val();
		if (content != null && content != "") {
			val.content = content;
		}
		var id = "homework_list_content";
		var url = "/clazz/teamHomework/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").val();
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
		}else
		$.initWinOnTopFromLeft_bbx('布置作业', '${ctp}/clazz/teamHomework/creator?teamId='+teamId, '700', '500');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		var teamId = $("#bj").val();
		$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/clazz/teamHomework/editor?id=' + id+'&teamId='+teamId, '700', '500');
	}
	
	//  加载浏览对话框
	function loadViewPage(homeworkId) {
		var teamId = $("#bj").val();
		$.initWinOnTopFromLeft_bbx('浏览详情', '${ctp}/clazz/teamHomework/viewer?homeworkId=' + homeworkId+'&teamId='+teamId, '750', '350');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/clazz/teamHomework/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_li").remove();
					search();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>