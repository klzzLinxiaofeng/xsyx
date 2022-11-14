<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
<table id="data-table" class="responsive table  table-bordered table-striped">
	<c:choose>
		<c:when test="${xqs == null}">
			<thead>
				<tr>
					<th></th>
					<th>一</th>
					<th>二</th>
					<th>三</th>
					<th>四</th>
					<th>五</th>
					<th>六</th>
					<th>日</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>第1节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第2节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第3节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第4节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第5节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第6节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>第7节</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</c:when>
		<c:when test="${xqs != null}">
			<thead>
				<tr>
					<th></th>
					<c:forEach items="${xqs}" var="xq">
						<th data-xqdm="${xq}"><jcgc:cache tableCode="XY-JY-XINGQI"
								value="${xq}"></jcgc:cache></th>
					</c:forEach>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%-- 		<c:if test="${fn:length(morningLessons) > 0}"> --%>
				<!-- 			<tr> -->
				<%-- 				<td colspan="${fn:length(xqs) + 2}"> --%>
				<!-- 					上午 -->
				<!-- 				</td> -->
				<!-- 			</tr> -->
				<%-- 		</c:if> --%>

				<!-- 上午 -->
				<c:forEach items="${morningLessons}" var="morning"
					varStatus="morningStatus">
					<tr>
						<td data-kj="${morning}">第${morningStatus.count}节</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"></td>
						</c:forEach>
						<td></td>
					</tr>
				</c:forEach>

				<%-- 		<c:if test="${fn:length(afternoonLessons) > 0}"> --%>
				<!-- 			<tr> -->
				<%-- 				<td colspan="${fn:length(xqs) + 2}"> --%>
				<!-- 					下午 -->
				<!-- 				</td> -->
				<!-- 			</tr> -->
				<%-- 		</c:if> --%>

				<c:forEach items="${afternoonLessons}" var="afternoon"
					varStatus="status">
					<tr>
						<td data-kj="${afternoon}">第${afternoon - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))}节
						</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"></td>
						</c:forEach>
						<td></td>
					</tr>
				</c:forEach>

				<%-- 		<c:if test="${fn:length(eveningLessons) > 0}"> --%>
				<!-- 			<tr> -->
				<%-- 				<td colspan="${fn:length(xqs) + 2}"> --%>
				<!-- 					晚上 -->
				<!-- 				</td> -->
				<!-- 			</tr> -->
				<%-- 		</c:if> --%>

				<c:forEach items="${eveningLessons}" var="evening"
					varStatus="status">
					<tr>
						<td data-kj="${evening}">第${evening - (sca:getAfternoonMaxLessonCount() - fn:length(afternoonLessons)) - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))}节
						</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"></td>
						</c:forEach>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</c:when>
	</c:choose>
</table>
<script type="text/javascript">
	$(function() {
		var lessonTimes = "${item.lessonTimes}";
		if(lessonTimes != "" && lessonTimes !="undefind") {
			lessonTimes = eval("(" + lessonTimes + ")");
			$.each(lessonTimes, function(index, value) {
				var kj = parseInt(value.lesson);
				$("#data-table tbody tr td[data-kj='" + kj + "']").nextAll("td:last").append("<span>" + value.startTime + " ~ " + value.endTime + "</span>")
			});
		}
		
		var $requestData = {};
		$requestData.syllabusId = "${item.id}";
		$.get("${ctp}/teach/syllabus/rkap/list/json2", $requestData, function(data, status) {
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
									"<p class='rk_js'>" + jiaosMc + "</p>");
				});
			}
		});
	});
</script>
