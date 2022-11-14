<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%-- <tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> --%>
 <c:forEach items="${parentList}" var="all"> 
<tr>
	<td width="15%"  style="vertical-align:top;vertical-align:text-top;padding-top:18px;">${all.teamName}:</td>
	<td width="85%">
		<div class="cont_tabby pull-left" id="name_cont_tabby">
			<ul >
				<c:forEach items="${all.parInfList}" var="item"> 
					<li>
						<a href="#" onclick="selectName(this)" draggable="false" id="${item.parUserName}">${item.parName}(${item.stuName})</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</td>
</tr>
 </c:forEach>
 