<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
</head>
<body>
	<div class="points-content table-spacing" style="display: block;">
		<p>
			${msg }
<!-- 			2015-2016年下学期 三年级2班 <span>第1周</span> -->
		</p>
		<div class="points limit-cancel" style="position: relative;">
			<table
				class="responsive table table-striped reflective-evaluate optimization"
				id="data-table">
				<thead>
					<tr role="row">
						<th>星期</th>
						<th>学号</th>
						<th>姓名</th>
						<th>节次</th>
						<th class="bad">不良行为类型</th>
<!-- 						<th>科目</th> -->
						<th>任课老师</th>
						<th>修改</th>
					</tr>
				</thead>
				<tbody id="module_list_content">
					<c:forEach items="${scoreData}" var="score">
						<tr class="">
							<input type="hidden" value="${score.checkDate}">
							<td><fmt:formatDate value="${score.checkDate}" pattern="E" /></td>
							<td>${score.number}</td>
							<td>${score.name}</td>
							<td>${score.checkRange}</td>
							<td><span>${score.badBehaviours}</span></td>
<!-- 							<td>科目</td> -->
							<td>${score.courseTeacher}</td>
							<td>
							<button  type="button" class="btn btn-blue" style="z-index: -1;">重新编辑</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		$("#module_list_content").children("tr").each(function(index,item){
			var year = $("#xn").val();
			var termCode = $("#xq").val();
			var gradeId = null;
			var teamId;
			if($("#sel_div").find("#nj").length>0){
				teamId = $("#bj").val();
				gradeId = $("#nj").val();
			}else{
				teamId = $("#teamId").val();
			}
			
			var date = $(this).children("input").val();
			var checkDate = new Date(date).Format("yyyy-MM-dd");

			var cr = $(this).children("td").eq(3).text();
			var checkRange = cr.substring(cr.indexOf("第")+1, cr.indexOf("节")); 
			
			var badBehaviour = $(this).children("td").eq(4).text();
			badBehaviour = badBehaviour.substring(0,8).trim();
			
			$(this).find("button").each(function(){
 				$(this).click(function(){
					
					toEvaPage(year,termCode,gradeId,teamId,checkDate,checkRange,badBehaviour);
					
 				});
			});
		});	
	</script>
	
</body>
</html>