<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="待办中心" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					 
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div>
						<div>
							<ul class="nav nav-tabs">
								<li class="active"><a href="#1" onclick="leaveModal()"
									 data-toggle="tab">审批中</a>
								</li>
								<li ><a href="#2" onclick="leaveAppModal()"
									 data-toggle="tab">已审批</a>
								</li>
								  
								 
								
								

							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="1">
								<div id="leaveList"></div>
								</div>
								<div class="tab-pane " id="2">
								  <div id="leave_appr"></div> 
								</div> 
								 
								 
							</div>
						</div>
					</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
 <script>
	(function($) {
		$(document).ready(function() { 
			leaveModal();
		});

	})(jQuery);
 
 
 function leaveModal() { 
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/appr/leaveList",
			dataType : "html", 
			success : function(data) {
			 
				$("#leaveList").empty();
				$("#leaveList").append(data);
			}
		});
	}
 
 function leaveAppModal() { 
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/appr/apprLeaveList",
			dataType : "html", 
			success : function(data) {
				$("#leave_appr").empty();
				$("#leave_appr").append(data);
			}
		});
	}
  
	 
</script>
</html>