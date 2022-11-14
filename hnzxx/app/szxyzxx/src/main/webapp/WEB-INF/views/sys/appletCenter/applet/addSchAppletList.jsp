<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${appletOwnerList }" var ="owner" varStatus="status">
	<tr data-id="${owner.id}"><td style="width: 22%;"><i class="ck"></i></td><td style="width: 22%;">${status.index + 1}</td><td>${owner.schoolName}</td></tr>
</c:forEach>
<tr>
	<td style="padding:0;border:0 none; display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>

<script>

</script>