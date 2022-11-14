<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				
<ul id="treeZylb" class="ztree"></ul>

<input type="hidden" id="groupId" value="${param.groupId}">

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
		check : {
			autoCheckTrigger : true,
			chkboxType : {"Y" : "s" , "N" : "ps"},
			chkStyle : "checkbox",
			enable : true,
			nocheckInherit : true,
			chkDisabledInherit : false,
			radioType : "level"
		},
		view : {
			expandSpeed : "",
		},
		callback : {
			beforeExpand : beforeExpand,
			onAsyncSuccess : onAsyncSuccess,
			onAsyncError : onAsyncError,
			onCheck : onCheck
		}
	};
	
	function onCheck(event, treeId, treeNode) {
	}
	
	function creator(permissionCode) {
		var requestData = {"permissionCode" : permissionCode};
		$.post("${pageContext.request.contextPath}/sys/menucustom/creator", requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
				$.closeWindow();
			} else {
				$.errot("网络异常");	
			}
		});
	}
	
	function getUrl(treeId, treeNode) {
		var groupId = $("#groupId").val();
		var param = "parentCode=" + treeNode.id + "&groupId=" + groupId + "&check=true";
		return "${pageContext.request.contextPath}/sys/module/tree/json?" + param;
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
			$.each(treeNode.children, function(index, value) {
				value.icon = "${pageContext.request.contextPath}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
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

	var rMenu;
	$(document).ready(function() {
		initModuleTree();
		rMenu = $("#rMenu");
	});

	function initModuleTree() {
		var groupId = $("#groupId").val();
		var requestData = {"check" : true, "groupId" : groupId, "parentCode" : "0"};
		$.get("${pageContext.request.contextPath}/sys/module/tree/json", requestData, function(value, status) {
			var data = eval("(" + value + ")");
	
			$.each(data, function(index, valueTmp) {
				valueTmp.icon = "${pageContext.request.contextPath}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
			});
			
			$.fn.zTree.init($("#treeZylb"), setting, data);
			zTree = $.fn.zTree.getZTreeObj("treeZylb");
		});
	}

	$.getModuleData = function($requestData, handller) {
		$.get("${pageContext.request.contextPath}/sys/module/list/json", $requestData, function(value, status) {
			if ("success" === status) {
				data = eval("(" + value + ")");
				handller(data);
			}
		});
	}
</script>
</html>