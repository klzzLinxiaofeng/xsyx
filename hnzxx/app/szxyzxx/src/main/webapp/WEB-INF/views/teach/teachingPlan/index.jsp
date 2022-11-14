<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教学计划" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教学计划列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加教学计划</a>
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
										<select id="schoolYear"></select>
									</div>
									<div class="select_div">
										<span>学期：</span>
										<select id="termCode"></select>
									</div>
									<div class="select_div">
										<span>年级：</span>
										<select id="gradeCode">
											<option value="">请选择</option>
											<c:forEach items="${gradeList}" var="grade">
												<option value="${grade.uniGradeCode}">${grade.name}</option>
											</c:forEach>
										</select>
									</div>
									<div class="select_div">
										<span>科目：</span>
										<select id="subjectCode"></select>
									</div>
									<c:if test="${type=='gl'}">
										<div class="select_div">
											<span>教师：</span>
											<select id="teacherId">
												<option value="">请选择</option>
												<c:forEach items="${teacherList}" var="teacher">
													<option value="${teacher.id}">${teacher.name}</option>
												</c:forEach>
											</select>
										</div>
									</c:if>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>年级</th>
											<th>学科</th>
											<c:if test="${type=='gl'}">
												<th>教師</th>
											</c:if>
											<th>上传日期</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="lessonPlan_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="lessonPlan_list_content" />
								<jsp:param name="url" value="/generalTeachingAffair/pjteachingplan/index?sub=list&dm=${param.dm}&type=${type}" />
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
	$(function(){
		dateChangeInit();
		removeRep();
		$("#gradeCode").chosen();
		$("#teacherId").chosen();
	});
	
	function search() {
		var val = {
				"schoolYear" : $("#schoolYear").val(),
				"termCode" : $("#termCode").val(),
				"gradeCode" : $("#gradeCode").val(),
				"subjectCode" : $("#subjectCode").val()
		};
		if("${type}"=="gl"){
			val.teacherId = $("#teacherId").val()
		}
		var id = "lessonPlan_list_content";
		var url = "/generalTeachingAffair/pjteachingplan/index?sub=list&dm=${param.dm}&type=${type}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/generalTeachingAffair/pjteachingplan/creator', '700', '500');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/generalTeachingAffair/pjteachingplan/editor?id=' + id, '700', '500');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/generalTeachingAffair/pjteachingplan/viewer?id=' + id, '700', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/generalTeachingAffair/pjteachingplan/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	//学年下拉选
	$.SchoolYearSelector({
		"selector" : "#schoolYear",
		"condition" : {},
		"afterHandler" : function(selector) {
			var schoolYear = $("#schoolYear").val();
			getTermBySchoolYear(schoolYear);
			search();
	}
	});
	
	//去重
	function removeRep(){
		 $("#gradeCode option").each(function() {
            var val = $(this).val();
            if ( $("#gradeCode option[value='" + val + "']").length > 1 )
                $("#gradeCode option[value='" + val + "']:gt(0)").remove();
	    });
	}
	
	//科目下拉选
	$.PjSubjectSelector({
		"selector" : "#subjectCode"
	});
	
	//触发下拉框事件   根据学年改变获取学期
	function dateChangeInit(){
		$("#schoolYear").change("on",function(){
			var currentValue = $(this).val();
			if(currentValue == ""){
				$("#termCode").html("").append("<option value=''>请选择</option>");
				$("#termCode").trigger("liszt:updated"); 
				$("#termCode").chosen();
			}else{
				getTermBySchoolYear(currentValue);
			}
		});
	}
	
	//根据学年获取学期
	function getTermBySchoolYear(schoolYear){
		$("#termCode").html("");
		var htmlSelect = "<option value=''>请选择</option>";
		var url = "${ctp}/generalTeachingAffair/lessonplan/getTermBySchoolYear?schoolYear="+schoolYear;
		$.post(url,null,function(data){
			for(var flag in data){
				htmlSelect += "<option value='" + data[flag].code + "'>"+ data[flag].name +"</option>"
			}
			$("#termCode").append(htmlSelect);
			$("#termCode").trigger("liszt:updated");
			$("#termCode").chosen();
		},'json')
	}

</script>
</html>