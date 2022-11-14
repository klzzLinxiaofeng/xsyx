<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<div class="xj_div">
	<div class="xj_d">
		<div class="jszj">${content }</div>
		<div class="xsxj">
			<ul>
				<c:forEach items="${lpTaskUserActivityList }" var="taskActivity">
					<li>
						<div class="l_left">
							<img src="<avatar:avatar userId='${taskActivity.userId  }'></avatar:avatar>">
						</div>
						<div class="l_right">
							<div class="name">
								<b>${taskActivity.studentName }</b>
								<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${taskActivity.createTime }" />
							</div>
							<div class="neirong">
								<p>${taskActivity.content }</p>
								<c:forEach items="${taskActivity.files }" var="item">
									<img src="${item }">
								</c:forEach>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="xszj_list_id" />
			<jsp:param name="url" value="/learningPlan/task/activity/list?taskId=${taskId }&unitId=${unitId }" />
			<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>