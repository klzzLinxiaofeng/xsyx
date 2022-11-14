<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty isQuery  || !isQuery}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
	<%@ include file="/views/embedded/common.jsp" %>
	<title>通知阅读详情</title>
</head>
<body>



<div class="container-fluid">
	<div class="row-fluid" style="margin-top: 10px;">
		<div class="span12">
			<div class="content-widgets white">
				<div class="content-widgets">
					<div class="widget-container">
						<div class="select_b">

							<div class="select_div">
								<span>是否已读：</span>
								<select name="read" id="read">
									<option value="0" selected="selected">未读</option>
									<option value="1">已读</option>
								</select>
							</div>

							<div class="select_div">
								<span>姓名：</span>
								<input type="text" id="name" name="name" data-id-container="name" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
							</div>

							<button onclick="search()" class="btn btn-primary" type="button">查询</button>
							<div class="clear"></div>
						</div>
						<table class="responsive table table-striped" id="data-table">
							<thead>
							<tr role="row">
								<th style="width:215px;">姓名</th>
								<th style="width:100px;">用户类型</th>
								<th style="width:100px;">是否阅读</th>
								<th style="width:100px;">阅读时间</th>
							</tr>
							</thead>
							<tbody id="module_list_content">
	</c:if>
							<tr style="display: none">
								<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
								<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
							</tr>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.receiver_name}</td>
									<td>${item.receiver_type}</td>
									<td>${item.read ? "已读":"未读"}</td>
									<c:choose>
										<c:when test="${item.read}">
											<td><fmt:formatDate value="${item.modify_date}" pattern="yyyy-MM-dd HH:mm"/></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
<c:if test="${empty isQuery || !isQuery}">
							</tbody>
						</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content"/>
							<jsp:param name="url" value="/office/notice/readDetail?isQuery=true&noticeId=${noticeId}"/>
							<jsp:param name="pageSize" value="${page.pageSize }"/>
						</jsp:include>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var name = $("#name").val();
		var read = $("#read").val();
		if (name != null && name != "") {
			val.receiverName = name;
		}
		val.isQuery=true;
		val.read = read;
		val.noticeId=${noticeId};
		var id = "module_list_content";
		var url = "/office/notice/readDetail";
		myPagination(id, val, url);
	}

</script>
</html>
	</c:if>