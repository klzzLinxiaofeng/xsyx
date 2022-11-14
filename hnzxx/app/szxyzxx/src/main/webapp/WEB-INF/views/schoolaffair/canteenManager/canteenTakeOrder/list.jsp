<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${canteenTakeOrderVoList}" var="canteenTakeOrderVo" varStatus="i">
	<tr id="${canteenTakeOrderVo.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>  
				<td>${canteenTakeOrderVo.canteenName}</td>
				<td>${canteenTakeOrderVo.goodsName}</td>
				<td>${canteenTakeOrderVo.storeNum}</td>
				<td>${canteenTakeOrderVo.takeCount}</td>
				<td>${canteenTakeOrderVo.remainNum}</td>
	    		<td><fmt:formatDate value="${canteenTakeOrderVo.takeDate}" pattern="yyyy-MM-dd" /></td>
	    		<td><fmt:formatDate value="${canteenTakeOrderVo.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${canteenTakeOrderVo.signPerson}</td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${canteenTakeOrderVo.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
