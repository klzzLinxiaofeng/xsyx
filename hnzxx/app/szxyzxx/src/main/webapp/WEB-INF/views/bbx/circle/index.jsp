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
			<jsp:param value="群组" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							群组列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a2"
									onclick="synTeamCircles();"><i class="fa fa-undo"></i>同步班级群</a>
								</c:if>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a2"
									onclick="synDeptCircles();"><i class="fa fa-undo"></i>同步部门群</a>
								</c:if>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建群组</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>群组名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="请输入群组名称" value="">
								</div>
								<button type="button" id="soso" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>班级</th>
											<!-- <th>圈子类型</th> -->
											<!-- <th>创建者</th> -->
											<!-- <th>管理员</th> -->
											<th>状态</th>
											<!-- <th>人数</th> -->
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="circle_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="circle_list_content" />
								<jsp:param name="url" value="/bbx/circle/index?sub=list&dm=${param.dm}" />
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
		// 给搜索框绑定回车事件
		$('#name').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				$("#soso").click();
			}
		}); 
	});
	
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "circle_list_content";
		var url = "/bbx/circle/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/circle/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/circle/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/circle/viewer?id=' + id, '700', '300');
	}
	// 弹出群成员页面
	function loadMemberPage(id, circleType, objectId) {
		$.initWinOnTopFromLeft('群成员', '${ctp}/bbx/circle/user/index?circleId=' + id + '&circleType=' + circleType + '&objectId=' + objectId, '900', '550');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/circle/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	// 	开通或关闭圈子对话框
	function changeStatus(status, objectId) {
		$.confirm("确定执行此次操作？", function() {
			executeChange(status, objectId);
		});
	}

	// 	执行开通或关闭操作
	function executeChange(status, objectId) {
		$.post("${ctp}/bbx/circle/changeStatus", {"_method" : "post", 'status' : status, 'objectId' : objectId}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("操作成功");
					search();
				} else if ("fail" === data) {
					$.error("操作失败，系统异常", 1);
				}
			}
		});
	}
	
	// 同步班级群
	function synTeamCircles(){
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/bbx/circle/user/synTeamCircles", function(data, status) {
			if ("success" === status) {
				loader.close();
				data = eval('('+data+')');
				if ("fail" === data.info) {
					$.error("同步失败，系统异常", 1);
				} else {
					$.success("同步成功");
					$("#name").val('');
					setTimeout(function(){
						$.refreshWin();
					}, 1000);
				}
			}
		});
	}
	
	// 同步班级群
	function synDeptCircles(){
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/bbx/circle/user/synDeptCircles", function(data, status) {
			if ("success" === status) {
				loader.close();
				data = eval('('+data+')');
				if ("fail" === data.info) {
					$.error("同步失败，系统异常", 1);
				} else {
					$.success("同步成功");
					$("#name").val('');
					setTimeout(function(){
						$.refreshWin();
					}, 1000);
				}
			}
		});
	}
</script>
</html>