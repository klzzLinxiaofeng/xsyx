<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>${param.isMicro ? '微课' : '学科资源'}</title>
<style>
.course-box {
	float:left;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="${param.isMicro ? '微课列表' : titleName }" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" >
					<c:choose>
						<c:when test="${param.isMicro}">
							<jsp:include page="/views/embedded/resource/resource_micro_nav.jsp">
								<jsp:param name="type" value="fenlei" ></jsp:param>
								<jsp:param value="${personType}" name="personType"/>
							</jsp:include>	
						</c:when>
						<c:otherwise>
							<jsp:include page="/views/embedded/resource/resource_nav.jsp">
								<jsp:param name="type" value="fenlei" ></jsp:param>
								<jsp:param value="${personType}" name="personType"/>
							</jsp:include>
						</c:otherwise>
					</c:choose>
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike" style="margin-bottom: 10px;">
									<div class="courses-header">
										<jsp:include page="/views/embedded/resource/resource_textbox.jsp">
											<jsp:param value="catalog_div" name="catalog_div"/>
											<jsp:param value="catalogCallback" name="callback"/>
											<jsp:param value="${condition.stageCode}" name="stageCode"/>
											<jsp:param value="${condition.subjectCode}" name="subjectCode"/>
											<jsp:param value="${condition.gradeCode}" name="gradeCode"/>
											<jsp:param value="${condition.versionCode}" name="versionCode"/>
											<jsp:param value="${condition.volumnCode}" name="volumnCode"/>
											<jsp:param value="${personType}" name="personType"/>
											<jsp:param value="${dm}" name="dm"/>
											
										</jsp:include>
									</div>
									<div class="rs_platformContainer_1">
										<div class="unit-area xkzy" id="catalog_div">
										</div>
										<div class="main">
											<div class="zy_list">
												<div class="xkzy_list" id="zy_list">
												</div>
												<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
													<jsp:param name="id" value="studentAid_list_content" />
													<jsp:param name="url" value="/resource/searcher?dm=${param.dm}" />
													<jsp:param name="pageSize" value="${page.pageSize}" />
												</jsp:include>
												<div class="clear"></div>
											</div>
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
function catalogCallback(code, obj) {
	$(obj).parents("#acatalogList_div").find("a").removeClass("on");
	$(obj).parent().addClass("on");
	search(code);
}

function search(data) {
	var val = {};
	if(data != null && data != "" && data != "-1") {
		val.catalogCode = data;	
	}
	val.stageCode = $("#stageCode_td .on").attr("data-code");
	val.subjectCode = $("#subjectCode_td .on").attr("data-code");
	val.versionCode = $("#publisherId_div .on").attr("data-code");
	var gradeDiv = $("#gradeCode_div .on");
	val.gradeCode = gradeDiv.attr("data-code");
	val.volumnCode = gradeDiv.attr("data-volumn-code");
	val.resType = "${param.resType}";
	val.isMicro = "${param.isMicro}";
	val.personType="${personType}";
	var resType;
	resType=$('.top .left .on').attr("data-code");
	if(resType === undefined){
		val.resType ="${param.resType}";
	}else{
		val.resType=$('.top .left .on').attr("data-code");
	}

	var id = "zy_list";
	var url = "/resource/searcher?dm=${dm}";
	myPagination(id, val, url);
}


$(function() {
	$("#zy_list").on("click", ".cz_btn .shoucan", function() {
		var $this = $(this);
		var isFaved = $this.attr("data-isFaved");
            var id=$this.attr("data-id");
		//取消收藏
		if("true" === isFaved) {
			cancelFav(id, function() {
				$this.attr("data-isFaved", "false");
				$this.text("收藏");
			});
		} else {
			//收藏
			fav(id, function() {
				$this.attr("data-isFaved", "true");
				$this.text("取消收藏");
			});
		}
	});
});

function fav(id, callback) {
	var requestData = {"id":id,"isFav" : true};
	$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
		if("success" === status) {
			if ("faved" === data) {
				$.success("已收藏过");
			} else if ("success" === data) {
				$.success("收藏成功");
				var favedCount = $("#faved_count_" + id).html();
				$("#faved_count_" + id).html(parseInt(favedCount) + 1);
				callback();
			} else {
				$.error("收藏失败");
			}		
		} else {
			$.error("服务器响应异常!");
		}
	});
}

function cancelFav(id, callback) {
	var requestData = {"id":id, "isFav" : false};
	$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
		if("success" === status) {
			if ("faved" === data) {
			} else if ("success" === data) {
				$.success("取消收藏成功");
				var favedCount = $("#faved_count_" + id).html();
				$("#faved_count_" + id).html(parseInt(favedCount) - 1);
				callback();
			} else {
				$.error("取消收藏失败");
			}		
		} else {
			$.error("服务器响应异常!");
		}
	});
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
</script>
</html>