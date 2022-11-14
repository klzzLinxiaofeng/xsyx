<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>

<c:forEach items="${resourceList}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${(page.currentPage-1)*page.pageSize+status.index+1}</td>
				<%-- <td>${item.thumbnailUrl}</td> --%>
				<td>${item.title}</td>
				<td>
				<c:choose>
					<c:when test="${item.gradeName==''}">
						高中
					</c:when>
					<c:otherwise>
					${item.gradeName}
					</c:otherwise>
				</c:choose>
				
				
				</td>
				<td>${item.subjectName}</td>
				
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
				
				<td>
				<c:choose>
			  	 <c:when test="${item.resType == 1}">
			  	 微课
				 </c:when>
				 <c:when test="${item.resType == 11}">
			  	 书写型微课
				 </c:when>
				<c:when test="${item.resType == 2}">
				课件
				 </c:when>
				 
				 <c:when test="${item.resType == 3}">
				 作业
				 </c:when>
				 <c:when test="${item.resType == 4}">
				 试卷
				 </c:when>
				 <c:when test="${item.resType == 5}">
				 教案
				 </c:when>
				 <c:when test="${item.resType == 6}">
				 素材
				 </c:when>
				  <c:otherwise>
				       其他
				 </c:otherwise>
				 </c:choose>
				
				</td>
				
				
				<td> ${item.userName}</td>
				<td>
				<c:choose>
			  	 <c:when test="${item.verify == 6}">
			  		<span  style="color: green">待审核</span>
				 </c:when>
				<c:when test="${item.verify == 2}">
			  		<span   style="color: blue">审核通过</span>
				 </c:when>
				 
				 <c:when test="${item.verify == 5}">
			  		<span   style="color: red">审核拒绝</span>
				 </c:when>
				  <c:otherwise>
				   <span   style="color: red">其他</span>
				 </c:otherwise>
			 </c:choose>
				
				</td>
			
		<td class="caozuo">
			
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			
			
			
			 	
			
			
			<c:choose>
			  	 <c:when test="${item.verify == 6}">
			  	 
			  	     
			  		 <button class="btn btn-green" type="button" onclick="deleteObj('${item.id}','0');">通过</button>			
					 <button class="btn btn-red" type="button" onclick="deleteObj('${item.id}','5');">否决</button>
			  		
				 </c:when>
				
				  <c:otherwise>
				  
				  	 <!-- <button class="btn btn-gray" type="button" >通过</button>			
					 <button class="btn btn-gray" type="button" >否决</button> -->
				 </c:otherwise>
			 </c:choose>
			
			<a href="${pageContext.request.contextPath}/resource/viewer/${item.id}" class="btn btn-green">
			 	  查看
			 	</a>
			</c:if>
			
		</td>
	</tr>
</c:forEach>
