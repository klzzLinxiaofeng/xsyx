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
			chkboxType : {"Y" : "p" , "N" : "s"},
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
	
	
	function creator(permissionCodes, groupIds) {

        var loader = new loadLayer();
        loader.show();
		var requestData = {"permissionCodes" : permissionCodes, "groupIds" : groupIds};
		$.post("${pageContext.request.contextPath}/sys/menusync/creator", requestData, function(data, status) {
			if("success" === status) {
				$.success("分配成功");
			} else {
				$.errot("网络异常");	
			}
            loader.close();
		});
	}
	
	function sweeper(permissionCode, groupId) {
		var requestData = {"permissionCode" : permissionCode, "groupId" : groupId};
		$.post("${pageContext.request.contextPath}/sys/menusync/sweeper", requestData, function(data, status) {
			if("success" === status) {

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
	function savePage(){
		var ids = new Array();
		var groupIds = new Array();
		var checked = $("#checkDiv input:checkbox[name='ids']:checked");
		var treeObj = $.fn.zTree.getZTreeObj("treeZylb");
		var nodes = treeObj.getCheckedNodes(true);
		if(checked.length>0){
		    for(var i=0;i<nodes.length;i++){
           	    ids.push(nodes[i].id);
           }
		    $.each(checked, function(index, value) {
			    groupIds.push($(value).val());
		    });
		    creator(ids,groupIds);
	    } else{
		    $.error("请选择分配的用户组");
	    }
	}
</script>
</html>