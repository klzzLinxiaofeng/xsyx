<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>新生报名列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="新生报名" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							新生报名列表
							<p class="btn_link" style="float: right;">
<!-- 								<a href="javascript:void(0)" onclick="uploadStudentInfo();" class="a3"><i class="fa  fa-undo"></i>导入学生信息</a> -->
<!-- 								<a href="javascript:void(0)" onclick="uploadStudentInfo();" class="a3"><i class="fa  fa-undo"></i>导入学生信息</a> -->
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a2" onclick="loadCreateNewStudentPage();"><i class="fa fa-plus"></i>新增学生信息</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">

								<div class="select_div">
									<span>姓名：</span><input type="text" id="name" name="name" data-id-container="name" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<div class="select_div">
									<span>学年：</span>
									<select id="schoolYear"></select>
								</div>
								<div class="select_div">
									<span>学生类型：</span>
									<select id="studentType"></select>
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
		                           <th>序号</th>
		                           <th>学年</th>
		                           <th>姓名</th>
		                           <th>性别</th>
		                           <th>民族</th>
		                           <th>证件号码</th>
		                           <th>招生人</th>
		                           <th>注册状态</th>
		                           <th>学生类别</th>
		                           <th>操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url"  value="/entrollStudent/newStudent/getNewStudentList?sub=list" />
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
	$(function () {
		//学生类别
		$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "", function() {
			
		});
		//学年
		$.SchoolYearSelector({
			"selector" : "#schoolYear",
			"condition" : {},
			"selectedVal" : "",
			"afterHandler" : function(selector) {
				
			}
		});
	});
	
	function search() {
		var val = {};
		
		val = {
				"name" : $("#name").val(),
				"schoolYear" : $("#schoolYear").val(),
				"studentType" : $("#studentType").val()
		}
		var id = "module_list_content";
		var url = "/entrollStudent/newStudent/getNewStudentList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	function getGrade(gradeId){
		$("#gradeId").val(gradeId);
	}

	
	function loadCreateNewStudentPage() {
		$.initWinOnTopFromTop("新增新生注册信息", "${pageContext.request.contextPath}/entrollStudent/newStudent/addNewStudentPage", '700', '600');
	}
	
	function loadModifyStudentPage(id){
		$.initWinOnTopFromTop("修改新生注册信息", "${pageContext.request.contextPath}/entrollStudent/newStudent/modifyNewStudentPage?id="+id, '700', '600');
	}
	
	function loadViewNewStudentRegistPage(id){
		$.initWinOnTopFromTop("查看新生注册信息", "${pageContext.request.contextPath}/entrollStudent/newStudentRegist/viewNewStudentRegistPage?id="+id, '700', '600');
	}
	
	function deleteNewStudent(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/entrollStudent/newStudent/deleteNewStudent?id=" + id, {"_method" : "delete"}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>