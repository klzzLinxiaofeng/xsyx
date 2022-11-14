<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
.zi-list .time{
	height:35px;
}
</style>

<%-- <input type="text" id="attendanceDay" value="${attendanceDay}" /> --%>
<div class="zi-wrap">
	<ul id="dutyList">
	<c:forEach items="${items}" var="item">
		<li>
			<div class="zi-list">
				<div class="clearfix time">
					<span class="left titlea"><i class="fa fa-calendar-o"></i><em>
					<%-- <c:choose>
						<c:when test="${teamDutyUser.status==0 }">今天</c:when>
						<c:when test="${teamDutyUser.status==1 }">明天</c:when>
						<c:otherwise>${teamDutyUser.dayOfWeek }</c:otherwise>
					</c:choose> --%>
					</em><span id="span1" style="margin-left: 10px">
					<%-- <c:if test="${teamDutyUser.status!=null }">${teamDutyUser.dayOfWeek }</c:if> --%>
					<fmt:formatDate  pattern="yyyy年MM月dd日" value='${item.attendanceDay}' />
						</span></span>
					<%-- <c:if test="${not empty teamDutyUser.tduList}">
						<button id="button1" class="right btn btn-primary xm-btn"
							type="button" date_time="<fmt:formatDate  pattern="yyyy-MM-dd" value='${teamDutyUser.dutyDate }' />" onclick="loadEditPage(this);">编辑</button>
					</c:if> --%>
				</div>
				<c:if test="${not empty item.lateList}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">迟到名单：</span>
						<c:forEach items="${item.lateList}" var="vo">
							${vo.name}、
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${not empty item.outEarlyList}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">早退名单：</span>
						<c:forEach items="${item.outEarlyList}" var="vo">
							${vo.name}、
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${not empty item.absentList}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">缺勤名单：</span>
						<c:forEach items="${item.absentList}" var="vo">
							${vo.name}、
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${not empty item.leaveList}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">请假名单：</span>
						<c:forEach items="${item.leaveList}" var="vo">
							${vo.name}<c:if test="${not empty vo.remarks}">(${vo.remarks})</c:if>、
						</c:forEach>
					</div>
				</c:if>

				<%-- <c:if test="${empty teamDutyUser.tduList }">
					<div class="x-head" id="div1">
						<a href="javascript:void(0);" class="left add-btn" date_time="<fmt:formatDate  pattern="yyyy-MM-dd" value='${teamDutyUser.dutyDate }' />"
							onclick="loadCreatePage(this);">+</a>
					</div>
				</c:if>
				<c:if test="${not empty teamDutyUser.tduList}">
					<c:forEach items="${teamDutyUser.tduList }" var="td">
						<div class="x-head" data-student-id="${td.userId }">
							<span class="left img">
								<img src="${td.imgUrl}"/>
							</span>
							<i class="name">${td.studentName }</i>
						</div>
					</c:forEach>
				</c:if> --%>
			</div>
		</li>
	</c:forEach>
</ul>	
	
</div>

<script type="text/javascript">
$(function(){
	$("#lastDay").val("${lastDay}");
});
</script>
