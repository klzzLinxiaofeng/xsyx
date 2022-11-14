<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title></title>
</head>
<body  >
	<form class="form-horizontal" id="educeAttendance_form" action="javascript:void(0);">
       <div class="content-widgets">
		<div class="widget-container " >
		 <table  class=" responsive table table-striped table-bordered" style="text-align: right;" id="data-table">
			<thead>
			   <tr role="row">
				 <th>床位号</th>
				 <th>年级</th>
				 <th>班级</th>
				 <th>姓名</th>
				 </tr>  
				 </thead>
			<tbody id="module_list_content">
				<c:forEach items="${dormitoryPersonVo}" var="dps" varStatus="i">
				<tr id="${dps.id}_tr">
					<td>${i.index+1}</td>
					<td>${dps.gradeName}</td>
					<td>${dps.teamName}</td>
					<td>${dps.studentName}</td>							
				</tr>
				</c:forEach>
			</tbody>
		 </table>
	</form>

</body>


 