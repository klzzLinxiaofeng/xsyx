<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.table tbody td p{
	margin:3px 0;
}
.kb_cell{
	position:relative;
	padding-bottom:10px;
	margin-bottom:5px;
}
.kb_cell:hover{
	border:1px solid #00859D;
	background-color:#eee;
}
</style>
<table id="data-table" class="responsive table  table-bordered table-striped">
	<thead>
		<tr>
			<th style="width: 9%">课节\星期</th>
			<c:forEach items="${xqs}" var="xq">
				<th data-xqdm="${xq}">
					<jcgc:cache tableCode="XY-JY-XINGQI" value="${xq}"></jcgc:cache>
				</th>
			</c:forEach>
			<th style="display: none;">
<!-- 				<input type="button" value="重新设置课表结构" onclick='$("#setting_input_pane").show("slow")'> -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(morningLessons) > 0}">
			<tr>
				<td colspan="${fn:length(xqs) + 2}">
					上午
				</td>
			</tr>
		</c:if>
	
		<!-- 上午 -->
		<c:forEach items="${morningLessons}" var="morning" varStatus="morningStatus">
			<tr>
				<td data-kj="${morning}">${morningStatus.count}</td>
				<c:forEach items="${xqs}" var="xq">
				<td data-xq="${xq}">
					<span class="add" style="display :none;"><i class="icon-edit icon-1x"></i>选择课程类型</span>
				</td>
			</c:forEach>
				<td style="display: none;"></td>			
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(afternoonLessons) > 0}">
			<tr>
				<td colspan="${fn:length(xqs) + 2}">
					下午
				</td>
			</tr>
		</c:if>
		
		<c:forEach items="${afternoonLessons}" var="afternoon" varStatus="status">
			<tr>
				<td data-kj="${afternoon}">
<%-- 					${status.count} --%>
					${afternoon}
					<%-- ${afternoon - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))} --%>
				</td>
				<c:forEach items="${xqs}" var="xq">
					<td data-xq="${xq}">
						<span class="add" style="display :none;"><i class="icon-edit icon-1x"></i>选择课程类型</span>
					</td>
				</c:forEach>
				<td style="display: none;"></td>			
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(eveningLessons) > 0}">
			<tr>
				<td colspan="${fn:length(xqs) + 2}">
					晚上
				</td>
			</tr>
		</c:if>
			
		<c:forEach items="${eveningLessons}" var="evening" varStatus="status">
			<tr>
				<td data-kj="${evening}">
					${evening}
<%-- 					${status.count} --%>
					<%-- ${evening - (sca:getAfternoonMaxLessonCount() - fn:length(afternoonLessons)) - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))}
				</td> --%>
				<c:forEach items="${xqs}" var="xq">
					<td data-xq="${xq}">
						<span class="add" style="display :none;"><i class="icon-edit icon-1x"></i>选择课程类型</span>
					</td>
				</c:forEach>
				<td style="display: none;"></td>			
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- <div class="reset"><a href="javascript:void(0)" onclick='$("#setting_input_pane").show("slow")'><i class="fa fa-undo"></i>重新设置课表结构</a></div> -->
<script type="text/javascript">
	$(function() {
		var loader = new loadLayer("加载课表中");
		loader.show();
		/* var lessonTimes = "${item.lessonTimes}";
		if(lessonTimes != "" && lessonTimes !="undefind") {
			lessonTimes = eval("(" + lessonTimes + ")");
			$.each(lessonTimes, function(index, value) {
				var kj = parseInt(value.lesson);
				$("#data-table tbody tr td[data-kj='" + kj + "']").nextAll("td:last").append("<span>" + value.startTime + " ~ " + value.endTime + "</span>")
			});
		} */
		
		var $requestData = {};
		$requestData.gradeSyllabusId = "${item.id}";
		$.get("${ctp}/bbx/bwGradeLesson/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$.each(data, function(index, value) {
					var kj = value.lesson;
					var xq = value.dayOfWeek;
					var kbDm = value.id;
					var type;
					if ( value.type === 0 ) {
						type = "不上课";
					} else if (value.type === 1) {
						type = "行政课";
					} else if (value.type === 2) {
						type = "走班课";
					}
					var kbCell = $("#data-table tbody tr").find("td[data-kj='" + kj + "']").parent().find("td[data-xq='" + xq + "']");
					// kbCell.find(".add").remove();
					kbCell.find("span").remove();
					kbCell.append(
							"<div data-kbDm='" + kbDm + "' class='kb_cell'>" + 
							"<p class='kc_mc'>" + 
							type + "</p>" +
							"<span class='delete' style='display :none;'><i class='icon-trash icon-1x'></i> 清除</span>" + 
							"<span class='edit' style='display :none;'><i class='icon-edit icon-1x'></i> 编辑</span></div>"	
					);
				/* 	kbCell.find(".add").before("<div data-kbDm='" + kbDm + "' class='kb_cell'>" + 
							"<p class='kc_mc'>" + 
							type + "</p>" +
							"<span class='delete' style='display :none;'><i class='icon-trash icon-1x'></i> 清除</span>" + 
							"<span class='edit' style='display :none;'><i class='icon-edit icon-1x'></i> 编辑</span></div>"); */
				});
			}
			loader.close();
		});
	});
</script>
