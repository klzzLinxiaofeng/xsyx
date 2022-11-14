<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>详情</title>
</head>
<body  >
					<form class="form-horizontal" id="educeAttendance_form"
						action="javascript:void(0);">

						<div class="content-widgets">
							<div class="widget-container " >
							     <input id="id" type="hidden" value="${scas.id }"/>
								学年:<span>&nbsp;<input id="schoolYearId" type="hidden"
									value="${scas.schoolYearId }" /> ${schoolYear.name }
								   </span> &nbsp; <input type="hidden" id="gradeId"
									value=" ${scas.gradeId } " /> <span>班级:&nbsp;<input
									type="hidden" id="teamId" value="${scas.teamNumber }" />${team.name }</span>
								&nbsp; <span><input type="hidden" id="studentId"
									value="${scas.studentId }" />姓名:&nbsp;${scas.studentName }</span><br />

								<c:if test="${studentCheckAttendanceList0.size()!=0 }">

									<br />
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">事假</font></span>
									<table  class=" responsive table table-striped table-bordered" style="text-align: right;"
										 id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th>开始时间</th>
												<th>结束时间</th>
												<th>天数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList0 }" var="sca0">
												<tr id="${sca0.id}_tr">
													<td><input type="hidden" value="${sca0.id}" /> 事假</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd "
															value="${sca0.beginDate}"></fmt:formatDate></td>
													<td><fmt:formatDate pattern="yyyy-MM-dd "
															value="${sca0.endDate}"></fmt:formatDate></td>
													<td>${sca0.dayNumber}</td>
													<td>${sca0.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca0.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${studentCheckAttendanceList1.size()!=0 }">
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">病假</font></span>
									<table class="responsive table table-striped table-bordered"
										id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th>开始时间</th>
												<th>结束时间</th>
												<th>天数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList1 }" var="sca1">
												<tr id="${sca1.id}_tr">
													<td><input type="hidden" value="${sca1.id}" /> 病假</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd "
															value="${sca1.beginDate}"></fmt:formatDate></td>
													<td><fmt:formatDate pattern="yyyy-MM-dd "
															value="${sca1.endDate}"></fmt:formatDate></td>
													<td>${sca1.dayNumber}</td>
													<td>${sca1.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca1.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${studentCheckAttendanceList2.size()!=0 }">
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">缺课</font></span>
									<table class="responsive table table-striped table-bordered"
										id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th style="width:43%">日期</th>
												<th>节数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList2 }" var="sca2">
												<tr id="${sca2.id}_tr">
													<td><input type="hidden" value="${sca2.id}" /> 缺课</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd"
															value="${sca2.beginDate}"></fmt:formatDate></td>
													<td>${sca2.nodeNumber}</td>
													<td>${sca2.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca2.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${studentCheckAttendanceList3.size()!=0 }">
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">旷课</font></span>
									<table class="responsive table table-striped table-bordered"
										id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th style="width:43%">日期</th>
												<th>节数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList3 }" var="sca3">
												<tr id="${sca3.id}_tr">
													<td><input type="hidden" value="${sca3.id}" /> 旷课</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd"
															value="${sca3.beginDate}"></fmt:formatDate></td>
													<td>${sca3.nodeNumber}</td>
													<td>${sca3.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca3.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${studentCheckAttendanceList4.size()!=0 }">
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">迟到</font></span>
									<table class="responsive table table-striped table-bordered"
										id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th style="width:43%">日期</th>
												<th>次数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList4 }" var="sca4">
												<tr id="${sca4.id}_tr">
													<td><input type="hidden" value="${sca4.id}" /> 迟到</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd"
															value="${sca4.beginDate}"></fmt:formatDate></td>
													<td>${sca4.orderNumber}</td>
													<td>${sca4.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca4.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${studentCheckAttendanceList5.size()!=0 }">
									<span style="margin: 10px;"><font
										style="line-height: 2.5;">早退</font></span>
									<table class="responsive table table-striped table-bordered"
										id="data-table">
										<thead>
											<tr role="row">
												<th>考勤类别</th>
												<th style="width:43%">日期</th>
												<th>次数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											<c:forEach items="${studentCheckAttendanceList5 }" var="sca5">
												<tr id="${sca5.id}_tr">
													<td><input type="hidden" value="${sca5.id}" /> 早退</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd"
															value="${sca5.beginDate}"></fmt:formatDate></td>
													<td>${sca5.orderNumber}</td>
													<td>${sca5.remark}</td>
													<td>
														<button class="btn btn-blue" type="button"
															onclick="deleteObj(this, '${sca5.id}');">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>

                         <div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button"
									onclick="educe()">导出</button>
						</div>

					</form>

</body>

<script type="text/javascript">
//删除对话框
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
		parent.window.search();
	});
	
}


//导出对话框
function educe(){
	var id = $("#id").val();
	window.location.href="${pageContext.request.contextPath}/teach/studentCheckAttendance/checkOutOne?id="+id ;
}


</script>


 