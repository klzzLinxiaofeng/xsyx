<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<div class="select_top" style="margin-top:0">
	<div class="s2">
		活动次数：<span class="a1">${total}</span> 次 
	</div>
</div>
<div class="biao-ul">
		<ul id="ul">
		<c:choose>
		<c:when test="${activityListVo.size()>0 }">
		<c:forEach items="${activityListVo}" var="activity">
		<li id="${activity.id}_li">
				<div class="item">
					<div class="item-left">
						<span class="sign ban-acitve">班级活动</span>
					</div>
					<div class="photo">
						<img src="${activity.activityImage}"/>
					</div>
					<div class="item-right">
						<h4><strong>${activity.name }</strong></h4>
						<p class="texta">活动时间：<fmt:formatDate value="${activity.startTime}" pattern="yyyy年MM月dd日 HH:mm" /> - <fmt:formatDate value="${activity.finishTime}" pattern="yyyy年MM月dd日 HH:mm" /></p>
						<p class="texta" style="z-index: 999;position: relative;">活动地点：${activity.place }</p>
						<p class="texta">注意事项：${activity.comment }</p>
					</div>
				</div>
				<div class="item-bottom"><span>${activity.visualTime }</span>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<a href="javascript:void(0)"  onclick="deleteObj(this, '${activity.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
				</c:if>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<a href="javascript:void(0)" onclick="loadEditPage('${activity.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
				</c:if>
				</div>
				<c:if test="${activity.activityState==0 }"><em  class="label label-star"></em></c:if>
				<c:if test="${activity.activityState==1 }"><em  class="label label-ing"></em></c:if>
				<c:if test="${activity.activityState==2 }"><em  class="label label-end"></em></c:if>
			</li>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<li>
			 <div class="empty">
				<p class="empty_text">搜索结果为空</p>
		     </div>
		  	 </li>
		</c:otherwise>
		</c:choose>
		</ul>
	</div>