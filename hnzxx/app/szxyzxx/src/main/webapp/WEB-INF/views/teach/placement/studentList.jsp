<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学生列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a2" onclick="placementStudentPage();"><i class="fa fa-plus"></i>学生分班</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div"><span>学年：</span>
									<select style="margin-bottom: 0; padding: 6px; width: 120px;" id="schoolYear" name="schoolYear">
										<c:forEach items="${schoolYearList}" var="schoolYear">
					                   		<option value="${schoolYear.year }">${schoolYear.name }</option>
					                   	</c:forEach>
									</select>
								</div>
								<div class="select_div">
									<span>账号：</span><input type="text" id="name" name="name" data-id-container="name" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
								   <th><input type="checkbox" id="checkAll"></th>
								   <th>用户帐号</th>
		                           <th>用户名称</th>
		                           <th>姓名</th>
		                           <th>性别</th>
		                           <th>手机号码</th>
		                           <th>所在班级</th>
		                           <th>在读状态</th>
		                           <th>学生类别</th>
		                           <th>职务</th>
		                           <th>操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url"  value="/teach/placement/studentList?sub=list" />
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
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "module_list_content";
		var url = "/teach/placement/studentList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	$("#checkAll").on("click", function() {
		$("#module_list_content input:checkbox[name='checkList']").prop("checked", this.checked);
	});
	
	function placementStudentPage() {
		var chk_value =[]; 
		$("#module_list_content input:checkbox[name='checkList']:checked").each(function(){ 
			chk_value.push($(this).val()); 
		});
		var studentId = chk_value;
		$.initWinOnTopFromLeft("学生分班信息", "${pageContext.request.contextPath}/teach/placement/placementStudentPage?studentId="+studentId, '600', '460');
	}
	
</script>
</html>