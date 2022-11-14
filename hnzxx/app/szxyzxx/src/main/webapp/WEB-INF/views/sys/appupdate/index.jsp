<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<jsp:param value="软件APP更新" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							软件APP更新
							<p class="btn_link" style="float: right;">
	<!-- 						window.location.reload(); -->
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();"><i class="fa fa-plus"></i>发布</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>产品：</span>
									<select id="select">
										<c:forEach items="${selectItems}" var="item" varStatus="status"> 
											<option value="${item.appKey}" <c:if test="${status.index==0}">selected="selected"</c:if> >${item.name }</option>
										</c:forEach>
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<p class="update-record" style="padding:20px 0 20px 0;">产品发布记录</p>
							<table class="responsive table table-striped" id="data-table">
								<thead id="update_number">
									<tr>
										<th>序号</th>
										<th>中文名称</th>
										<th>版本</th>
										<th>发布日期</th>
										<th>是否当前版本</th>
										<th>是否强制更新</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="app_list1_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="app_list1_content" />
								<jsp:param name="url" value="/sys/appupdate/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	search();
	$("#select").chosen();
});

	function search() {
		var val = {};
		var appKey = $("#select").val();
		if (appKey != null && appKey != "") {
			val.appKey = appKey;
		}else{
			val.appKey = null;
		}
		var id = "app_list1_content";
		var url = "/sys/appupdate/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop('发布', '${ctp}/sys/appupdate/creator', '700', '600');
	}
	
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromTop('编辑', '${ctp}/sys/appupdate/editor?id=' + id, '720', '600');
	}
	
	function setverison(id) {
		var url = "${ctp}/sys/appupdate/setverison"
		$.post(url,{"id":id},function(data,status){
			if ("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			} else {
				$.error("操作失败");
			}
		});
	}
	
	//设置强制更新
	function setForce(id) {
		var url = "${ctp}/sys/appupdate/setForce"
		$.post(url,{"id":id,"isForce":true},function(data,status){
			if ("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			} else {
				$.error("操作失败");
			}
		});
	}
	
	//设置强制更新
	function removeForce(id) {
		var url = "${ctp}/sys/appupdate/setForce"
		$.post(url,{"id":id,"isForce":false},function(data,status){
			if ("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			} else {
				$.error("操作失败");
			}
		});
	}
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/sys/appupdate/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>