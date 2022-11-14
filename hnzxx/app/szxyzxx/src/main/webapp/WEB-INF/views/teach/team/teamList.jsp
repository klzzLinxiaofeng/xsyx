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
			<jsp:param value="班级管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
	<div class="row-fluid">
            <div class="span12">
            <div class="content-widgets white">
            <div class="widget-head">
					<h3>班级管理
					<p class="btn_link" style="float: right;">
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
							</p>
					</h3>
			<div class="light_grey"></div>
                <div class="content-widgets">
<!-- 						<div class="select_b"> -->
							<%--<div class="select_div"><span>学年：</span>
								<select style="margin-bottom: 0; padding: 6px; width: 120px;" id="schoolYear" name="schoolYear" onchange="getSchoolYear();">
 									<c:forEach items="${schoolYearVoList}" var="schoolYear"> --%>
<%-- 				                   		<option value="${schoolYear.year }" <c:if test="${schoolYear.flag==1 }">selected</c:if> >${schoolYear.name }</option> --%>
<%-- 				                   	</c:forEach>
								</select> --%>
							</div>
							<!-- <button class="btn btn-primary">搜索</button> -->
							
<!--							<p style="float:right;margin-bottom:3px;" class="btn_link">
								<a class="a3" href="javascript:void(0)" onclick="$.refreshWin();"><i class="fa fa-plus"></i>刷新列表</a>
 								<a class="a2" href="javascript:void(0)"><i class="fa fa-download"></i>批量导入学生</a> -->
<!-- 								<a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>批量导入任课老师</a> -->
							<!--</p> -->
<!-- 							<div class="clear"></div> -->
<!-- 						</div> -->
						<input type="hidden" id="gradeId" name="gradeId" vlalue="" />
						<div class="select_c" id="select_c">
<%-- 							<c:forEach items="${gradeList}" var="grade" varStatus="status"> --%>
								
<%-- 							</c:forEach> --%>
						</div>
						<div class="widget-container">
					<!-- 	<div class="widget-head" style="border-bottom:0 none;">
							<h3>
								<p style="float:right;margin-bottom:3px;" class="btn_link">
									<a class="a3" href="javascript:void(0)" onclick="loadCreateTeamPage();"><i class="fa fa-plus"></i>新增班级</a>
								</p>
							</h3>
						</div> -->
                        <table class="table  table-striped responsive" id="tableId">
                            <thead>
                           		<tr >
								   <th>班级编号</th>
		                           <th>校内名称</th>
		                           <th>标准名称</th>
		                           <th>校内编码</th>
<!-- 		                           <th>班级成员数</th> -->
		                           <th class="caozuo">操作</th>
								</tr>
                            </thead>
                            <tbody id="tbodyId">
<!--                             	<jsp:include page="./list.jsp" /> -->
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
		
