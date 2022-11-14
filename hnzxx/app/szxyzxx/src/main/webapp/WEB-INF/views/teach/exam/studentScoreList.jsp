<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<table class="responsive table table-bordered" id="tableId">
	<thead>
		<tr>
			<th>序号（班内学号）</th>
			<th>姓名</th>
			<th>最终成绩</th>
			<th>考试性质</th>
		</tr>
	</thead>
	<tbody id="tBodyId">
		<c:forEach items="${items}" var="item" varStatus="i">
			<tr data-id="${item.studentId}">
				<td>${i.index+1}</td>
				<td>${item.name}</td>
				<td><input name="score" class="score" value="${item.score}" Onkeydown="nextFour(this)"/></td>
				<td>
					<span>
						<input type="radio" name="testType_${item.studentId}" value="01" <c:if test="${item.testType==01}">checked="checked"</c:if> />正常考试
					</span>
					<span>
						<input type="radio" name="testType_${item.studentId}" value="03" <c:if test="${item.testType==03}">checked="checked"</c:if> />缺考
					</span>
					<span>
						<input type="radio" name="testType_${item.studentId}" value="11" <c:if test="${item.testType==11}">checked="checked"</c:if> />补考
					</span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" id="examId" value="${examId}"/>

<!-- 用于校验当前输入的分数不能大于满分分数   2016-2-16-->
<input type="hidden" id="fullScore" value="${examStat.fullScore}"/>
<div class="d2" id="d2mess" style="display: none;">本科还没有录入成绩记录</div>
<div class="d3">
	<button id="inputScore" class="btn btn-success" onclick="inputScore()">开始录入</button>
	<button id="cleanScore" class="btn btn-danger" onclick="cleanScore()">清空学生成绩</button>
	<button id="deleteScore" class="btn btn-warning" onclick="deleteScore()">删除本科成绩记录</button>
</div>
<script type="text/javascript">
	$(function () {
		showOrHideButton();
	});
	
	//用于按钮的显示和隐藏
	function showOrHideButton(){
		var isShow = ${items != null && items.size() >0 && items != ''};
		if(isShow){
			$("#tableId").show();
			$("#inputScore").hide();
			$("#cleanScore").show();
			$("#deleteScore").show();
			$("#d2mess").hide();
		}else{
			$("#tableId").hide();
			$("#inputScore").show();
			$("#cleanScore").hide();
			$("#deleteScore").hide();
			$("#d2mess").show();
		}
	}
</script>
