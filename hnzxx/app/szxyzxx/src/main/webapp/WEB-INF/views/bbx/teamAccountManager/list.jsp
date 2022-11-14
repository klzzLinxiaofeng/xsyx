<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:choose>
  <c:when test="${bbxSchoolList.size()>0 }">
    <c:forEach items="${bbxSchoolList}" var="bbxschool" varStatus="i">
	<tr id="${bbxschool.school.id }_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${bbxschool.school.name}</td>
		<td>${bbxschool.schoolType }</td>
		<td>${bbxschool.teamCount }</td>
		<td>${bbxschool.school.runningType != null && school.runningType != "" ? jcgcFn:getColValue("JY-BXLB", bbxschool.school.runningType, "name") : "无"}</td>
		<td>${bbxschool.school.telephone }</td>
		<td>${bbxschool.school.address }</td>
		<td class="caozuo"><button class="btn btn-warning" onclick="enter('${bbxschool.school.id}')">进入</button></td>
	</tr>
  </c:forEach>	
  </c:when>
  <c:otherwise>
    <tr><td colspan="8">暂无相关学校数据!</td></tr>
  </c:otherwise>
</c:choose>
 