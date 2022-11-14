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
			<jsp:param value="请假" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							请假列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>请假审请</a>
								</c:if>
							</p>
						</h3>
					</div>
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
			url : "${ctp}/office/ajax/leave/list",
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
			url : "${ctp}/office/ajax/leave/appr_list",
			dataType : "html", 
			success : function(data) {
				$("#leave_appr").empty();
				$("#leave_appr").append(data);
			}
		});
	}
 
	/* function leaveSaveModal() {
            
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/leave/to_save",
			dataType : "html",
			success : function(data) {
				$("#leave_save").empty();
				$("#leave_save").append(data);
			}
		});
	} */
	 
	
	 
// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop('请假审请', '${ctp}/office/ajax/leave/to_save', '900');
	}
</script>
</html>