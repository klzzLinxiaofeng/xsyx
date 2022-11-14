<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" 
	rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<style>
    img{
       height:50px;
       width:55px;
    }
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="通讯录" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							通讯录
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${pageContext.request.contextPath}/teach/studentCheckAttendance/checkOutAll" method="get" style="position:relative;">						  
<!-- 							</form> -->
							<table class="responsive table table-striped" id="data-table">
								 <thead>
									<tr role="row">
											<th>序号</th>
											<th>头像</th>
											<th>姓名</th>
											<th>性别 </th>
											<th>电话 </th>
									</tr>
								</thead>
								<tbody id="mailList_list_content">
									<jsp:include page="./list.jsp" />
								</tbody> 
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="mailList_list_content" />
								<jsp:param name="url" value="/office/mailList/mailList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- <script type="text/javascript">

$(function(){
	var val = {};
	var id = "mailList_list_content";
	var url = "/office/mailList/mailList?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
});
</script> -->
</html> --%>