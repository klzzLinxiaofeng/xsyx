<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="/views/embedded/taglib.jsp" %>
  <div id="appr_leave_list_content">
								<jsp:include page="./appr_list.jsp" />
								</div>
								
								
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="appr_leave_list_content" />
								<jsp:param name="url" value="/office/ajax/appr/apprLeaveList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
								<div class="clear"></div>