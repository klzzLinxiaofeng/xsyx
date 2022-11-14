<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
	<tr>
		<td style="padding: 0; border: 0 none;"><input type="hidden"
			id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" />
			<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
	</tr>
</table>
<input type="hidden" id="type" value="${type }"/>
<ul>
	<c:forEach items="${puLists1 }" var="pu1">
		<li><img
			src="<avatar:avatar userId='${pu1.userId}' ></avatar:avatar>"
			style="border-radius: 50%;"><span>${pu1.realName }</span>
			<p>${pu1.telphone }</p></li>

	</c:forEach>
</ul>
<div style="margin-top: 20%;">
			<jsp:include page="./myJqpagination.jsp" flush="true">
				<jsp:param name="id" value="yuedu_list_content" />
				<jsp:param name="url"
					value="/office/ajax/paper/yuedu_index?sub=yuedu_list&dm=${param.dm}&paperId=${paperId}&type=${type }" />
				<jsp:param name="pageSize" value="${page.pageSize }" />
			</jsp:include>
			<div class="clear"></div>
		</div>