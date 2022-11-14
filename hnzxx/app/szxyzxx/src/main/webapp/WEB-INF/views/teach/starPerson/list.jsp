<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/views/embedded/common.jsp"%>
<div class="points-content table-spacing" style="display: block;">

	<c:if test="${usertype eq 'school'}">
		<p>
			<c:if test="${datetype eq 'month'}">月</c:if>
			发展性评价全校之星
			<c:if test="${flag==0}"><span style="color: red;">(未评定)</span></c:if>
			<c:if test="${flag==1}"><span style="color: green;">(已评定)</span></c:if>
			<c:if test="${flag==2}"><span style="color: red;">(暂无可评定数据)</span></c:if>
		</p>
	</c:if>
	<c:if test="${usertype eq 'grade'}">
		<p>
			<c:if test="${datetype eq 'month'}">月</c:if>
			发展性评价年级之星
			<c:if test="${flag==0}"><span style="color: red;">(未评定)</span></c:if>
			<c:if test="${flag==1}"><span style="color: green;">(已评定)</span></c:if>
			<c:if test="${flag==2}"><span style="color: red;">(暂无可评定数据)</span></c:if>
		</p>
	</c:if>
	<c:if test="${usertype eq 'team'}">
		<p>
			<c:if test="${datetype eq 'month'}">月</c:if>
			发展性评价班级之星
			<c:if test="${flag==0}"><span style="color: red;">(未评定)</span></c:if>
			<c:if test="${flag==1}"><span style="color: green;">(已评定)</span></c:if>
			<c:if test="${flag==2}"><span style="color: red;">(暂无可评定数据)</span></c:if>
		</p>
	</c:if>
    

	<p class="specific-while">${msg}</p>
	<table>
	<tr>
		<td style="padding:0;border:0 none;">
			<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
			<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		</td>
	</tr>
</table>
	<table class="responsive table  reflective-evaluate" id="data-table" style="border: 1px solid #ddd;">

		
		<c:choose>
			<c:when test="${usertype eq 'team'}">
				<thead>
					<tr role="row">
						<th>排名</th>
						<th>姓名</th>
						<th>发展评价卡</th>
						<th>激励评价卡</th>
						<th>总评价卡数</th>
					</tr>
				</thead>
				<tbody id="module_list_content">

					<c:forEach items="${items}" var="item" varStatus="status">
						<tr>
							<td>${item.rank}</td>
							<td>${item.studentName}</td>
							<td>${item.normalScore}</td>
							<td>${item.addScore}</td>
							<td>${item.addScore+item.normalScore}</td>
						</tr>

					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<thead>
					<tr role="row">
						<th>排名</th>
						<th>姓名</th>
						<th>年级班级</th>
						<th>评价总分</th>
					</tr>
				</thead>
				<tbody id="module_list_content">
					<c:forEach items="${items}" var="item" varStatus="status">
						<tr>
							<td>${item.rank}</td>
							<td>${item.studentName}</td>
							<td>${item.teamName}</td>
							<td>${item.addScore+item.normalScore}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>






	</table>
<%-- 											<jsp:include page="/views/embedded/jqpagination.jsp" --%>
<%-- 												flush="true"> --%>
<%-- 												<jsp:param name="id" value="kb_tb" /> --%>
<%-- 												<jsp:param name="url" value="/teach/starPerson/list?usertype=${usertype}&datetype=${datetype}&termCode=${termCode}&gradeId=${gradeId}&teamId=${teamId}&date=${date}&year=${year}" /> --%>
<%-- 												<jsp:param name="pageSize" value="${page.pageSize}" /> --%>
<%-- 												<jsp:param name="pageNumber" value="${page.currentPage}" /> --%>
<%-- 											</jsp:include> --%>

</div>
<script type="text/javascript">
// $(function() {
	

// 	$(".pagination input").val("当前第${page.currentPage}页,共${page.totalPages}页");
// })



	if(${flag} == 1){
		$("#button_pd").text("重新评定");
	}else {
		$("#button_pd").text("评定");

	}
	
</script>