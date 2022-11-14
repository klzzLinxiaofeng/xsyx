<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
	<tr>
		<td style="padding:0;border:0 none;">
			<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
			<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
			<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
		</td>
	</tr>
</table>
<input type="hidden" value="${type}" id="type"/>
<div class="clsq">
	<ul>
		<c:forEach items="${items}" var="item">
			<li>
				<div class="touxiang"> 
					<img src="<avatar:avatar userId='${item.createuserId}' ></avatar:avatar>"></img>
				</div>
				<div class="caozuo_2 <c:if test='${item.fanwei==1 || item.fanwei==2}'>one</c:if><c:if test='${item.fanwei==0}'>all</c:if>">
				</div>
				<div class="detail_2">
					<div class="p1"><span>${item.createname}</span>组织的会议</div>
					<div class="p7"><a href="javascript:void(0)" onclick="showMeetingContent('${item.id}','${item.isCheck}');">${item.meetingName}</a></div>
					<div class="p5">
						<i class="fa fa-clock-o"></i>
						<p class="p_div">会议时间</p>
						<span>
							${item.starttime}
							-
							${item.endtime}
						</span>
						<b>
							<c:if test="${item.meetingStatus=='notStarted'}">
								<span>${item.day}</span>天<span>${item.hour}</span>小时后开始
							</c:if>
							<c:if test="${item.meetingStatus=='underway'}">
								进行中
							</c:if>
							<c:if test="${item.meetingStatus=='complete'}">
								已结束
							</c:if>
						</b>
						<i class="fa  fa-map-marker"></i>
						<p class="p_div">会议地点</p>
						<span>${item.address} </span>
					</div>
					<div class="p5"><i class="fa fa-user"></i>
					<p class="p_div">主持人</p>
					<span>${item.createname}</span></div>
					<div class="p5"><i class="fa fa-users"></i>
					<p class="p_div">参与人</p>
					<span>
						<c:if test='${item.fanwei==2}'>
							<c:forEach items="${item.meetingUser}" var="user" varStatus="status">
								<c:if test="${!status.last}">${user.userName}、</c:if>
								<c:if test="${status.last}">${user.userName}</c:if>
							</c:forEach>
						</c:if>
						<c:if test='${item.fanwei==1}'>
							<c:forEach items="${item.meetingUser}" var="user" varStatus="status">
								<c:if test="${!status.last}">${user.departmentName}、</c:if>
								<c:if test="${status.last}">${user.departmentName}</c:if>
							</c:forEach>
						</c:if>
						<c:if test='${item.fanwei==0}'>
							所有人
						</c:if>
					</span>
					</div>
					<div class="p6">
					<i class="fa fa-paperclip"></i>
					<p>附件</p>
						<c:if test="${item.uploadFile!=null && item.uploadFile!=''}">
						<a href='<entity:getHttpUrl uuid="${item.uploadFile}"/>'>
							${item.fileName}
						</a>
						</c:if>
						<c:if test="${item.uploadFile==null || item.uploadFile==''}">
							无
						</c:if>
					</div>
					<div class="p4">${item.createname} &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate pattern="yyyy年MM月dd日 HH:ss" value="${item.createDate}"></fmt:formatDate></div>
				</div>
				<div class="caozuo_1" style="top: 46px;right: 29px;">
					<c:if test="${cuurenLogin==item.createuserId}">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<c:if test="${item.isCanModify == 'yes'}">
								<a class="edit" href="javascript:void(0)" onclick="editMeeting('${item.id}');"><i class="fa fa-edit"></i>编辑</a>
							</c:if>
						</c:if>
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete" href="javascript:void(0)" onclick="deleteObj(this,'${item.id}')">删除</a>
						</c:if>
					</c:if>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
	</ul>
	<c:if test="${items.size()<=0}">
		<div class="empty"></div>
	</c:if>
</div>