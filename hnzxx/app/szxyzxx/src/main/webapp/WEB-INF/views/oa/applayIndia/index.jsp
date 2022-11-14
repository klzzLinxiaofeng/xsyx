<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">
<title>文印</title>
<script type="text/javascript">
     var dclickId;
	$(function() {
		$(".sq_list .clsq ul li .caozuo .delete").click(function() {
			$(this).parent().parent().remove()
		});
		
		$(".sq_list .entry ul li a").click(function() {
			$(".sq_list .entry ul li a").parent().removeClass("on");
			$(this).parent().addClass("on");
			var j = $(this).parent().index();
			dclickId = $(this).attr("data-id");
		});
		$(".oa_top .top_ul li a").click(function() {
			$(".oa_top .top_ul li a").removeClass("on");
			$(this).addClass("on");
			var i = $(this).parent().index();
		});
	});
</script>
</head>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon" />
		<jsp:param value="文印" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="oa_top">
					<ul class="top_ul">
						<li><a href="javascript:void(0)" class="on"
							onclick="getData('own');">我申请的</a></li>
						<li><a href="javascript:void(0)" onclick="getData('all');">全部用章</a></li>
<%--						<c:if test="${noDepartment==null || noDepartment==''}">--%>
<%--							<li><a href="javascript:void(0)"--%>
<%--								onclick="getData('department');">部门文印</a></li>--%>
<%--						</c:if>--%>
					</ul>
				</div>
				<div class="wy_all">
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="标题/发布人" id="ss"> <a
								class="sea_s" onclick="ss2();"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry" id="dep">
						<button class="btn btn-success" id="ycsq"
								onclick="loadCreatePage();">申请文印</button>
						<c:if test="${currentUser!=null}">
							<ul id="ownMenuId" style="display: none;">
								<li class="on"><a href="javascript:void(0)"
									onclick="depentmentSeach('all')">全部用章（<span class="all">0</span>）
								</a></li>
<%--								<c:forEach items="${oList}" var="olist" varStatus="i" >--%>
<%--								<input type="hidden" value="${oList.size()}" id="oListLength"/>--%>
<%--									<li><a href="javascript:void(0)"--%>
<%--										onclick="depentmentSeach('${olist.departmentId}')"--%>
<%--										data-id="${olist.departmentId }">${olist.departmentName} （--%>
<%--											<span class="people_numo${i.index}"> --%>
<%--											<c:forEach items="${numSqList}" var="num">--%>
<%--														<c:if test="${num.departmentId==olist.departmentId}">${num.number}</c:if>--%>
<%--													</c:forEach> --%>
<%--											</span>）--%>
<%--									</a></li>--%>
<%--								</c:forEach>--%>
							</ul>
							</c:if>
							<ul id="allMenuId" style="display: none;">
								<li class="on"><a href="javascript:void(0)"
									onclick="depentmentSeach('all')" data-id="all">全部用章（<span
										class="all">0</span>）
								</a></li>

<%--								<c:forEach items="${dList}" var="list" varStatus="i">--%>
<%--									<input type="hidden" value="${dList.size()}" id="dListLength" />--%>
<%--									<li><a href="javascript:void(0)"--%>
<%--										onclick="depentmentSeach('${list.id}')" data-id="${list.id }">${list.name}--%>
<%--											（ <span class="people_num${i.index}"> --%>
<%--											<c:forEach--%>
<%--												items="${numSqList}" var="num">--%>
<%--												<c:if test="${num.departmentId==list.id}">${num.number}</c:if>--%>
<%--						                     </c:forEach>--%>
<%--						                     </span>--%>
<%--											 ）--%>
<%--									--%>
<%--									</a></li>--%>
<%--								</c:forEach>--%>
							</ul>
							<div class="clear"></div>
						</div>
						<div class="clsq" id="applyIndia_list_content"
							style="margin-bottom: 15px;">
							<c:choose>
								<c:when test="${items.size()>0 }">
									<jsp:include page="./list.jsp" />
								</c:when>
								<c:otherwise>
									<div class="empty">
										<p>暂无用章申请</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="applyIndia_list_content" />
							<jsp:param name="url"
								value="/office/applayIndia/index?sub=list&auditType=${auditType}&departmentId=${departmentId}&dm=${param.dm}" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="clickId" />
</div>
</body>
<script type="text/javascript">
	$(function() {
		var all = $("#all").val();
		$(".all").text(all);
	})

	function addData() {
		var all = $("#all").val();
		$(".all").text(all); 
		
		var dListLength = $("#dListLength").val();
		for(var m=0;m<dListLength;m++){
			if($.trim($(".people_num"+m).text())=='' || $.trim($(".people_num"+m).text())=='0'){
				$(".people_num"+m).text('0');
			}
		}
		var oListLength = $("#oListLength").val();
		for(var j=0;j<oListLength;j++){
			if($.trim($(".people_numo"+j).text())=='' || $.trim($(".people_numo"+j).text())=='0'){
				$(".people_numo"+j).text('0');
			}
		}
		
	}

	function ss2() {
		var val={};
		if(dclickId=="all"){
			val = {
			"searchWord" : $("#ss").val()
		};
			
		}else{
			val = {
					"searchWord" : $("#ss").val(),
					"departmentId":dclickId
				};
		}
		var id = "applyIndia_list_content";
		var url = "/office/applayIndia/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
		addData()
	}

	//加载文印申请对话框
	function loadCreatePage() {
		window.location.href = "${ctp}/office/applayIndia/creator";
	}

	//  加载编辑对话框
	function loadEditPage(id) {
		window.location.href = "${ctp}/office/applayIndia/editor?id=" + id;
	}

	//	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	//	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/office/applayIndia/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#li_" + id ).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			window.location.reload();
		});
	}

	function getData(type) {
		if (type == 'department') {
			$("#ycsq").hide();
			$("#ownMenuId").show();
			$("#allMenuId").hide();
		} else if (type == 'all') {
			dclickId = "all";
			$("#ycsq").hide();
			$("#ownMenuId").hide();
			$("#allMenuId").show();
		} else {
			$("#ycsq").show();
			$("#ownMenuId").hide();
			$("#allMenuId").hide();
		}
		//查询个人、部门、所有的文印申请情况
		var val = {};
		var id = "applyIndia_list_content";
		var url = "";
		if (type === 'own') {
			url = "/office/applayIndia/index?sub=list&auditType=own&dm=${param.dm}";
		} else if (type === 'department') {
			url = "/office/applayIndia/index?sub=list&auditType=department&dm=${param.dm}";
		} else {
			url = "/office/applayIndia/index?sub=list&auditType=all&dm=${param.dm}";
		}
		$.ajaxSetup({
			async : false
		});
		myPagination(id, val, url);
		addData();
	}

	//查询个人、所有部门下的文印申请情况
	function depentmentSeach(departmentId) {
		if (departmentId === 'all') {
			departmentId = '';
		}
		var val = {};
		val={
			"searchWord" : $("#ss").val()	
		};
		var id = "applyIndia_list_content";
		var url = "/office/applayIndia/index?sub=list&departmentId="
				+ departmentId + "&dm=${param.dm}";
		$.ajaxSetup({
			async : false
		});
		myPagination(id, val, url);
		addData();
	}
</script>
</html>