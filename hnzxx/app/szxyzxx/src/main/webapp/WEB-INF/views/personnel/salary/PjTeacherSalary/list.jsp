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
				<%-- <td>${item.schoolId}</td>
				<td>${item.departmentId}</td>
				<td>${item.teacherId}</td>
				<td>${item.userId}</td> --%>
				<td>${item.name}</td>
				<td>${item.payYear}</td>
				<td>${item.payMonth}</td>
				<td>${item.salaryTotal}</td>
				<%-- <td>${item.payYear}</td>
				<td>${item.payMonth}</td>
				<td>${item.salaryTotal}</td> 
				<c:if test="${item.s1} != null"><td>${item.s1}</td></c:if> --%>
				<c:forEach items="${item.valueList}" var="fieldValue">
					<td>${fieldValue.value}</td>
				</c:forEach>
				<%--<td>${item.s1}</td>
				<td>${item.s2}</td>
				<td>${item.s3}</td>
				<td>${item.s4}</td>
				<td>${item.s5}</td>
				<td>${item.s6}</td>
				<td>${item.s7}</td>
				<td>${item.s8}</td>
				<td>${item.s9}</td>
				<td>${item.s10}</td>
				<td>${item.s11}</td>
				<td>${item.s12}</td>
				<td>${item.s13}</td>
				<td>${item.s14}</td>
				<td>${item.s15}</td>
				<td>${item.s16}</td>
				<td>${item.s17}</td>
				<td>${item.s18}</td>
				<td>${item.s19}</td>
				<td>${item.s20}</td>
	    		 <td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
	    		<td><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
				<td>${item.isDeleted}</td> --%>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if> --%>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if> 
		</td>
	</tr>
</c:forEach>
