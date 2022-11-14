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
			<jsp:param value="人员薪资" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							人员薪资列表
							<p class="btn_link" style="float: right;">
								<a style="margin-right:118px;position: relative;" href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<!-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
								<a href="javascript:void(0)" class="a2" onclick="downLoadSalary();"><i class="fa fa-plus"></i>导出薪资信息</a>
								</c:if> -->
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
<!-- 									<a href="javascript:void(0)" class="a2" -->
<!-- 										id="downloadExcel" onclick="downloadExcel();"><i class="fa fa-plus"></i>导出薪资信息</a> -->
									<a href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增薪资信息</a>
								</c:if> 
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${ctp}/personnel/teacherSalary/downLoadSalary" method="post" style="position:relative;">
						<button type="submit" class="btn btn-info  a2 fa fa-plus" style="  position:absolute;right:96px;top:-56px;border-radius:0; background-color:#EFAD4D; height:30px;l">&nbsp;&nbsp;导出薪资信息 </button>
							<div class="select_b">
								<div class="select_div">
									<span>姓名：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="" />
								</div>
								<div class="select_div">
									<span>工号：</span>
									<input type="text" id="jobNumber" name="jobNumber" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="" />
								</div>
								<div class="select_div">
									<span>身份证号：</span>
									<input type="text" id="idCardNumber" name="idCardNumber" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="" />
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							</form>
							
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>工号</th>
											<th>姓名</th>
											<th>身份证号</th>
											<th>部门</th>
											<th>职位</th>
											
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="teacherSalary_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="teacherSalary_list_content" />
								<jsp:param name="url" value="/personnel/teacherSalary/index?sub=list&dm=${param.dm}" />
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
	function search() {
		var val = {};
		var name = $("#name").val();
		var jobNumber = $("#jobNumber").val();
		var idCardNumber = $("#idCardNumber").val();
		if(name != null && name != "") {
			val.name = name;
		}
		if(jobNumber != null && jobNumber != "") {
			val.jobNumber = jobNumber;
		}
		if(idCardNumber != null && idCardNumber != "") {
			val.idCardNumber = idCardNumber;
		}
		var id = "teacherSalary_list_content";
		var url = "/personnel/teacherSalary/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/personnel/teacherSalary/creator', '700', '400');
	}
	
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/personnel/teacherSalary/editor?id=' + id, '700', '400');
	}
	
	//  加载教师薪资列表对话框
	function loadCheckPage(id) {
		$.initWinOnTopFromLeft('教师薪资', '${ctp}/personnel/teacherSalary/check?teacherId=' + id, '900', '600');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('教师薪资', '${ctp}/personnel/teacherSalary/viewer?teacherId=' + id, '900', '600');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/personnel/teacherSalary/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	//创建弹出框的筛选教师控件的回调函数
	function selectedHandler(data) {
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names[0]);
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids[0]);
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
		$.success("设置成功");
		$.closeWindowByNameOnParent(data.windowName);
	}
	
	//导出薪资信息
	function downloadExcel() {
		var name = $("#name").val();
		var jobNumber = $("#jobNumber").val();
		var idCardNumber = $("#idCardNumber").val();
		var url = "${ctp}/personnel/teacherSalary/downLoadSalary?";
		var param = "name=" + name + "&jobNumber=" + jobNumber + "&idCardNumber=" + idCardNumber;
		url = url + param;
		url = encodeURI(url);
		$("#downloadExcel").attr("href", url);
	}
	
	$(function(){
		if($(".a4").length==0){
			$(".a2").css("right","-19px");
			$(".a3").css("top","2px");
		}
	})
</script>
</html>