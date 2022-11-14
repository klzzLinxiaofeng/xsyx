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
		<div style="height: 10px;"></div>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
				<div class="widget-head">
						<h3>
							成员列表
							<p class="btn_link" style="float: right;">
							<c:if test="${circleType==3}">
								<a href="javascript:void(0)" class="a3"
									onclick="loadSelectTeacherPage('${circleId}');"><i class="fa  fa-plus"></i>添加教师</a>
								<a href="javascript:void(0)" class="a4"
									onclick="loadSelectParentPage('${circleId}');"><i class="fa fa-plus"></i>添加家长</a>
							</c:if>
							<c:if test="${circleType==1}">
							<a href="javascript:void(0)" class="a2"
									onclick="synTeamCircles('${objectId}');"><i class="fa fa-undo"></i>同步此班级群</a>
							</c:if>
							<c:if test="${circleType==2}">
							<a href="javascript:void(0)" class="a2"
									onclick="synDeptCircles('${objectId}');"><i class="fa fa-undo"></i>同步此部门群</a>
							</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>成员名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" id="soso" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<!-- <th>用户在群的角色 circle_role.code</th>
											<th>加入时间</th>
											<th>离开时间</th> -->
											<th>头像</th>
											<th>姓名</th>
											<th>状态</th>
											<th>是否为管理员</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="user_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="user_list_content" />
								<jsp:param name="url" value="/bbx/circle/user/index?sub=list&dm=${param.dm}&circleId=${circleId}&circleType=${circleType}" />
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
		var circleId = '${circleId}';
		var circleType = '${circleType}';
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "user_list_content";
		var url = "/bbx/circle/user/index?sub=list&dm=${param.dm}&circleId="+circleId+"&circleType="+circleType;
		myPagination(id, val, url);
	}
	
	// 	加载选择老师对话框
	function loadSelectTeacherPage(circleId) {
		$.initWinOnTopFromLeft('添加教师', '${ctp}/bbx/circle/user/selectTeacher?circleId='+circleId, '900', '550');
	}
	// 	加载选择家长对话框
	function loadSelectParentPage(circleId) {
		$.initWinOnTopFromLeft('添加家长', '${ctp}/bbx/circle/user/selectParents?sub=enter&circleId='+circleId, '900', '550');
	}
	//  禁言或解禁
	function changeStatus(id, status) {
		$.confirm("确定执行此次操作？", function() {
			var url = '${ctp}/bbx/circle/user/changeStatus';
			var data = {id : id, status : status}
			$.post(url, data, function(data, status) {
				//loader.close();
				if("success" == status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" == data.info) {
						// 刷新列表
						setTimeout(function(){search();}, 1000);
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
			});
		});
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/circle/user/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/circle/user/viewer?id=' + id, '700', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id, circleId) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id, circleId);
		});
	}

	// 	执行删除
	function executeDel(obj, id, circleId) {
		$.post("${ctp}/bbx/circle/user/" + id, {"_method" : "delete", 'circleId' : circleId}, function(data, status) {
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
	
	// 同步单个班级群
	function synTeamCircles(objectId){
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/bbx/circle/user/synTeamCircles", {objectId:objectId}, function(data, status) {
			if ("success" === status) {
				loader.close();
				data = eval('('+data+')');
				if ("fail" === data.info) {
					$.error("同步失败，系统异常", 1);
				} else {
					if(data.removedCircleCount==1){
						$.success("同步成功, 对应班级不存在, 该群组被删除.");
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					}else{
						$.success("同步成功");
						$("#name").val('');
						setTimeout(function(){
							search();
						}, 1000);
					}
				}
			}
		});
	}
	
	// 同步单个班级群
	function synDeptCircles(objectId){
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/bbx/circle/user/synDeptCircles", {objectId:objectId}, function(data, status) {
			if ("success" === status) {
				loader.close();
				data = eval('('+data+')');
				if ("fail" === data.info) {
					$.error("同步失败，系统异常", 1);
				} else {
					if(data.removedCircleCount==1){
						$.success("同步成功, 对应部门不存在, 该群组被删除.");
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.success("同步成功");
						$("#name").val('');
						setTimeout(function(){
							search();
						}, 1000);
					}
				}
			}
		});
	}
</script>
</html>