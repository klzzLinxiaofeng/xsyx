<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<thead>
<tr>
	<th>考试名称</th>
	<c:forEach items="${subjectList}" var="subject"
		varStatus="statusSubject">
		<th>${subject.name}</th>
	</c:forEach>
	<th>平均分</th>
	<th>总分</th>
<!-- 	<th>班级排名</th> -->
<!-- 	<th>年级排名</th> -->
</tr>
</thead>
<tbody>
	<c:forEach items="${examScoreVoList}" var="exam" varStatus="status">
		<c:set var="flag">0</c:set>
		<c:set var="count">0</c:set>

		<tr>
			<td align="center">${exam.examName}</td>

			<c:forEach items="${exam.studentScoreSort}" var="score"
				varStatus="statusScore">
				<c:set var="subjectnum">0</c:set>
				<c:forEach items="${subjectList}" var="subject"
					varStatus="statusSubject">

					<c:if test="${subjectnum==0}">
						<c:if test="${count>0}">
							<c:set var="count" value="${count-1}"></c:set>
						</c:if>
					</c:if>

					<c:if test="${count==0}">
						<c:set var="flag">0</c:set>
					</c:if>

					<c:choose>
						<c:when test="${score.subjectCode==subject.code}">
							<c:set var="count" value="${statusSubject.index+2}"></c:set>
							<c:set var="flag" value="${flag+1}"></c:set>
							<c:set var="subjectnum" value="${subjectnum+1}"></c:set>
							<td align="center">${score.score}</td>

						</c:when>
						<c:otherwise>
							<c:if test="${flag==0}">
								<td>
									<%--  <input type="text" value="${statusSubject.index}" style="width:20px;"> --%>
								</td>
							</c:if>

						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:forEach>

			<c:if test="${exam.scoreNum+1<=count}">
				<c:forEach begin="${count}" end="${fn:length(subjectList)}">
					<td></td>
				</c:forEach>
			</c:if>
			<c:if test="${exam.scoreNum==0}">
				<c:forEach begin="${count+1}" end="${fn:length(subjectList)}">
					<td></td>
				</c:forEach>
			</c:if>

			<td align="center">${exam.stuAverage}</td>
			<td align="center">${exam.stuTotal}</td>
			<%-- 									<td align="center">${exam.stuTeamRank}</td> --%>
			<%-- 									<td align="center">${exam.stuGradeRank}</td> --%>
		</tr>
	</c:forEach>
</tbody>