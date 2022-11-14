<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
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
.table tbody td{text-align:center}
</style>
<table id="data-table" class="responsive table  table-bordered table-striped">
	<thead>
		<tr>
			<th style="width: 9%">课节\星期</th>
			<th data-xqdm="1">星期一</th>
			<th data-xqdm="2">星期二</th>
			<th data-xqdm="3">星期三</th>
			<th data-xqdm="4">星期四</th>
			<th data-xqdm="5">星期五</th>
			<th data-xqdm="6">星期六</th>
			<th data-xqdm="0">星期日</th>
		</tr>
	</thead>
	<tbody id="t1">
		<!-- <tr>
			<td colspan="8">
				上午
			</td>
		</tr> -->
		<!-- <tr>
			<td data-kj="1">1</td>
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
			<td data-kj="2">2</td>
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
			<td data-kj="3">3</td>
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
			<td data-kj="4">4</td>
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
			<td colspan="8">
				下午
			</td>
		</tr>
		<tr>
			<td data-kj="5">5</td>
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
			<td data-kj="6">6</td>
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
			<td data-kj="7">7</td>
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
			<td data-kj="8">8</td>
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
			<td colspan="8">
				晚上
			</td>
		</tr>
		<tr>
			<td data-kj="9">9</td>
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
			<td data-kj="10">10</td>
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
			<td data-kj="11">11</td>
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
			<td data-kj="12">12</td>
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
		</tr> -->
	</tbody>
</table>

<script type="text/javascript">
	$(function() {
		var moringMaxLessonCount = parseInt("${sca:getMorningMaxLessonCount()}");
		var afternoonMaxLessonCount = parseInt("${sca:getAfternoonMaxLessonCount()}");
		var eveningMaxLessonCount = parseInt("${sca:getEveningMaxLessonCount()}");
		for(var i=1; i<=moringMaxLessonCount; i++){
			$("#t1").append("<tr><td data-kj=\"" + i + "\">" + i + "</td><td data-xq=\"1\"></td><td data-xq=\"2\"></td><td data-xq=\"3\"></td><td data-xq=\"4\"></td>" + 
				"<td data-xq=\"5\"></td><td data-xq=\"6\"></td><td data-xq=\"0\"></td></tr>");
		}
		
		for(var i=1; i<=afternoonMaxLessonCount; i++){
			var value = moringMaxLessonCount + i;
			$("#t1").append("<tr><td data-kj=\"" + value + "\">" + value + "</td><td data-xq=\"1\"></td><td data-xq=\"2\"></td><td data-xq=\"3\"></td><td data-xq=\"4\"></td>" + 
				"<td data-xq=\"5\"></td><td data-xq=\"6\"></td><td data-xq=\"0\"></td></tr>");
		}
		
		for(var i=1; i<=eveningMaxLessonCount; i++){
			var value = moringMaxLessonCount + afternoonMaxLessonCount + i;
			$("#t1").append("<tr><td data-kj=\"" + value + "\">" + value + "</td><td data-xq=\"1\"></td><td data-xq=\"2\"></td><td data-xq=\"3\"></td><td data-xq=\"4\"></td>" + 
				"<td data-xq=\"5\"></td><td data-xq=\"6\"></td><td data-xq=\"0\"></td></tr>");
		}
		
		
		var $requestData = {};
		var loader = new loadLayer("加载课表中");
		loader.show();
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
					kbCell.text("");
					kbCell.append("<div data-kbDm='" + kbDm + "' class='kb_cell'>" + 
									"<p class='kc_mc'>" + value.subjectName + "</p>" +
// 									"<p class='rk_js'>" + jiaosMc + "</p>" + 
									"<p class='bj_mc'>" + value.teamName + "</p>" +
									"<p class='lessonTime'>" + getTime(value.lessonTimes, kj) + "</p>" +
									"<span class='delete' style='display :none;'><i class='icon-trash icon-1x'></i> 清除</span>" + 
									"<span class='edit' style='display :none;'><i class='icon-edit icon-1x'></i> 编辑</span></div>");
				});
			}
			loader.close();
		});
	});
	
	
	function getTime(lessonTimes, lesson) {
		if(lessonTimes != "" && lessonTimes != "undefind" && lessonTimes != null) {
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
