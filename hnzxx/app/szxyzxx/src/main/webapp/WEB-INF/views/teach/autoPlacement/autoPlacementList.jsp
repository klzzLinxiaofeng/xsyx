<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="自动分班" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="zdfb">
				<div class="z_top">
					<p>未分班学生</p>
					<p>人数：${noClassStuNum }</p>
				</div>
				<div class="z_bottom">
					<input type="hidden" name="noClassStuNum" id="noClassStuNum" value="${noClassStuNum}"/>
					<input type="hidden" name="manNum" id="manNum" value="${manNum}"/>
					<input type="hidden" name="womNum" id="womNum" value="${womNum}"/>
					<input type="hidden" name="manPercentNot" id="manPercentNot" value="${manPercentNot}"/>
					<input type="hidden" name="wonPercentNot" id="wonPercentNot" value="${wonPercentNot}"/>
					
					<table class="table table-bordered"  style="max-height:460px;overflow:auto;display:block">
						<thead>
							<tr>
								<th colspan="2">男生比例</th>
								<th colspan="2">女生比例</th>
							</tr>
							<tr style="background-color:#fff">
								<th colspan="2"><span class="nan">男</span>：${manNum }人 <span class="nan">比例</span>：${manPercent }</th>
								<th colspan="2"><span class="nv">女</span>： ${womNum }人 <span class="nv">比例</span>：${wonPercent }</th>
							</tr>
							<tr>
								<th style="width:226px;">序号</th>
								<th style="width:250px;">姓名</th>
								<th style="width:226px;">性别</th>
								<th style="width:250px;">学籍号</th>
							</tr>
						</thead>
						<thead>
							<tr class="none">
								<th style="width:226px;"></th>
								<th style="width:251px;"></th>
								<th style="width:227px;"></th>
								<th style="width:251px;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${studentList}" var="student" varStatus="s">
								<input type="hidden" name="studentId" id="studentId" value="${student.id }"/>
								<tr>
									<td>${s.index+1}</td>
									<td>${student.name }</td>
									<c:if test="${student.sex==1 }">
										<td class="nan">男</td>
									</c:if>
									<c:if test="${student.sex==2 }">
										<td class="nv">女</td>
									</c:if>
									<c:if test="${student.sex==9 }">
										<td class="nv">未说明</td>
									</c:if>
									<c:if test="${student.sex==0 }">
										<td class="nv">未知</td>
									</c:if>
									<c:if test="${empty student.sex }">
										<td class="nv">无</td>
									</c:if>
									<td>${student.studentNumber}</td>
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</div>
				<div class="z_btn">
					<a href="javascript:void(0)" onclick="setPlacement();">自动分班</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".fenban_left .bottom .bj_rs .ban_ul li a").click(function(){
			$(".fenban_left .bottom .bj_rs .ban_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		$(".fenban_left .bottom").on("click",".open .school",function(event){
			event.stopPropagation();
			$(this).parent().next().hide();
			$(this).parent().removeClass("open").addClass("close_1")
		});
		$(".fenban_left .bottom").on("click",".close_1 .school",function(event){
			event.stopPropagation();
			$(this).parent().next().show();
			$(this).parent().removeClass("close_1").addClass("open")
		});
		var wid=$(".fenban_right").css("width");
		var w=(parseInt(wid)-140)*0.5;
		$(".fenban_right .xs_div").css("width",w);
	});
	
	function setPlacement(){
		var noClassStuNum = $("#noClassStuNum").val();
		var manNum = $("#manNum").val();
		var womNum = $("#womNum").val();
		var wonPercentNot = $("#wonPercentNot").val();
		var manPercentNot = $("#manPercentNot").val();
		if(noClassStuNum==0 || noClassStuNum=="0"){
			$.alert("没有学生！无法分班！");
			return;
		}
		$.initWinOnTopFromLeft('设置分班条件', '${pageContext.request.contextPath}/teach/autoPlacement/setPlacementPage?noClassStuNum='+noClassStuNum+'&manNum='+manNum+'&womNum='+womNum+'&wonPercentNot='+wonPercentNot+'&manPercentNot='+manPercentNot, '750', '350');
	}
	</script>
</body>
</html>
