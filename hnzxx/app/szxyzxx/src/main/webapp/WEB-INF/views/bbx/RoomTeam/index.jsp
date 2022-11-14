<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	 <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教室班级绑定" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
            <div class="span12">
            <div class="content-widgets white">
            	<div class="widget-head">
					<h3>教室班级绑定
					<%-- <p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">	
									<a href="javascript:void(0)" class="a4" onclick="loadCreateTeamPage();">
									  <i class="fa fa-plus"></i>新增班级
									</a>
									<a href="javascript:void(0)" class="a4" onclick="createTeamBatchPage();">
									  <i class="fa fa-plus"></i>批量新增班级
									</a>
								</c:if>
							</p> --%>
					</h3>
					<div class="light_grey"></div>
                	<div class="content-widgets"></div>

						<input type="hidden" id="gradeId" name="gradeId" vlalue="" />
						<div class="select_c" id="select_c">
						</div>
						<div class="widget-container">
                        	<table class="table  table-striped responsive" id="tableId">
	                            <thead>
	                           		<tr >
									   <th>班级编号</th>
			                           <th>校内名称</th>
			                           <th>标准名称</th>
			                           <th>校内编码</th>
			                           <th>绑定教室</th>
			                           <th class="caozuo">操作</th>
									</tr>
	                            </thead>
	                            <tbody id="tbodyId">
	                            </tbody>
                            </table>
<%--                              <jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 								<jsp:param name="id" value="module_list_content" /> --%>
<%-- 								<jsp:param name="url"  value="/teach/team/teamList?sub=list" /> --%>
<%-- 								<jsp:param name="pageSize" value="${page.pageSize}" /> --%>
<%-- 							</jsp:include> --%>
							<div class="clear"></div>
                    	</div>
                	</div>
                </div>
            </div>
        </div>
	</div>
	
	
	
<script>
	$(function(){
		
		var $requestData = {"schoolYear" : "${sessionScope[sca:currentUserKey()].schoolYear}"};
		
		$(".select_c").on("click", "a", function(){
			$(".select_c a").removeClass("on");
			$(this).addClass("on");
			getGrade($(this).attr("data-obj-id"));
		});
		
		$.getGrade($requestData, function(data) {
			if(data.length > 0) {
				$.each(data, function(index, value) {
					$("#select_c").append('<a data-obj-id="' + value.id + '" href="javascript:void(0);">' + value.name + '</a>')
				});
				$("#select_c").find("a:first").addClass("on");
				var gradeId = $(".select_c .on:first").attr("data-obj-id");
				$("#gradeId").val(gradeId);
				ajaxFunction(gradeId, function(){});
			} else {
				$.alert("当前学年还没有建立年级数据，请先到【年级管理】栏目进行初始化");
			}
		});		
	});
		
	function loadGrade(data){
		var select_temp = $("#select_c");
		var tempGrade = "";
		for (var i = 0, len = data.length; i < len; i++) {
			if(i==0){
				tempGrade+="<a data-obj-id='"+data[i].id+"' href='javascript:void(0)' onclick='getGrade("+data[i].id+", this)' class='on' >"+data[i].name+"</a>";
				ajaxFunction(data[i].id, function(){});
				$("#gradeId").val(data[i].id);
			}else{
				tempGrade+="<a data-obj-id='"+data[i].id+"' href='javascript:void(0)' onclick='getGrade("+data[i].id+", this)'>"+data[i].name+"</a>";
			}
		}
		select_temp.html(tempGrade);
	}	
	
	function ajaxFunction1(){
		var gradeId = $(".select_c .on:first").attr("data-obj-id");
		ajaxFunction(gradeId, function(){})
	}
		
	function ajaxFunction(gradeId, afterHandler){
		var url = "${pageContext.request.contextPath}/bbx/RoomTeam/getAjaxRoomTeamList";
		var aj = $.ajax({
		    url: url,
		    data:'gradeId=' + gradeId,    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadTable(data);
		     },    
		     error : function() {
		         $.alert("异常！");    
		     }    
		});
	}

	function loadTable(data){
		var table = $("#tbodyId"); 
		var teamTrs ="";
		for (var i = 0, len = data.length; i < len; i++) {			
			var edit = "<button onclick='loadModifyTeamPage("+data[i].teamId+");' type='button' class='btn btn-blue'>绑定教室</button>";
			var del =  "<button onclick='deleteRoomTeam("+data[i].teamId+");' type='button' class='btn btn-red'>解除绑定</button>";
			var roomName = "";
			if(data[i].roomName != null){
				roomName = data[i].roomName;
			}
			teamTrs += '<tr><td>'+(i+1)+'</td><td>'+data[i].teamName+'</td><td>'+data[i].fullName
				+'</td><td>'+data[i].code+'</td><td>'+roomName+'</td><td class="caozuo">'+edit+del+'</td></tr>';
		}
		table.html(teamTrs);
	}			 
	
	function getGrade(gradeId, $this){
		$("#gradeId").val(gradeId);
		ajaxFunction(gradeId, function() {
			$(".select_c .on").removeClass("on");
			$($this).addClass("on");
		});
	}	
	
	
	function loadModifyTeamPage(teamId){
		$.initWinOnTopFromLeft('绑定班级教室', '${pageContext.request.contextPath}/bbx/RoomTeam/editor?teamId='+teamId, '600', '300');
	}
	
	
	function deleteRoomTeam(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/bbx/RoomTeam/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					ajaxFunction1();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常");
				}
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
		
		
		


	
	
	

	
</script>
</body>
</html>
