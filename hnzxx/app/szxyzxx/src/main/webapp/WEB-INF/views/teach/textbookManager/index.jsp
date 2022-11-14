<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教材管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教材管理列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建教材</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
								<span>学年：</span>
								<select id="xn" name="schoolYear"  disabled="disabled"  class="chzn-select" style="width:200px;" onchange="onChangeSchoolYear();">
									<option value="">请选择</option>
								</select>
							</div>
							<div class="select_div">
								<span>
									学期：</span>
								
								<select id="termCode" name="termCode"  class="chzn-select" style="width:160px;">
									
								</select>
							</div>
							<div class="select_div">
								<span>
								     使用年级：</span>
								
								<select id="nj" name="gradeId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getTeamSelect();">
									<option value="">请选择</option>
								</select>
							</div>
							<div class="select_div">
								<span>
									 使用班级：</span>
								
								<select id="bj" name="teamId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getNumFromTeam();">
									<option value="">请选择</option>
									 
								</select>
								
							</div>
							
								<!-- <div class="select_div">
									<span>角色名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div> -->
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											
											<th>任课教师</th>
											<th>教材年级</th>
											<th>科目</th>
											<th>册次</th>
											<th>版本</th>
											<th>名称</th>
											<th>书籍类型</th>
											<th>国际标准书号</th>
											<th>价格</th>
											<th>使用年级</th>
											<th>使用班级</th>
											<th>数量</th>
						
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="textbookManager_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="textbookManager_list_content" />
								<jsp:param name="url" value="/teach/textbookManager/index?sub=list&dm=${param.dm}" />
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

	$(function() {
		$.initCascadeSelector({
			"type":"team",
			"teamCallback" : function($this){
				onChangeSchoolYear();
				
				search();
			}
	  });
	});

	function search() {
		var val = {};
		var gradeId = $("#nj").val();
		var teamId = $("#bj").val();
		
		
		var schoolYear = $("#xn").val();
		var termCode = $("#termCode").val();
		
		if (schoolYear != null && schoolYear != "") {
			val.schoolYear = schoolYear;
		}else{
			val.schoolYear = '${sessionScope[sca:currentUserKey()].schoolYear}';
		}
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		
		
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		
		
		if (termCode != null && termCode != "") {
			val.termCode = termCode;
		}else{
			val.termCode = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
		}
		
		var id = "textbookManager_list_content";
		var url = "/teach/textbookManager/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
		
	}
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/teach/textbookManager/creator', '700', '500');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/teach/textbookManager/editor?id=' + id, '700', '500');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teach/textbookManager/viewer?id=' + id, '700', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/textbookManager/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	 function onChangeSchoolYear(){
		   var xn = $("#xn").val();
			if(xn == null || xn == ""){
				xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
			 }
			
		  $.getSchoolTerm({"schoolYear" : xn}, function(data, status) {
				var $xq = $("#termCode");
				if(data.length == 0){
					$xq.html("");
					$xq.append("<option value=''>请选择 </option>");
				}else{
					$xq.html("");
					var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
					$.each(data, function(index, value) {
						if(termCurrent == value.code ){
							$xq.append("<option  selected='selected' value='" + value.code + "'>" + value.name + "</option>");
						}else{
							$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
						}
						
					});
				}
				
			});
		  
		
	  }
	

</script>
</html>