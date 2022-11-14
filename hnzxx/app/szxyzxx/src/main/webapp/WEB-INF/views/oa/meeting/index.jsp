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
			<jsp:param value="会议" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							会议列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>发起会议</a>
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
								<li class="active"><a href="#1" onclick="meetingModal()"
									 data-toggle="tab">我参与的会议</a>
								</li>
								<li ><a href="#2" onclick="meetingMyModal()"
									 data-toggle="tab">我发起的会议</a>
								</li>
								  
								<li ><a href="#3"   onclick="meetingSummaryModal()"
									 data-toggle="tab">会议纪要</a>
								</li> 
								
								

							</ul>
							<div class="tab-content">
								<div class="tab-pane " id="1">
								<div id="meetingList"></div>
								</div>
								<div class="tab-pane " id="2">
								  <div id="meeting_sponsorList"></div> 
								</div> 
								 
								<div class="tab-pane " id="3">
								  <div id="meeting_summaryList"></div> 
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
			 
			    
			 meetingModal();	 
		 

		});

	})(jQuery);
 
 
 function meetingModal() {
	    
		 

		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/meeting/myList",
			dataType : "html", 
			success : function(data) {
			 
				$("#meetingList").empty();
				$("#meetingList").append(data);
			}
		});
	}
 
 function meetingMyModal() { 
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/meeting/sponsorList",
			dataType : "html", 
			success : function(data) {
				$("#meeting_sponsorList").empty();
				$("#meeting_sponsorList").append(data);
			}
		});
	}
 
 
 function meetingSummaryModal() { 
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/meeting/summaryList",
			dataType : "html", 
			success : function(data) {
				$("#meeting_summaryList").empty();
				$("#meeting_summaryList").append(data);
			}
		});
	}

 
// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop('发起会议', '${ctp}/office/meeting/creator', '900');
	}
	
	
</script>
</html>