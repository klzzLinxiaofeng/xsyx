<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>查看</title>
</head>
<body >
			
				
				  <div class="content-widgets">
					<div class="widget-container ">
					
					      学年:<span >&nbsp;<input id="schoolYearId" type="hidden" value="${scas.schoolYearId }"/> ${schoolYear.name } </span> &nbsp;
					      
					  <input type="hidden"  id="gradeId" value=" ${scas.gradeId } "/>
					  
					  <span>班级:&nbsp;<input type="hidden" id="teamId" value="${scas.teamNumber }"/>${team.name }</span> &nbsp;
					  
					  <span><input type="hidden" id="studentId" value="${scas.studentId }"/>姓名:&nbsp;${scas.studentName }</span><br/>
					  
					  <c:if test="${attendanceType==0 }">
					  <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    
					  <br/>
					    <table class="responsive table table-striped table-bordered" id="data-table">
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
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca0">
				                        <tr id="${sca0.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca0.id}"/>
                                               	事假
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sca0.beginDate}" ></fmt:formatDate></td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sca0.endDate}" ></fmt:formatDate></td>
				                             <td>${sca0.dayNumber}</td>
				                             <td>${sca0.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca0.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if>
					  </c:if>
					  
					  <c:if test="${attendanceType==1 }">
 					  <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    <table class="responsive table table-striped table-bordered" id="data-table">
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
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca1">
				                        <tr id="${sca1.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca1.id}"/>
                                               	病假
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sca1.beginDate}" ></fmt:formatDate></td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sca1.endDate}" ></fmt:formatDate></td>
				                             <td>${sca1.dayNumber}</td>
				                             <td>${sca1.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca1.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if>    
					  </c:if>
					 <c:if test="${attendanceType==2 }">  
					 <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    <table class="responsive table table-striped table-bordered" id="data-table">
								<thead>
									<tr role="row">
										 <th>考勤类别</th>
										 <th>日期</th>
										 <th>节数</th>
										 <th>备注</th>
										 <th>操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca2">
				                        <tr id="${sca2.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca2.id}"/>
                                               	缺课
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd" value="${sca2.beginDate}" ></fmt:formatDate></td>
				                             <td>${sca2.nodeNumber}</td>
				                             <td>${sca2.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca2.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if>     
					  </c:if>
					  
					  <c:if test="${attendanceType==3 }">
					  <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    <table class="responsive table table-striped table-bordered" id="data-table">
								<thead>
									<tr role="row">
										 <th>考勤类别</th>
										 <th>日期</th>
										 <th>节数</th>
										 <th>备注</th>
										 <th>操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca3">
				                        <tr id="${sca3.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca3.id}"/>
                                               	旷课
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd" value="${sca3.beginDate}" ></fmt:formatDate></td>
				                             <td>${sca3.nodeNumber}</td>
				                             <td>${sca3.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca3.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if> 
					  </c:if>
					  
					  <c:if test="${attendanceType==4 }">
					  <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    <table class="responsive table table-striped table-bordered" id="data-table">
								<thead>
									<tr role="row">
										 <th>考勤类别</th>
										 <th>日期</th>
										 <th>次数</th>
										 <th>备注</th>
										 <th>操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca4">
				                        <tr id="${sca4.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca4.id}"/>
                                               	迟到
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd" value="${sca4.beginDate}" ></fmt:formatDate></td>
				                             <td>${sca4.orderNumber}</td>
				                             <td>${sca4.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca4.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if> 
					  </c:if>
					  
					  <c:if test="${attendanceType==5 }">
					  <c:if test="${studentCheckAttendanceList.size()!=0 }">
					    <table class="responsive table table-striped table-bordered" id="data-table">
								<thead>
									<tr role="row">
										 <th>考勤类别</th>
										 <th>日期</th>
										 <th>次数</th>
										 <th>备注</th>
										 <th>操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
					   				 <c:forEach items="${studentCheckAttendanceList }" var="sca5">
				                        <tr id="${sca5.id}_tr">
				                            <td>
				                                <input type="hidden" value="${sca5.id}"/>
                                               	早退
				                            </td>
				                             <td><fmt:formatDate pattern="yyyy-MM-dd" value="${sca5.beginDate}" ></fmt:formatDate></td>
				                             <td>${sca5.orderNumber}</td>
				                             <td>${sca5.remark}</td>
				                            <td>
												<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${sca5.id}');">删除</button> 
				                            </td>
				                        </tr>
								    </c:forEach>
								</tbody>
							</table>
					  </c:if> 
					  </c:if>
					      
					</div>
					</div>
			
		
		
</body>
<script type="text/javascript">


//  加载编辑对话框
function loadEditPage(id) {
	$.initWinOnCurFromLeft('编辑', '${pageContext.request.contextPath}/teach/studentCheckAttendance/editor?id=' + id, '600', '250');
}

//	删除对话框
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







</script>
</html>