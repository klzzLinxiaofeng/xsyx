<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet"/>
<title>${sca:getDefaultSchoolName()}</title>
<style>
	.chzn-container{
		/* position:relative;top:12px; */
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生考勤" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生考勤列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" style="position:absolute;right:210px;top:3px;border-radius:0; height:30px;l"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							  <!-- <a class="a1"href="javascript:void(0)" ><i class="fa fa-plus"></i>模板下载</a>
							  <a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>批量导入数据</a> -->
<!-- 							  <a class="a2"href="javascript:void(0)" ><i class="fa fa-plus" onclick="educeAll();"></i>导出数据</a> -->
							  <a class="a4" href="javascript:void(0)" onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加信息</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${pageContext.request.contextPath}/teach/studentCheckAttendance/checkOutAll" method="post" style="position:relative;">						  
					     <button type="submit" class="btn btn-info  a2 fa fa-plus" style="position:absolute;right:80px;top:-58px;border-radius:0; background-color:#EFAD4D; height:30px;l">&nbsp;&nbsp;导出数据 </button>
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span>
									<select id="xn" name="schoolYearId" style="width: 120px;"></select>
									</div>
						        <div class="select_div">
									<span>年级：</span>
									<select id="nj" name="gradeId" style="width: 120px;"></select>
						        </div>
						        <div class="select_div">
									<span>班级：</span>
									<select id="bj" name="teamNumber" style="width: 120px;"></select>
						        </div>
						        <div class="select_div">
									<span>关键字：</span>
									<input type="text" id="stu" name="studentName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="学生姓名" value=""/>
									</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								</div>
								<div class="clear"></div>
							</form>
							<table class="responsive table table-striped" id="data-table">
								 <thead>
									<tr role="row">
											<th>序号</th>
											<th>班级</th>
											<th>姓名</th>
											<th>事假 </th>
											<th>病假 </th>
											<th>缺课 </th>
											<th>旷课 </th>
											<th>迟到 </th>
											<th>早退 </th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="studentCheckAttendance_list_content">
									<jsp:include page="./list.jsp" />
								</tbody> 
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentCheckAttendance_list_content" />
								<jsp:param name="url" value="/teach/studentCheckAttendance/list?sub=list&dm=${param.dm}" />
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
	$.initCascadeSelector({"type" : "team", "teamCallback" : function($this){search()}});
	
});



	function search() {
		
		var val = {
                "schoolYearId":$("#xn").val(),
				"gradeId":$("#nj").val(),
				"teamNumber":$("#bj").val(),
				"studentName":$("#stu").val()
		};
		
		var id = "studentCheckAttendance_list_content";
		var url = "/teach/studentCheckAttendance/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}


//    //导出对话框
//    function educeAll(){
// 		var $requestData = formData2JSONObj("#attendance_form");
// 	   window.location.href="${pageContext.request.contextPath}/teach/studentCheckAttendance/checkOutAll" ; 
//    }
   
   
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('添加信息', '${pageContext.request.contextPath}/teach/studentCheckAttendance/creator', '900', '500');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${pageContext.request.contextPath}/teach/studentCheckAttendance/editor?id=' + id, '900', '250');
	}
	
	//加载详情对话框
	function loadViewerPage(id) {
		var xn = $("#xn").val();
		$.initWinOnCurFromLeft('详情', '${pageContext.request.contextPath}/teach/studentCheckAttendance/viewer?id=' + id+' &xn='+xn, '700', '500');
	}
	

	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/teach/studentCheckAttendance/" + id, {"_method" : "delete"}, function(data, status) {
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
</script>
</html>