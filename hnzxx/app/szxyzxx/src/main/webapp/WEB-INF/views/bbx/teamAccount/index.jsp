<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班班星账号管理</title>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style type="text/css">
	.select_top .s2 span{
		margin-right: 10px;
	}
	.table button{min-width: 70px;}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="select_top">
			<div class="s1 s1_bg"> 
			    <input type="hidden" value="${schoolId }" id="schoolId"/>
				<p class="school">${schoolName }</p>
				<div class="search">
<!-- 					<button class="btn btn-warning">导出Excel</button> -->
					<button class="btn btn-primary" onclick="back();">返回</button>
				</div>
			</div>
			<div class="s2">班级总数：<span class="a2" id="totalNum">0</span> 已开通：<span class="a2" id="teamAccountNum">0</span> 未开通：<span class="a2" id="unTeamAccountNum">0</span> 
			<div class="s_right">
			    <div hidden><select id="xn" ></select></div>
				<select id="nj" onchange="search();" ></select>
				<select id="bj" onchange="search();"></select>
			</div>  
			</div>
		</div>
	</div>
		<div class="row-fluid">
			<div class="zh_list">
				<table class="responsive table table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>班级名称</th>
							<th>班级账号</th>
<!-- 							<th>学生人数</th> -->
<!-- 							<th>教师数量</th> -->
							<th>开通情况</th>
							<th class="caozuo">操作</th>
						</tr>
					</thead>
					<tbody id="teamAccount_list_content">
						<jsp:include page="./list.jsp"/>
					</tbody>
				</table>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="teamAccount_list_content" />
					<jsp:param name="url"
						value="/bbx/teamAccount/index?sub=list&dm=${param.dm}&schoolId=${schoolId }" />
					<jsp:param name="pageSize" value="${page.pageSize }" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	
	$.initCascadeSelector({
		"schoolId": $("#schoolId").val(),
		"type" : "team",
		"isEchoSelected" : true,
		"yearSelectedVal" : "${schoolTermCurrent.schoolYear}", //要回显的学年唯一标识
		"teamCallback" : function($this){
			search();
		}
	});	

});

	//返回
	function back() {
		window.location.href = "${ctp}/bbx/teamAccountManager/index";
	}
	
	function search() {
		var val = {};
		var xn = $("#xn").val();
		var nj = $("#nj").val();
		var bj = $("#bj").val();
		if (xn != null && xn != "") {
			val.schoolYear = xn;
		}

		if (nj != null && nj != "") {
			val.gradeId = nj;
		}

		if (bj != null && bj != "") {
			val.id = bj;
		}

		var id = "teamAccount_list_content";
		var url = "/bbx/teamAccount/index?sub=list&dm=${param.dm}&schoolId="+${schoolId };
		myPagination(id, val, url);
	}
	

// 	加载创建对话框
	function loadCreatePage(obj,schoolId,teamId) {
		$.initWinOnTopFromLeft_bbx('开通班班星', '${ctp}/bbx/teamAccount/creator?schoolId='+schoolId+'&teamId='+teamId, '630', '275');
	}
	
	//加载修改班级账号密码对话框
	function revisePasswordPage(obj,schoolId,teamId,i){
		$.initWinOnTopFromLeft_bbx('修改密码', '${ctp}/bbx/teamAccount/revisePage?schoolId='+schoolId+'&teamId='+teamId, '630', '375');
	}
	
	/* //开通对话框
	function openTeamAccount(obj,schoolId,teamId){
		$.confirm("确定开通班级账号？", function() {
			executeOpenTeamAccount(obj,schoolId,teamId);
		});
		
	}
	
	//执行开通
	function executeOpenTeamAccount(obj,schoolId,teamId){
		$.post("${ctp}/bbx/teamAccount/openTeamAccount", {"schoolId":schoolId,"teamId":teamId}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.post("${ctp}/bbx/circle/openTeamCircle", {"teamId":teamId}, function(data, status) {
						if ("success" === status) {
							if ("success" === data) {
							} else {
								$.error("班级圈子开通失败", 1);
							}
						}
					});
					$.success("开通成功");
					window.location.reload();
				} else {
					$.error("开通失败", 1);
				}
			}
		});
	} */
	
	//取消开通对话框
	function closeTeamAccount(obj,schoolId,teamId){
		$.confirm("确定取消开通班级账号？", function() {
			executeCloseTeamAccount(obj,schoolId,teamId);
		});
	}
	
	//执行取消开通
	function executeCloseTeamAccount(obj,schoolId,teamId){
		$.post("${ctp}/bbx/teamAccount/closeTeamAccount", {"schoolId":schoolId,"teamId":teamId}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.post("${ctp}/bbx/circle/closeTeamCircle", {"teamId":teamId}, function(data, status) {
						if ("success" === status) {
							if ("success" === data) {
							} else {
								$.error("班级圈子取消开通失败", 1);
							}
						}
					});
					$.success("取消开通成功");
					window.location.reload();
				} else {
					$.error("取消开通失败", 1);
				}
			}
		});
	}
	
 	//重置班级账号密码对话框
	function resetPassword(obj,schoolId,teamId,i){
		$.confirm("确定重置班级账号密码？", function() {
			executeResetPassword(obj,schoolId,teamId,i);
		});
	}
	
 	
	//重置班级账号密码
	function executeResetPassword(obj,schoolId,teamId,i){
		$.post("${ctp}/bbx/teamAccount/resetPassword", {"schoolId":schoolId,"teamId":teamId,"type":i}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("重置成功");
					window.location.reload();
				} else {
					$.error("重置失败", 1);
				}
			}
		});
	}
	
	
</script>
</html>