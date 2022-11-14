<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<c:choose>
	<c:when test="${!empty tdInfoList }">
		<%-- <div class="pailie">
			<p>
				共<span class="red">${allSum }</span>人
			</p>
			<!-- <div class="zm">
								<a href="javascript:void(0)">a</a>
								<a href="javascript:void(0)">b</a>
								<a href="javascript:void(0)">c</a>
								<a href="javascript:void(0)">d</a>
								<a href="javascript:void(0)">e</a>
								<a href="javascript:void(0)">f</a>
								<a href="javascript:void(0)">g</a>
								<a href="javascript:void(0)">h</a>
								<a href="javascript:void(0)">i</a>
								<a href="javascript:void(0)">j</a>
								<a href="javascript:void(0)">k</a>
								<a href="javascript:void(0)">l</a>
								<a href="javascript:void(0)">m</a>
								<a href="javascript:void(0)">n</a>
								<a href="javascript:void(0)">o</a>
								<a href="javascript:void(0)">p</a>
								<a href="javascript:void(0)">q</a>
								<a href="javascript:void(0)">r</a>
								<a href="javascript:void(0)">s</a>
								<a href="javascript:void(0)">t</a>
								<a href="javascript:void(0)">u</a>
								<a href="javascript:void(0)">v</a>
								<a href="javascript:void(0)">w</a>
								<a href="javascript:void(0)">x</a>
								<a href="javascript:void(0)">y</a>
								<a href="javascript:void(0)">z</a>
								<a href="javascript:void(0)" class="all">所有</a>
							</div> -->
		</div> --%>
		<div class="txl_list">
			<ul id="ajax_workmates_list">
				<jsp:include page="./ajaxMail_list.jsp" />
			</ul>
			<div style="margin-top:23%;width:87%;">
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="ajax_workmates_list" />
					<jsp:param name="url"
						value="/office/ajax/ajaxMail/ajaxMail_index?sub=ajaxMail_list&dm=${param.dm}&departmentId=${departmentId }" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<div class="txl_list">
		<div class="empty">
			<p>暂无数据!</p>
		</div>
	</div>
	</c:otherwise>
</c:choose>






