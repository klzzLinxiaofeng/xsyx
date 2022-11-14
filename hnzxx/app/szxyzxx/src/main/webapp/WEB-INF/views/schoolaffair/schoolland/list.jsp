<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${status.index + 1}</td>
				<td>${item.name}</td>
				<td>${item.address}</td>
				<td>
				<c:choose>
					<c:when test="${schoolLand.landPropertyRight==1 }">
						产权非属学校共同使用
					</c:when>
					<c:when test="${schoolLand.landPropertyRight==2 }">
						学校独立产权
					</c:when>
					<c:otherwise>
						其他
					</c:otherwise>
				</c:choose>
				</td>
				<td>${item.floorArea}</td>
				<td>
				<c:choose>
						<c:when test="${schoolLand.coverUse==1 }">
							房屋用途
						</c:when>
						<c:when test="${schoolLand.coverUse==2 }">
							体育场地
						</c:when>
						<c:when test="${schoolLand.coverUse==3 }">
							绿化用地
						</c:when>
					
						<c:otherwise>
							其他
						</c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${schoolLand.landUseStatus==1 }">
							对外投资
						</c:when>
						<c:when test="${schoolLand.landUseStatus==2 }">
							被借用
						</c:when>
						<c:when test="${schoolLand.landUseStatus==3 }">
							租用
						</c:when>
						<c:when test="${schoolLand.landUseStatus==4 }">
							使用
						</c:when>
						<c:when test="${schoolLand.landUseStatus==5 }">
							担保
						</c:when>
						<c:when test="${schoolLand.landUseStatus==6 }">
							闲置
						</c:when>
						<c:otherwise>
							其他
						</c:otherwise>
					</c:choose>
				</td>
				<td>${item.remark}</td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
