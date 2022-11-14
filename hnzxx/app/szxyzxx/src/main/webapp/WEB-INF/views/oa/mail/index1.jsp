<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">
<title>通讯录</title>
<script type="text/javascript">
	$(function() {
		addClass_On();
		/* $(".txl .select_peo ul li a").click(function() {
			$(".txl .select_peo ul li a").parent().removeClass("on");
			$(this).parent().addClass("on");
			var i = $(this).parent().index();
			$(".txl_list ul").hide();
			$(".txl_list ul").eq(i).show();
		}) */
	})
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="通讯录" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
							<li><a href="javascript:void(0)" class="on">通讯录</a></li>
						</ul>
					</div>
					<div class="txl">
						<div class="select_peo">
							<ul>
								<li id="all"><a href="javascript:void(0);"
									onclick="searchByCondition('all')">全部同事</a></li>
								<c:forEach items="${departmentList }" var="department">
									<li id="type_${department.id }"><a
										href="javascript:void(0);"
										onclick="searchByCondition('${department.id}');" title="${department.name }">
										  <c:choose>
												<c:when test="${fn:length(department.name)>6}">
													<c:out value="${fn:substring(department.name,0,6)}"></c:out>
													...
												</c:when>
												<c:otherwise>${department.name }</c:otherwise>
											</c:choose> 
<%-- 											${department.name } --%>
									</a></li>
								</c:forEach>
							</ul>
							<div class="search_ts">
								<input type="text" placeholder="搜索同事" id="name" /> <a
									href="javascript:void(0)" onclick="ss();"><i
									class="fa fa-search" style="color: #aaa"> </i></a>
							</div>
						</div>
						<!-- <div class="pailie">
							<p>共<span class="red">5</span>人</p>
							<div class="zm">
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
							</div>
						</div> -->
						<div class="txl_list" id="mail_list">
							<jsp:include page="./list.jsp" />
						</div>
					</div>
						<div style="margin-top:20%; margin-right:14%;">
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="mail_list" />
								<jsp:param name="url"
									value="/office/mail/index1?sub=list&dm=${param.dm}&type=${type }" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function searchByCondition(type) {
		window.location.href = "${ctp}/office/mail/index1?dm=${param.dm}&type="
				+ type;
	}

	function ss() {
		$.ajaxSetup({
			async : false
		});
		var type = $("#type").val();
		var val = {
			"name" : $("#name").val()
		};
		var id = "mail_list";
		var url = "/office/mail/index1?sub=list&dm=${param.dm}&type=" + type;
		myPagination(id, val, url);

	}

	$("#name").keyup(function(e) {
		if (!e)
			var e = window.event;
		var val = {
			"name" : $("#name").val()
		};

		var type = $("#type").val();
		var id = "mail_list";
		var url = "/office/mail/index1?sub=list&dm=${param.dm}&type=" + type;
		myPagination(id, val, url);

	});

	function addClass_On() {
		var type = $("#type").val();
		if (type == "all") {
			$("#all").addClass("on");
		} else {
			$("#type_" + type).addClass("on");
		}
	}
</script>

</html>