<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${schDesList}" var="schDes" varStatus="a">
	<tr><td style="width:10%"><i class="ck" data-ownerid="${schDes.ownerId}"></i></td><td style="width:10%">${a.index+1 }</td><td style="width:20%">${schDes.province }</td><td style="width:20%">${schDes.city }</td><td>${schDes.schoolName }</td></tr>
</c:forEach>
<%-- <tr>
	<td style="padding:0;border:0 none; display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> --%>

<script>


</script>