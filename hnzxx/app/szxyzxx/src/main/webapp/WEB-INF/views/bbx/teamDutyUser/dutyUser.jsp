<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
.zi-list .time{
	height:35px;
}
</style>
<div class="zi-wrap">
	<ul id="dutyList">
	<c:forEach items="${bbxTeamDutyUser}" var="teamDutyUser">
		<li>
			<div class="zi-list">
				<div class="clearfix time">
					<span class="left titlea"><i class="fa fa-calendar-o"></i><em>
					<c:choose>
						<c:when test="${teamDutyUser.status==0 }">今天</c:when>
						<c:when test="${teamDutyUser.status==1 }">明天</c:when>
						<c:otherwise>${teamDutyUser.dayOfWeek }</c:otherwise>
					</c:choose>
					</em><span id="span1" style="margin-left: 10px">
					<c:if test="${teamDutyUser.status!=null }">${teamDutyUser.dayOfWeek }</c:if>
					<fmt:formatDate  pattern="yyyy年MM月dd日" value='${teamDutyUser.dutyDate }' />
						</span></span>
					<c:if test="${not empty teamDutyUser.tduList}">
						<button id="button1" class="right btn btn-primary xm-btn"
							type="button" date_time="<fmt:formatDate  pattern="yyyy-MM-dd" value='${teamDutyUser.dutyDate }' />" onclick="loadEditPage(this);">编辑</button>
					</c:if>
				</div>
				<c:if test="${empty teamDutyUser.tduList }">
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
				</c:if>
		</li>
	</c:forEach>
</ul>	
	
</div>