// 		function getSchoolYear(){
// 			var schoolYear = $("#schoolYear").find("option:selected").val();
// 			var url = "${pageContext.request.contextPath}/teach/team/getAjaxGradeList";
// 			var aj = $.ajax({
// 			    url: url,
// 			    data:'schoolYear='+schoolYear,    
// 			    type:'post',    
// 			    cache:false,    
// 			    dataType:'json',    
// 			    success:function(data) {
// 			    	loadGrade(data);
// 			     },    
// 			     error : function() {
// 			         $.alert("异常！");   
// 			     }    
// 			});
// 		}
		
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
		
		 function ajaxFunction(gradeId, afterHandler){
				var url = "${pageContext.request.contextPath}/teach/team/getAjaxTeamList";
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
				var myDate = new Date(data[i].finishDate);
				var finishDate = myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate();
				var myBeginDate = new Date(data[i].beginDate); 
				var beginDate = myBeginDate.getFullYear()+"-"+myBeginDate.getMonth()+"-"+myBeginDate.getDate();
				
				var edit = "<button onclick='loadModifyTeamPage("+data[i].id+");' type='button' class='btn btn-blue'>编辑</button>";
				var del = "";
				console.log(data[i].isDelete);
				if(data[i].isDelete) {
					del +=  "<button onclick='deleteTeam("+data[i].id+");' type='button' class='btn btn-red'>删除</button>";
				} else {
					del +=  "<button type='button' class='btn btn-lightGray'>删除</button>";
				}
				
				//var del="";
				var num="";
				if(data[i].memberCount==null){
					num="0";
				}else{
				   num = data[i].memberCount;
				}
				var code2="";
				if(data[i].code2==null){
					code2="";
				}else{
					code2=data[i].code2;
				}
				/* teamTrs += '<tr><td>'+data[i].teamNumber+'</td><td>'+data[i].name+'</td><td>'+data[i].fullName+'</td><td>'+num+'</td><td>'+data[i].code+'</td><td class="caozuo">'+edit+''+del+'</td></tr>'; */
				//teamTrs += '<tr><td>'+data[i].teamNumber+'</td><td>'+data[i].name+'</td><td>'+data[i].fullName+'</td><td>'+num+'</td><td class="caozuo">'+edit+''+del+'</td></tr>';
				teamTrs += '<tr><td>'+data[i].teamNumber+'</td><td>'+data[i].name+'</td><td>'+data[i].fullName+'</td><td>'+code2+'</td><td class="caozuo">'+edit+''+del+'</td></tr>';
			}
			table.html(teamTrs);
		}
		
		function search() {
			var val = {};
			var name = $("#name").val();
			if (name != null && name != "") {
				val.name = name;
			}
			var id = "module_list_content";
			var url = "/teach/team/teamList?sub=list&dm=${param.dm}";
			myPagination(id, val, url);
		}
		
		function getGrade(gradeId, $this){
			$("#gradeId").val(gradeId);
			ajaxFunction(gradeId, function() {
				$(".select_c .on").removeClass("on");
				$($this).addClass("on");
			});
		}

	function loadCreateTeamPage() {
// 		var schoolYear = $("#schoolYear option:selected") .val();
		var gradeId = $("#gradeId").val();
		//判断如果还没有年级的提示先创建年级
		if(gradeId == null || gradeId == ""){
			$.alert("当前没有年级，请先去“年级信息管理”创建年级！");
			return;
		}
        var schoolYear = "${sessionScope[sca:currentUserKey()].schoolYear}";
        if(schoolYear == null || schoolYear == ""){
            $.alert("请先设置当前学期");
            return;
        }
		$.initWinOnTopFromLeft('新增班级', '${pageContext.request.contextPath}/teach/team/addTeamPage?gradeId='+gradeId, '600', '500');
// 		$.initWinOnTopFromLeft('新增班级', '${pageContext.request.contextPath}/teach/team/addTeamPage?gradeId='+gradeId+'&schoolYear='+schoolYear, '600', '500');
	}
	
	//批量创建班级页面
	function createTeamBatchPage() {
		var gradeId = $("#gradeId").val();
		//判断如果还没有年级的提示先创建年级
		if(gradeId == null || gradeId == ""){
			$.alert("当前没有年级，请先去“年级信息管理”创建年级！");
			return;
		}
        var schoolYear = "${sessionScope[sca:currentUserKey()].schoolYear}";
        if(schoolYear == null || schoolYear == ""){
            $.alert("请先设置当前学期");
            return;
        }
		$.initWinOnTopFromLeft('批量新增班级', '${pageContext.request.contextPath}/teach/team/addTeamBatchPage?gradeId='+gradeId+'&schoolYear='+schoolYear, '800', '600');
	}

	
	
	function loadModifyTeamPage(id){
		$.initWinOnTopFromLeft('修改班级', '${pageContext.request.contextPath}/teach/team/modifyTeam?id='+id, '600', '500');
	}

	function deleteTeam(id) {
		$.confirm("删除班级将会删除班级下的学生，确定执行此次删除操作？", function() {
			executeDel(id);
		});
	}

	function executeDel(id) {
		var loader = new loadDialog();
        loader.show();
		$.post("${pageContext.request.contextPath}/teach/team/delete", {"id" : id}, function(data, status) {
			loader.close();
			if("success" == status) {
				if("success" == data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" == data) {
					$.error("删除失败，系统异常", 1);
				} else if("noDelete" == data){
					$.error("班级里面有学生，不允许删 除");
				}
			}
		});
	}
</script>
</body>
</html>
