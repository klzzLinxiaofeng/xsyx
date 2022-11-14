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
			<jsp:param value="文印" name="title" />
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
								<li class="active"><a href="#1" onclick="myApplay()"
									 data-toggle="tab">我申请的</a>
								</li>
								<li ><a href="#2" onclick="allIndia()"
									 data-toggle="tab">全部文印</a>
								</li>
								  
								<!-- <li><a href="#3" onclick="departmentIndia()"
									data-toggle="tab">部门文印</a>
								</li> -->
								
								

							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="1">
								   <div id="myApplayList"></div>
								</div>
								<div class="tab-pane " id="2">
								  <div id="allIndia"></div> 
								</div> 
								 
								<div class="tab-pane" id="3" >
									<div id="departmentIndia"></div>
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
			 
			    
			myApplay();	 
		 

		});

	})(jQuery);
 
 
 function myApplay() {
	    
		 

		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/office/ajax/india/list",
			dataType : "html", 
			success : function(data) {
			 
				$("#myApplayList").empty();
				$("#myApplayList").append(data);
			}
		});
	}
 
 function allIndia() { 
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/office/ajax/allIndia/allIndia_index",
			dataType : "html", 
			success : function(data) {
				$("#allIndia").empty();
				$("#allIndia").append(data);
			}
		});
	}
 
	function departmentIndia() {
            
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/office/ajax/departmentIndia/departmentIndia_index",
			dataType : "html",
			success : function(data) {
				$("#departmentIndia").empty();
				$("#departmentIndia").append(data);
			}
		});
	}
	 
	
	
</script>
</html>