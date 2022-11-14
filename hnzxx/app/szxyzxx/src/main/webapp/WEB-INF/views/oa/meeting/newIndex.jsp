<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>会议</title>
<script type="text/javascript">
	$(function(){
		$(".sq_list .entry ul li a").click(function(){
			$(".sq_list .entry ul li a").parent().removeClass("on");
			$(this).parent().addClass("on");
// 			var j=$(this).parent().index();
// 			$(this).parent().parent().parent().next().children().hide()
// 			$(this).parent().parent().parent().next().children().eq(j).show();
		})
		
		addClass_On();
		
	})
	
	window.onload = function(){
		var leng ="${fn:length(depList)}";
		for(var i=0;i<=leng;i++){
			if($.trim($(".depNums"+i).text())=="0" || $.trim($(".depNums"+i).text())==""){
				$(".depNums"+i).text("0");
			}
		}
	}
	
	function addClass_On(){
		var type = $("#type").val();
		if(type=="department"){
			$("#department_a").addClass("on");
		}else if(type=="myPublish"){
			$("#myPublish_a").addClass("on");
		}else if(type=="allRecord"){
			$("#allRecord_a").addClass("on");
		}else{
			$("#relatedWithMe_a").addClass("on");
		}
	}
	
	function searchByCondition(type){
		$("#ssWord").val("");
		window.location.href="${ctp}/office/meeting/newIndex?dm=${param.dm}&type="+type;
	}
	
	function seacherByDep(departmentId){
		$("#nowDepartmentId").val(departmentId);
		if(departmentId=="allRecord"){
			departmentId = "";
		}
		var val = {
				"ssWord" : $("#ssWord").val(),
				"departmentId" : departmentId
		};
		var id = "meeting_newList";
		var url = "/office/meeting/newIndex?sub=list&dm=${param.dm}&type=${type}";
		myPagination(id, val, url);
	}
	
	function seacher(){
		var departmentId = $("#nowDepartmentId").val();
		if(departmentId=="allRecord"){
			departmentId = "";
		}
		var val = {
				"ssWord" : $("#ssWord").val(),
				"departmentId" : departmentId
		};
		var id = "meeting_newList";
		var url = "/office/meeting/newIndex?sub=list&dm=${param.dm}&type=${type}";
		myPagination(id, val, url);
	}
	
	function createMeeting(){
		window.location.href="${ctp}/office/meeting/creatorMetting";
	}
	
	function editMeeting(id){
		window.location.href="${ctp}/office/meeting/editMetting?id="+id;
	}
	
// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/office/meeting/delMeeting/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					window.location.reload();
					$.success("删除成功");
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	//查看会议详细 判断是否有权限查看该会议 并判断会议是否开完  会议纪要是否写上 若写了则将会议查看人数加1
	function showMeetingContent(id,isCheck){
		if(isCheck=="false"){
			$.alert("对不起，您无权查看该会议内容！",2);
			return;
		}
		window.location.href="${ctp}/office/meeting/meetingInDetail?meetingId="+id;
	}
</script>
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
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" id="relatedWithMe_a" onclick="searchByCondition('relatedWithMe');">与我相关</a></li>
				            <c:if test="${hasDepartment == 'hasDepartment'}">
				            	<li><a href="javascript:void(0)" id="department_a" onclick="searchByCondition('department');">部门会议</a></li>
				            </c:if>
				            <li><a href="javascript:void(0)" id="allRecord_a" onclick="searchByCondition('allRecord');">全部会议</a></li>
				            <li><a href="javascript:void(0)" id="myPublish_a" onclick="searchByCondition('myPublish');">我组织的</a></li>
				        </ul>
					</div>
					<div class="wy_all">
						<div class="sq_list">
						<div class="search_1">
							<input type="text" id="ssWord" placeholder="标题/发布人">
							<a class="sea_s" href="javascript:void(0)" onclick="seacher()"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<button class="btn btn-success" onclick="createMeeting();">组织会议</button>
							</c:if>
							<ul>
								<c:if test="${type=='relatedWithMe'}">
									<li class="on"><a href="javascript:void(0)">与我相关（${totalSize}）</a></li>
								</c:if>
								<c:if test="${type=='department'}">
									<li class="on"><a href="javascript:void(0)">部门会议（${totalSize}）</a></li>
								</c:if>
								<c:if test="${type=='myPublish'}">
									<li class="on"><a href="javascript:void(0)">我组织的（${totalSize}）</a></li>
								</c:if>
								<c:if test="${type=='allRecord'}">
								<input type="hidden" id="nowDepartmentId" value="allRecord"/>
									<li class="on"><a href="javascript:void(0)" onclick="seacherByDep('allRecord')">全部记录（${totalSize}）</a></li>
									<c:forEach items="${depList}" var="dep" varStatus="count">
										<li><a href="javascript:void(0)" onclick="seacherByDep('${dep.id}')">${dep.name}（
											<span class="depNums${count.count}">
												<c:forEach items="${depNumList}" var="depNum">
													<c:if test="${dep.id==depNum.departmentId}">${depNum.meetingCount}</c:if>
												</c:forEach>
											</span>
										）</a></li>
									</c:forEach>
								</c:if>
							</ul>
							<div class="clear"></div>
						</div>
						<div class="clsq" >
							<div id="meeting_newList" style="margin-bottom:15px;">
								<jsp:include page="./newList.jsp" />
							</div>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="meeting_newList" />
							<jsp:param name="url" value="/office/meeting/newIndex?sub=list&dm=${param.dm}&type=${type}" />
							<jsp:param name="pageSize" value="${page.pageSize}"/>
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>