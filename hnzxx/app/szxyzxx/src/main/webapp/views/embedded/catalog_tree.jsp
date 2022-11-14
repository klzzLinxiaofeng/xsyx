<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- <div class="container-fluid"> -->
<!-- 		<div class="row-fluid"> -->
<!-- 			<div class="span12"> -->
<!-- 				<div class="content-widgets"> -->
<!-- 					<div class="row-fluid"> -->
<!-- 						<div class="widget-container"> -->
<!-- 							<ul id="treeZylb" class="ztree"></ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- </div> -->
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
		var code = "${knowledgeVersion.code}";
		if(code === "undefined"){
			code = "";
		}
		var param = "parentId=" + treeNode.id + "&knowledgeVersionCode=" + code;
		return "${ctp}/teach/catalog/tree/json?" + param;
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
			$("#rMenu #module_bh").html(data[0].id);
			$("#rMenu #module_mc").html(data[0].name);
			$("#rMenu #module_lj").html(data[0].accessUrl);
			$("#rMenu #module_pxh").html(data[0].level);
			var parentId = data[0].parentId;
			if (parentId != null && parentId != "0" && parentId != "undefind") {
				requestData.id = parentId;
				$.getModuleData(requestData, function(dataTmp) {
					$("#rMenu #module_fcd").html(dataTmp[0].name);
				});
			}
			$("#rMenu #module_qy").html(0 == data[0].state ? "是" : "否");
			$("#rMenu #module_zyTb").attr("class", data[0].icon);
		});
		$("#rMenu ul").show();
// 		if (!treeNode.isParent) {
// 			$("#m_add").hide();
// 		} else {
// 			$("#m_add").show();
// 		}
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
			var parentId = zTree.getSelectedNodes()[0].id;
			loadCreateMenuPage(parentId);
		}
	}

	function editTreeNode() {
		hideRMenu();
		if (zTree.getSelectedNodes()[0]) {
			var id = zTree.getSelectedNodes()[0].id;
			loadEditMenuPage(id);
		}
	}

	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			var Id = nodes[0].id;
			var msg = "确定执行此次操作？";
			$.confirm(msg, function() {
				$.post("${pageContext.request.contextPath}/teach/catalog/delete/" + Id, {
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
// 		initModuleTree();
		rMenu = $("#rMenu");
	});

	function initModuleTree(selectr , code) {
		var requestData = {
				"knowledgeVersionCode" : code
		};
		$.get("${ctp}/teach/catalog/tree/json",
						requestData,
						function(value, status) {
							var data = eval("(" + value + ")");

							$.each(data, function(index, valueTmp) {
								valueTmp.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
							});

							$.fn.zTree.init($("#" + selectr), setting, data);
							zTree = $.fn.zTree.getZTreeObj(selectr);
						});
	}
	$.getModuleData = function($requestData, handller) {
		alert($requestData.id);
// 		$.get("${ctp}/teach/catalog/list/json", $requestData, function(value,
// 				status) {
// 			if ("success" === status) {
// 				data = eval("(" + value + ")");
// 				handller(data);
// 			}
// 		});
	}
</script>
