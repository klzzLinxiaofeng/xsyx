<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
<tr>
	<td style="padding: 0; border: 0 none;"><input type="hidden"
		id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
		type="hidden" id="totalPages" name="totalPages"
		value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
</tr>
</table>
	<input type="hidden" value="${tatol}" id="tatol"/>
	<ul id="ult">
<c:forEach items="${items}" var="item">
		<li id="${item.id}_li">
			<div class="touxiang">
				<c:if test="${item.cover==null || item.cover==''}">
					<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
				</c:if>
				<c:if test="${item.cover!=null && item.cover!=''}">
<%-- 					<img src="${item.cover}"> --%>
					<img src="<entity:getHttpUrl uuid='${item.cover}'/>">
				</c:if>
			</div>
			<div class="detail">
				<div class="p2">${item.cardName}</div>
				<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>${item.fullLoad}人</span></div>
				<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span>${item.plateNumber}</span></div>
				<div class="p3"><i class="fa   fa-key"></i><p class="p_div">购置日期</p><span><fmt:formatDate pattern="yyyy年MM月dd日" value="${item.purchaseData}"></fmt:formatDate></span></div>
				<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="keyong">
					<c:if test="${item.serviceCondition==0}">可用</c:if>
					<c:if test="${item.serviceCondition==1}">使用中</c:if>
					<c:if test="${item.serviceCondition==2}">维修中</c:if>
				</span>
					<button class="btn_1 btn-keyong" onclick="changeStatus('0','${item.id}')" <c:if test="${item.serviceCondition==0}">disabled="disabled"</c:if>>设为可用</button>
					<button class="btn_1 btn-using" onclick="changeStatus('1','${item.id}')"<c:if test="${item.serviceCondition==1 || item.serviceCondition==2}">disabled="disabled"</c:if>>使用中</button>
					<button class="btn_1 btn-xiuli" onclick="changeStatus('2','${item.id}')"<c:if test="${item.serviceCondition==2}">disabled="disabled"</c:if>>维修中</button>
				</div>
			</div>
			<div class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<a class="edit" href="javascript:void(0)" onclick="loadEditPage('${item.id}')"><i class="fa fa-edit"></i>编辑</a>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<a class="delete" href="javascript:void(0)" onclick="deleteObj(this,${item.id})">删除</a>
			</c:if>
			</div>
			<div class="clear"></div>
		</li>
</c:forEach>
	</ul>
	<div class="empty" style="display:none">
		<p>车辆为空</p>
	</div>
