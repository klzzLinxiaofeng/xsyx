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
			<jsp:param value="薪资管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							薪资列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>年份：</span>
									<select id="payYear" name="payYear">
											<option value="">---不 限---</option>
										<c:forEach items="${yearList }" var="year">
											<option value="${year }">${year }</option>											
										</c:forEach>
										
									</select>
									<!-- <input type="text" id="payYear" name="payYear" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
								</div>
								<div class="select_div">
									<span>月份：</span>
									<select id="payMonth" name="payMonth">
										<option value="">---不 限---</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
									<!-- <input type="text" id="payMonth" name="payMonth" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
								</div>
								<div class="select_div">
									<span>部门：</span>
									<select id="departmentId" name="departmentId">
											<option value="">---不 限---</option>
										<c:forEach items="${departmentMap}" var="department">
											<option value="${department.key}">${department.value}</option>
										</c:forEach>
									</select>
									<!-- <input type="text" id="departmentId" name="departmentId" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="display: none;">id</th>
											<!-- <th>所在学校id</th>
											<th>部门id</th>
											<th>教师id</th>
											<th>用户id</th> -->
											<c:if test="${salaryFieldList != null}">
												<td>教师姓名</td>
												<td>年份</td>
												<td>月份</td>
												<td>实发工资总额</td>
											</c:if>
											<!-- <th>工资发放年份</th>
											<th>工资发放月份</th>
											<th>工资总额</th> -->
								<c:forEach items="${salaryFieldList}" var="field">
									<%-- <tr id="${item.id}_tr"> --%>
											<td>${field.attrName}</td>
											
									<!-- </tr> -->		
								</c:forEach>			
											<!-- <th>工资明细项01</th>
											<th>工资明细项02</th>
											<th>工资明细项03</th>
											<th>工资明细项04</th>
											<th>工资明细项05</th>
											<th>工资明细项06</th>
											<th>工资明细项07</th>
											<th>工资明细项08</th>
											<th>工资明细项09</th>
											<th>工资明细项10</th>
											<th>工资明细项11</th>
											<th>工资明细项12</th>
											<th>工资明细项13</th>
											<th>工资明细项14</th>
											<th>工资明细项15</th>
											<th>工资明细项16</th>
											<th>工资明细项17</th>
											<th>工资明细项18</th>
											<th>工资明细项19</th>
											<th>工资明细项20</th>
											<th>记录创建时间</th>
											<th>记录修改时间</th>
											<th>记录是否删除(默认false)</th> -->
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="pjTeacherSalary_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="pjTeacherSalary_list_content" />
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
		var payYear = $("#payYear").val();
		var payMonth = $("#payMonth").val();
		var departmentId = $("#departmentId").val();
		if (payYear != null && payYear != "") {
			val.payYear = payYear;
		}
		if (payMonth != null && payMonth != "") {
			val.payMonth = payMonth;
		}
		if (departmentId != null && departmentId != "") {
			val.departmentId = departmentId;
		}
		var id = "pjTeacherSalary_list_content";
		var url = "/personnel/teacherSalary/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/personnel/teacherSalary/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/personnel/teacherSalary/editor?id=' + id, '700', '800');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/personnel/teacherSalary/viewer?id=' + id, '700', '300');
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
</script>
</html>