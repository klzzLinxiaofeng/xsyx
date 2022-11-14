<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<style>
.cdlj {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	word-wrap: normal;
	max-width: 520px;
	display: block;
}
</style>
</head>
<body>
	<!-- <div class="layout">
		<div class="main-wrapper"> -->
	<div class="container-fluid">
		<div class="row-fluid ">
			<div class="span12">
				<ul class="breadcrumb">
					<li><a href="javascript:void(0);"><i class="fa icon-glass"
							name="dashboard"></i>App应用管理</a></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="page-header" style="height: 34px">
							应用管理列表
							<p style="float: right;" class="btn_link">
								<a onclick="$.refreshWin();" class="a3" href="javascript:void(0)"><i class="fa  fa-undo"></i>刷新列表</a> 
								<c:if  test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a onclick="loadCreateTopMenuPage()" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>创建顶级App菜单</a>
								</c:if>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="widget-container">
				<ul id="treeZylb" class="ztree"></ul>
			</div>
		</div>
	</div>
	<!-- 	</div>
	</div> -->
	<div id="rMenu" class="rMenu">
		<div class="content-widgets light-gray">
			<div class="widget-container">
				<form id="menu_form" class="form-horizontal" novalidate="novalidate">
					<div class="control-group">
						<label class="control-label">App名称</label>
						<div class="controls" id="module_name">迅云产品</div>
					</div>
					<div class="control-group">
						<label class="control-label">appKey</label>
						<div class="controls" id="module_appKey"></div>
					</div>
					<div class="control-group">
						<label class="control-label">版本号</label>
						<div class="controls" id="module_version">1</div>
					</div>
					<div class="control-group">
						<label class="control-label">类型</label>
						<div class="controls" id="module_recordType">1</div>
					</div>
				</form>
			</div>
		</div>
		<ul>
			<li id="m_add" onclick="addTreeNode();">添加子App</li>
			<li id="m_edit" onclick="editTreeNode();">修改</li>
			<li id="m_del" onclick="removeTreeNode();">删除</li>
		</ul>
	</div>
</body>
<script type="text/javascript">
	var globalzTree;
	var requestData = {};
	var setting = {
		async : {
			enable : true,
			url : getUrl
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			expandSpeed : "",
		},
		callback : {
			beforeExpand : beforeExpand,
			onAsyncSuccess : onAsyncSuccess,
			onAsyncError : onAsyncError,
			onClick : onClick
		}
	};

	function getUrl(treeId, treeNode) {
		var param = "parentId=" + treeNode.id;
		return "${ctp}/sys/appTree/tree/json?" + param;
	}

	function ajaxGetNodes(treeNode, reloadType) {
		if (reloadType == "refresh") {
			zTree.updateNode(treeNode);
		}
		zTree.reAsyncChildNodes(treeNode, reloadType, true);
	}

	function beforeExpand(treeId, treeNode) {
		if (!treeNode.isAjaxing) {
			treeNode.times = 1;
			ajaxGetNodes(treeNode, "refresh");
			return true;
		} else {
			return false;
		}
	}

	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (!msg || msg.length == 0) {
			return;
		}
		var totalCount = treeNode.count;
		if (treeNode.children.length < totalCount) {
			setTimeout(function() {
				ajaxGetNodes(treeNode);
			}, perTime);
		} else {
			$.each( treeNode.children, function(index, value) {
				value.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
				zTree.updateNode(value);
			});
			zTree.selectNode(treeNode.children[0]);
		}
	}

	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus,
			errorThrown) {
		treeNode.icon = "";
		zTree.updateNode(treeNode);
	}

	function onClick(event, treeId, treeNode) {
		showRMenu(treeNode, event.clientX, event.clientY);
	}

	function showRMenu(treeNode, x, y) {
		requestData.id = treeNode.id;
		$.getModuleData(requestData, function(data) {
			$("#rMenu #module_name").html(data[0].name);
			$("#rMenu #module_appKey").html(data[0].appKey);
			$("#rMenu #module_version").html(data[0].version);
			$("#rMenu #module_recordType").html(1 == data[0].recordType ? "产品" : (0 == data[0].recordType ? "分组" : "版本"));
			var parentCode = data[0].parentId;
			if (parentCode != null && parentCode != "0" && parentCode != "undefind") {
				requestData.code = parentCode;
				$.getModuleData(requestData, function(dataTmp) {
					$("#rMenu #module_fcd").html(dataTmp[0].name);
				});
			}
			$("#rMenu #module_qy").html(0 == data[0].state ? "是" : "否");
			$("#rMenu #module_zyTb").attr("class", data[0].icon);
		});
		$("#rMenu ul").show();
		if (!treeNode.isParent) {
			$("#m_add").hide();
		} else {
			$("#m_add").show();
		}
		rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
		$("body").bind("mousedown", onBodyMouseDown);
	}

	function hideRMenu() {
		if (rMenu)
			rMenu.css({
				"visibility" : "hidden"
			});
		$("body").unbind("mousedown", onBodyMouseDown);
	}

	function onBodyMouseDown(event) {
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
			rMenu.css({
				"visibility" : "hidden"
			});
		}
	}

	function addTreeNode() {
		hideRMenu();
		if (zTree.getSelectedNodes()[0]) {
			var parentCode = zTree.getSelectedNodes()[0].id;
			loadCreateMenuPage(parentCode);
		}
	}

	function editTreeNode() {
		hideRMenu();
		if (zTree.getSelectedNodes()[0]) {
			var code = zTree.getSelectedNodes()[0].id;
			loadEditMenuPage(code);
		}
	}

	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			var code = nodes[0].id;
			var msg = "确定执行此次操作？";
			$.confirm(msg, function() {
				$.post("${pageContext.request.contextPath}/sys/appTree/" + code, {
					"_method" : "delete"
				}, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("删除成功");
							zTree.removeNode(nodes[0]);
						} else if ("fail" === data) {
							$.error("删除失败，系统异常");
						} else if ("exist_sub_res" === data) {
							$.error("删除失败,存在子菜单");
						}
					}
				});
			});

		}
	}

	var rMenu;
	$(document).ready(function() {
		initModuleTree();
		rMenu = $("#rMenu");
	});

	function initModuleTree() {
		var requestData = {};
		$.get("${ctp}/sys/appTree/tree/json",
						requestData,
						function(value, status) {
							var data = eval("(" + value + ")");

							$.each(data, function(index, valueTmp) {
								valueTmp.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
							});

							$.fn.zTree.init($("#treeZylb"), setting, data);
							zTree = $.fn.zTree.getZTreeObj("treeZylb");
						});
	}

	// 	加载创建菜单对话框
	function loadCreateMenuPage(parentCode) {
		$.initWinOnTopFromTop('创建菜单',
				'${pageContext.request.contextPath}/sys/appTree/creator?caller=tree&parentId='
						+ parentCode, "600", "500");
	}
	// 	加载创建菜单对话框
	function loadCreateTopMenuPage() {
		$.initWinOnTopFromTop('迅云产品',
						'${pageContext.request.contextPath}/sys/appTree/creator?parentId=0', "600", "580");
	}
	//  加载编辑菜单对话框
	function loadEditMenuPage(code) {
		$.initWinOnTopFromTop('编辑菜单', '${pageContext.request.contextPath}/sys/appTree/editor?caller=tree&id=' + code, "600", "580");
	}

	$.getModuleData = function($requestData, handller) {
		$.get("${ctp}/sys/appTree/list/json", $requestData, function(value,
				status) {
			if ("success" === status) {
				data = eval("(" + value + ")");
				handller(data);
			}
		});
	}
</script>
</html>