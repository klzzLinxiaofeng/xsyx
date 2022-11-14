<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>车辆</title>
<script type="text/javascript">
	$(function(){
		addData();

		$(".sq_list .entry ul li a").click(function(){
			/* $(this).parent().siblings().removeClass("on"); */
			$(".sq_list .entry ul li").removeClass("on");
			$(this).parent().addClass("on");
		});
		$(".shenhe .s_four .cancel,.shenhe .close_div").click(function(){
			$(this).parent().parent().hide();
			$(".zhezhao").hide();
		})
	});
	
	function shenhe(){
		$(".shenhe,.zhezhao").show();
		var h = document.documentElement.clientHeight;
		var w= document.documentElement.clientWidth;
		var h1 = (document.documentElement.clientHeight-455)/2;
		var w1= (document.documentElement.clientWidth-900)/2;
		$(".zhezhao").css({"width":w,"height":h});
		$(".shenhe").css({"left":w1,"top":h1});
	}
	
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="车辆" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on" onclick="getData('own');">我的申请</a></li>
				            <c:if test="${noDepartment!='noDepartment'}">
				            	<li><a href="javascript:void(0)" onclick="getData('department');">部门记录</a></li>
				            </c:if>
				            <li><a href="javascript:void(0)" onclick="getData('all');">全部记录</a></li>
				        </ul>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号" id="ss">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry" id="dep">
							<button class="btn btn-success" id="ycsq" onclick="loadCreatePage();">申请用车</button>
							<ul id="ownMenuId" style="display: none;">
								<li class="on">
									<a href="javascript:void(0)" onclick="depentmentSeach('all')">全部申请（<span class="all">0</span>）</a>
								</li>
								<c:forEach items="${oList}" var="list" varStatus="i">
								<input type="hidden" value="${oList.size()}" id="oListLength"/>
									<li>
										<a href="javascript:void(0)" onclick="depentmentSeach('${list.departmentId}')">${list.departmentName}
											（
												<span class="people_numo${i.index}">
													<c:forEach items="${numSqList}" var="num">
														<c:if test="${num.departmentId==list.departmentId}">${num.number}</c:if>
													</c:forEach>
												</span>
											）
										</a>
									</li>
								</c:forEach>
							</ul>
							<ul id="allMenuId" style="display: none;">
								<li class="on">
									<a href="javascript:void(0)" onclick="depentmentSeach('all')">全部申请（<span class="all">0</span>）</a>
								</li>
								<c:forEach items="${dList}" var="list" varStatus="i">
									<input type="hidden" value="${dList.size()}" id="dListLength"/>
									<li>
										<a href="javascript:void(0)" onclick="depentmentSeach('${list.id}')">${list.name}
											（
												<span class="people_numd${i.index}">
													<c:forEach items="${numSqList}" var="num">
														<c:if test="${num.departmentId==list.id}">${num.number}</c:if>
													</c:forEach>
												</span>
											）
										</a>
									</li>
								</c:forEach>
							</ul>
						<div class="clear"></div>
						</div>
						<div class="clsq" id="applyCard_list_content" style="margin-bottom:15px;">
							<jsp:include page="./list.jsp" />
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="applyCard_list_content" />
								<jsp:param name="url" value="/oa/applycard/index?sub=list&auditType=${auditType}&departmentId=${departmentId}&dm=${param.dm}" />
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
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		$.ajaxSetup({
		   async: false
		});
		var id = "applyCard_list_content";
		var url = "/oa/applycard/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
		addData();
	}
	
	$(function(){
		$(".top_ul li a").click(function(){
			var i=$(this).parent().index();
			$(".top_ul li a").removeClass("on");
			$(this).addClass("on")
		})
		ss2();
		addData();
	})
	
	function addData(){
		var all = $("#all").val();
		$(".all").text(all);
		
		var dListLength = $("#dListLength").val();
		for(var j=0;j<dListLength;j++){
			if($.trim($(".people_numd"+j).text())=='' || $.trim($(".people_numd"+j).text())=='0'){
				$(".people_numd"+j).text('0');
			}
		}
		
		var oListLength = $("#oListLength").val();
		for(var j=0;j<oListLength;j++){
			if($.trim($(".people_numo"+j).text())=='' || $.trim($(".people_numo"+j).text())=='0'){
				$(".people_numo"+j).text('0');
			}
		}
	}
	
	function ss2(){
		$("#ss").keyup(function(e){
		    if(!e) var e = window.event; 
		    	var val = {
						"ssword" : $("#ss").val()	
					};
					var id = "applyCard_list_content";
					var url = "/oa/applycard/index?sub=list&dm=${param.dm}";
					$.ajaxSetup({
					   async: false
					});
					myPagination(id, val, url);
					addData()
		 });
	}
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/oa/applycard/creator', '1000', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/oa/applycard/editor?id=' + id, '1000', '600');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/applycard/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/oa/applycard/del/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_li").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	function getData(type){
		if(type=='department'){
			$("#ownMenuId li").removeClass("on");
			$("#ownMenuId li").eq(0).addClass("on");
			$("#ycsq").hide();
			$("#ownMenuId").show();
			$("#allMenuId").hide();
		}else if(type=='all'){
			$("#allMenuId li").removeClass("on");
			$("#allMenuId li").eq(0).addClass("on");
			$("#ycsq").hide();
			$("#ownMenuId").hide();
			$("#allMenuId").show();
		}else{
			$("#ycsq").show();
			$("#ownMenuId").hide();
			$("#allMenuId").hide();
		}
		//查询个人、部门、所有的用车情况
		var val = {};
		var id = "applyCard_list_content";
		var url = "";
		if(type==='own'){
			url = "/oa/applycard/index?sub=list&auditType=own&dm=${param.dm}";
		}else if(type==='department'){
			url = "/oa/applycard/index?sub=list&auditType=department&dm=${param.dm}";
		}else{
			url = "/oa/applycard/index?sub=list&auditType=all&dm=${param.dm}";
		}
		$.ajaxSetup({
		   async: false
		});
		myPagination(id, val, url);
		addData();
	}
	
	//查询个人、所有部门下的用车情况
	function depentmentSeach(departmentId){
		$("#id").val("departmentId");
		if(departmentId==='all'){
			departmentId='';
		}
		var val = {};
		var id = "applyCard_list_content";
		var url = "/oa/applycard/index?sub=list&departmentId="+departmentId+"&dm=${param.dm}";
		$.ajaxSetup({
		   async: false
		});
		myPagination(id, val, url);
		addData();
	}
</script>
</html>