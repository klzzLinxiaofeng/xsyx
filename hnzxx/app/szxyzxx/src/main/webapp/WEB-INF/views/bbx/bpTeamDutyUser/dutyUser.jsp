<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
.zi-list .time{
	height:35px;
}
.news_tip{width: 60px; height: 60px; border-radius: 60px; overflow: hidden;display:bolck;;}
.news_boxdiv{width:60px; float: left;padding:15px;}
.name{ text-align:center;display: block; line-height:25px; font-size:14px;}
</style>
<div class="zi-wrap">
	<ul id="dutyList">

	
	
		<c:forEach items="${bbxTeamDutyUser}" var="teamDutyUser">
			<li>
				<div class="zi-list">
					<div class="clearfix time" style="position: relative;">
						<span class="left titlea"><i class="fa fa-calendar-o"></i>
						<em>
							<c:choose>
								<c:when test="${teamDutyUser.dayOfWeek == '0'}">星期日</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '1'}">星期一</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '2'}">星期二</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '3'}">星期三</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '4'}">星期四</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '5'}">星期五</c:when>
								<c:when test="${teamDutyUser.dayOfWeek == '6'}">星期六</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</em>	
						
						<c:if test="${not empty teamDutyUser.tduList}">
							<button id="button1" class="right btn btn-primary xm-btn" style="position: absolute;right: 22px;top: 10px;"
								type="button" date_time="${teamDutyUser.dayOfWeek}" onclick="loadEditPage(this);">编辑</button>		
						</c:if>											
					</div>
					
					<c:choose>
						<c:when test="${empty teamDutyUser.tduList }">
							<div class="x-head" id="div1">
								<a href="javascript:void(0);" class="left add-btn" date_time="${teamDutyUser.dayOfWeek}" 
									onclick="loadCreatePage(this);">+</a>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${teamDutyUser.tduList }" var="td">
								<div class="news_boxdiv" data-student-id="${td.userId }">
									<span class="left img news_tip" style="">
										<%-- <img src="${td.imgUrl}"/> --%>
										<c:choose>
											<c:when test="${empty td.imgUrl}">
												<img src="${ctp}/res/images/no_pic.jpg"/> 
											</c:when>
											<c:otherwise>
												<img src="${td.imgUrl}"/> 
											</c:otherwise>
										</c:choose>
									</span>
									<span class="name">${td.studentName }</span>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>										
			</li>
			<%-- <li>
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
								type="button" date_time="<fmt:formatDate  pattern="yyyy-MM-dd" value='${teamDutyUser.dutyDate }' />"
								onclick="loadEditPage(this);">编辑</button>
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
				</div>
			</li> --%>
		</c:forEach> 
	
	
	
	
	<%-- <c:forEach items="${bbxTeamDutyUser}" var="teamDutyUser">
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
	</c:forEach> --%>
	</ul>	
	
</div>
