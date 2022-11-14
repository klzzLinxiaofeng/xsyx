<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td style="display: none;">${item.id}</td>
				<td style="display: none;">${item.schoolId}</td>
				<td>${item.name}</td>
				<td>${item.code}</td>
				<td>${item.floorName}</td>
				<td>${item.capacity}</td>
				<td>${item.capacityTeam}</td>
				<td>${item.square}</td>
				<td>${item.courseName}</td>
				<td>
					<c:if test="${item.status == true}">正常</c:if>
					<c:if test="${item.status == false}">故障</c:if>
				</td>
				<td>${item.troubleCause}</td>
				<td>
					<c:if test="${item.isNotice == true }">是</c:if>
				</td>
	    		<td><fmt:formatDate value="${item.preRepairTime}" pattern="yyyy/MM/dd" /></td>
	    		<td onclick="getEquipmentsCountVo(${item.id})"><a href="javascript:void(0)" style="">室内设备</a></td>
	    		<td style="display: none;"></td>
	    		<td style="display: none;"><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
	    		<td style="display: none;"><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	function getEquipmentsCountVo (roomId) {
		$.initWinOnTopFromLeft('实验室设备', '${ctp}/schoolaffair/equipment/getEquipmentsCountVo?laboratoryRoomId=' + roomId, '320', '350');
	}
	
</script>
