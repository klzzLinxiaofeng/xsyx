<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${roles}" var="role">
	<tr id="${role.id}_tr">
		<td>
			${role.code}
		</td>
		<td>
			${role.name}
		</td>
		<td>
			<dou:fieldVal type="group" code="${role.groupId}"></dou:fieldVal>
		</td>
		<td>
			<c:choose>
				<c:when test="${role.defaultRole}">
					是（不可删）
				</c:when>
				<c:otherwise>
					否
				</c:otherwise>
			</c:choose>
		</td>
<!-- 		<td> -->
<!-- 			类型 -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<ca:cache tableName="t_dm_gy_xxjbxx" paramName="xxMc" id="${role.xxDm}" /> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<input type="checkbox" ${role.jyBj == "0" ? 'checked="checked"' : ''} ${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2) == false ? 'disabled="disabled"' : ''} onclick="enableOrDisable(this, '${role.id}')"> --%>
<!-- 		</td> -->
		<td><t:showTime createTime="${role.createDate}" /></td>
		<td class="caozuo">
			<%-- <a href="javascript:accredit('${role.id}');">【授权】</a> --%>
			<shiro:lacksRole name="${sca:getSuperAdminCode()}">
			<button class="btn btn-info" type="button" onclick="accredit('${role.id}', '${role.groupId}');">授权</button>
			</shiro:lacksRole>
			<shiro:hasRole name="${sca:getSuperAdminCode()}">
				<button class="btn btn-info" type="button" onclick="super_accredit('${role.id}', '${role.groupId}');">授权</button>
			</shiro:hasRole>
			
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadEditPage('${role.id}','disable');">详情</button>
				<%-- <a href="javascript:void(0);" onclick="loadEditPage('${role.id}','disable');">【详情】</a> --%>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${role.id}','');">编辑</button>
				<%-- <a href="javascript:void(0);" onclick="loadEditPage('${role.id}','');">【编辑】</a> --%>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<%-- <a href="javascript:deleteObj(this, '${role.id}');">【删除】</a> --%>
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${role.id}', '${!role.defaultRole}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
