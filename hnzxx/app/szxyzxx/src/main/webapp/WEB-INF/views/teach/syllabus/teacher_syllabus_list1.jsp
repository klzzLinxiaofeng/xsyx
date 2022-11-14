<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
<%@include file="/views/embedded/taglib.jsp" %>
<table id="data-table" class="responsive table  table-bordered table-striped">
	<thead>
		<tr>
			<th></th>
			<th data-xqdm="1">一</th>
			<th data-xqdm="2">二</th>
			<th data-xqdm="3">三</th>
			<th data-xqdm="4">四</th>
			<th data-xqdm="5">五</th>
			<th data-xqdm="6">六</th>
			<th data-xqdm="0">日</th>
		</tr>
	</thead>
	<tbody>
<!-- 		<tr> -->
<!-- 			<td colspan="8"> -->
<!-- 				上午 -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td data-kj="1">第1节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="2">第2节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="3">第3节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="4">第4节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td colspan="8"> -->
<!-- 				下午 -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td data-kj="5">第5节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="6">第6节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="7">第7节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="8">第8节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td colspan="8"> -->
<!-- 				晚上 -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td data-kj="9">第9节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
		<tr>
			<td data-kj="10">第10节</td>
			<td data-xq="1">
			</td>
			<td data-xq="2">
			</td>
			<td data-xq="3">
			</td>
			<td data-xq="4">
			</td>
			<td data-xq="5">
			</td>
			<td data-xq="6">
			</td>
			<td data-xq="0">
			</td>
		</tr>
	</tbody>
</table>

<script type="text/javascript">
	$(function() {
		var $requestData = {};
		$requestData.teachId = "${param.teachId}";
		$requestData.termCode = "${param.termCode}";
		$.get("${ctp}/teach/syllabus/teacher/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$.each(data, function(index, value) {
					var kj = value.lesson;
					var xq = value.dayOfWeek;
					var kbDm = value.lessonId;
					var kbCell = $("#data-table tbody tr").find("td[data-kj='" + kj + "']").parent().find("td[data-xq='" + xq + "']");
					var jiaosMc = value.teacherName;
					jiaosMc = jiaosMc != null ? jiaosMc : "";
					kbCell.append("<p class='kc_mc'>" + value.subjectName + "</p>" +
// 									"<p class='rk_js'>" + jiaosMc + "</p>" + 
									"<p class='bj_mc'>" + value.teamName + "</p>" 
// 									+ "<p class='lessonTime'>" + getTime(value.lessonTimes, kj) + "</p>"
									);
				});
			}
		});
	});
	
	
	function getTime(lessonTimes, lesson) {
		if(lessonTimes != "" && lessonTimes != "undefind") {
			lessonTimes = eval("(" + lessonTimes + ")");
			var lessonTimeStr = "";
			$.each(lessonTimes, function(index, value) {
				if(parseInt(value.lesson) == parseInt(lesson)) {
					lessonTimeStr = value.startTime + "~" + value.endTime; 
					return false
				}
			});
			return lessonTimeStr;
		}
		return "";
		
	}
</script>
