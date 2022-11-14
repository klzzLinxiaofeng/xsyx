<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.szxyzxx.contants.ResResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/css/extra/zyck.css" rel="stylesheet">
	<title>我的资源</title>
	<style>
		.course-box {
			float: left;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<%--		<c:if test="${personType eq 'res_school' || personType eq 'res_region'}">--%>
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon" />
		<jsp:param value="我的资源" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="weike_top">
					<div class="gjz_select">
						<input id="keyword" type="text" style="width: 200px;" placeholder="请输入资源关键词" value="">
						<button onclick="search();" class="btn btn-primary">搜索</button>
							<a href="${pageContext.request.contextPath}/crresource/uploadIndex?resType=${resType}&resourceType=${personType}&dm=${dm}" class="upload">
							<i class="fa fa-plus"></i> &nbsp; 上传资源
						</a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="kemu_nav">
											<ul>
												<c:if test="${personType eq 'res_school'}">
													<li><a class="active" href="javascript:void(0)" onclick="changeMyResourceType(null,this)">所有资源</a></li>
													<li><a href="javascript:void(0)" onclick="changeMyResourceType('true',this)">我收藏的资源</a></li>
													<li><a href="javascript:void(0)" onclick="changeMyResourceType(null,this,true)">我上传的资源</a></li>
												</c:if>
											</ul>
				</div>
				<div class="wrapper-right" style="padding:0;">
					<div id="discover-courses-rows">
						<div class="ud-coursecarousel course-list-wrapper collection">
							<div id="yourLike" style="margin-bottom: 10px;">
								<div class="rs_platformContainer_1">
									<div class="main" style="margin-left: -1px;">
										<c:if test="${personType eq 'res_school'}">
											<div class="top">
												<p class="left">
													<span>资源数</span>（<span id="total">${page.totalRows}</span>个）
													<span id="typeBar">
														<a id="_on" class="on" href="javascript:void(0)" onclick="changeType(null,this)">全部</a>
														<a id="<%=ResourceType.LEARNING_DESIGN%>_on" href="javascript:void(0)" onclick="changeType(<%=ResourceType.LEARNING_DESIGN%>,this)">课件</a>
														<a id="<%=ResourceType.HOMEWORK%>_on" href="javascript:void(0)" onclick="changeType(<%=ResourceType.HOMEWORK%>,this)">作业</a>
														<a id="<%=ResourceType.EXAM%>_on" href="javascript:void(0)" onclick="changeType(<%=ResourceType.EXAM%>,this)">试卷</a>
														<a id="<%=ResResourceType.PAID%>_on" href="javascript:void(0)" onclick="changeType(<%=ResResourceType.PAID%>,this)">教案</a>
													</span>
												</p>
												<div class="clear"></div>
											</div>
										</c:if>
										<div id="courses">
											<jsp:include page="./myResourceList.jsp" />
										</div>
										<jsp:include page="/views/embedded/jqpagination.jsp"
													 flush="true">
											<jsp:param name="id" value="courses" />
											<jsp:param name="url" value="/crresource/myResource?resType=${resType}&personType=${personType}" />
											<jsp:param name="pageSize" value="${page.pageSize }" />
										</jsp:include>
									</div>
									<div class="clear"></div>
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
<script type="text/javascript">
	var ptype="${personType}";
	var isFaved="${isFaved}"
	var isMyRes=false;
	function changeType(resType,obj) {
		$(obj).parent().children("a").removeClass("on");
		$(obj).addClass("on");
		var peType=ptype;
		if(isMyRes){
			peType="res_person";
		}
		var data={"resType":resType,"personType":peType,"isFaved":isFaved}
		myPagination("courses", data, "/crresource/myResource");
	}

	function myUserFav(obj) {
		$(".rs_platformContainer_1 .main .top .left a").removeClass("on");
		$(".rs_platformContainer_1 .main .top .left a").eq(0).addClass("on");
		$(obj).parent().parent().children().children("a").removeClass("active");
		$(obj).addClass("active");

		$(".rs_platformContainer_1").eq(0).hide();
		$(".rs_platformContainer_1").eq(1).show();
		myPagination("user_fav_list", {}, "/crresource/user/fav/list");
	}

	function changeMyResourceType(isFave,obj,isMy) {
		isFaved=isFave;
		$(".rs_platformContainer_1").eq(0).show();
		$(".rs_platformContainer_1").eq(1).hide();
		$(".rs_platformContainer_1 .main .top .left a").removeClass("on");
		$(".rs_platformContainer_1 .main .top .left a").eq(0).addClass("on");
		$(obj).parent().parent().children().children("a").removeClass("active");
		$(obj).addClass("active");

		var peType=ptype;
		if(isMy!=undefined){
			peType="res_person";
			isMyRes=true;
			$("#typeBar").hide();
		}else{
			$("#typeBar").show();
		}

		var data={"resType":"${resType}","personType":peType};
		if(isFave!=null){
			data.isFaved=isFave;
		}

		if(isMy!=undefined){
			data.isMySchool="true";
		}

		myPagination("courses", data, "/crresource/myResource");
	}

	function previewLp(lpId) {
		var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&editable=false&id="+lpId;
		window.open(url, "预览");
	}

	function leftHidden() {
		$(window.parent.document).find(".leftbar").hide();
		$(window.parent.document).find(".left_mu").css({
			width : '15px'
		});
		$(window.parent.document).find(".man_right").css({
			marginLeft : '0px'
		});
		$(window.parent.document).find(".left_close").addClass("left_open");
	}

	function search() {
		var title = $.trim($("#keyword").val());
		var url = "/crresource/myResource?personType="+ptype+"&isFaved="+isFaved;
		var data = {"title": title};
		myPagination("courses", data, url, function() {
			$("#courses .item-title a").each(function() {
				var ptext = $.trim($(this).text().toLowerCase());
				var rtext = "<font color=\"red\">" + title + "</font>";
				var ftext = ptext.replace(title, rtext);
				$(this).html(ftext);
			});
		});
	}

	function shareIndex(id){
		var mes = "共享";
		var url = "${pageContext.request.contextPath}/crresource/shareIndex?edit?personType="+ptype+"&dm=${dm}&id="+id;
		$.initWinOnTopFromLeft(mes, url, '950', '400');
	}

	function deleteMicro(verify,id) {
		if(verify == '7'|| verify=='4'){
			$.error("请先取消共享，然后进行删除");
			return;
		}
		$.confirm("确定删除此资源吗？", function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/crresource/delete",
				type: "POST",
				data: {
					"Id":id},
				async: false,
				success: function(data) {
					if(data=="2") {
						$.success("该导学案已经发布,无法删除");
					} else {
						$.success("资源删除成功！");
						if("${personType}"!='res_school'){
							location.href = "${pageContext.request.contextPath}/crresource/myResource?resType=${resType}&index=index&personType=res_person&dm=${dm}";
						}else{
							location.href = "${pageContext.request.contextPath}/crresource/myResource?resType=${resType}&index=index&personType=res_school&dm=${dm}";
						}
					}
				}
			});
		});
	}

	function verifyDelete(id,personType) {
		var personType1;
		if(personType === undefined){
			personType1 = ${personType}
		}else {
			personType1 = personType
		}

		$.confirm("确定取消共享此资源吗？", function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/crresource/verifyDelete",
				type: "POST",
				data: {
					"Id":id,
					"personType":personType1,

				},
				async: false,
				success: function() {
					$.success("取消资源共享成功！");
					location.href = "${pageContext.request.contextPath}/crresource/myResource?resType=${resType}&index=index&personType=res_person&dm=${dm}";
				}
			});
		});
	}

	function removeFav(id) {
		$.ajax({
			url: "${pageContext.request.contextPath}/crresource/fav?resType=${resType}&isFav=false",
			type: "POST",
			data: {"id": id},
			async: false,
			success: function() {
				$.success("取消收藏成功！");
				location.href = "${pageContext.request.contextPath}/crresource/myResource?index=index&resType=${resType}&personType=fav_resource&dm=${dm}";
			}
		});
	}


	$(function() {
		$("#keyword").on("keyup", function(event) {
			if (event.keyCode == 13) {
				search();
			}
		});

		$("#${resType}_on").parent().children("a").removeClass("on");
		$("#${resType}_on").addClass("on");
	});

</script>
</html>
