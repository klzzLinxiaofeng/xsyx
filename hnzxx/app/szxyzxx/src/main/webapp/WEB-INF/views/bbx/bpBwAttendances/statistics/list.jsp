<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
.zi-list .time{
	height:35px;
}
.class{    height: 40px;
font-size:16px;
    line-height: 40px;
    padding-left: 35px;
    font-weight: bold;}
    .display-icon{
    display: inline-block;
    color: #7d7d7d;
    font: 400 39px/54px "Microsoft YaHei";
    top:0;
    width:40px;
    text-align: center;
    right:20px;
    line-height:35px;
        cursor: pointer;
        position: absolute;
    border-radius: 50px;}
    #staGradeAttendanceList li{    position: relative;}
</style>

<div class="zi-wrap">
		<ul id="staGradeAttendanceList">
		<c:forEach items="${staGradeAttendanceList}" var="staGradeAttendance">
		<li>
		 <div class="class" >
			<span>${staGradeAttendance.gradeName}</span>
			<span style="padding-left: 20px">迟到人数：${staGradeAttendance.lateNum}人，</span>
			<span>早退人数：${staGradeAttendance.outEarlyNum}人，</span>
			<span>缺勤人数：${staGradeAttendance.absentNum}人，</span>
			<span>请假人数：${staGradeAttendance.leaveNum}人</span>
			<span class="display-icon" style=''>+</span>
		</div>
			<ul style="background:none; font-size:13px;padding-left:35px; display:none;">
				<c:forEach items="${staGradeAttendance.teamAttendanceList}" var="teamAttendance">
					<li>
						<div class="zi-list">
						<span>${teamAttendance.teamName}</span>
						<span style="padding-left: 20px">迟到人数：${teamAttendance.lateNum}人，</span>
						<span>早退人数：${teamAttendance.outEarlyNum}人，</span>
						<span>缺勤人数：${teamAttendance.absentNum}人，</span>
						<span>请假人数：${teamAttendance.leaveNum}人</span>
						</div>
					</li>
				
				</c:forEach>	
				
			</ul>
			</li>
	</c:forEach>
		</ul>

</div>

<script>
$('ul#staGradeAttendanceList').on('click','.display-icon',function(){
	//console.log("11");
	if($(this).parent().parent().find('ul').is(':hidden')){
		$(this).html('-')
		$(this).parent().parent().find('ul').show(100);
	}else{
		$(this).parent().parent().find('ul').hide(100);
		$(this).html('+')
	}
})
</script>






	
	<%-- <c:forEach items="${items}" var="item">
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
					<fmt:formatDate  pattern="yyyy年MM月dd日" value='${item.attendanceDay}' />
						</span></span>
					<c:if test="${not empty teamDutyUser.tduList}">
						<button id="button1" class="right btn btn-primary xm-btn"
							type="button" date_time="<fmt:formatDate  pattern="yyyy-MM-dd" value='${teamDutyUser.dutyDate }' />" onclick="loadEditPage(this);">编辑</button>
					</c:if>
				</div>
				<c:if test="${not empty item.lateNames}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">迟到名单：</span>${item.lateNames}</div>
				</c:if>
				<c:if test="${not empty item.outEarlyNames}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">早退名单：</span>${item.outEarlyNames}</div>
				</c:if>
				<c:if test="${not empty item.absentNames}">
					<div style="padding-left: 20px;font-size: 15px">
						<span style="font-weight:bold; padding-right: 5px">缺勤名单：</span>${item.absentNames}</div>
				</c:if>

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
			</div>
		</li>
	</c:forEach>
</ul>	 --%>
	


<script type="text/javascript">
</script>
