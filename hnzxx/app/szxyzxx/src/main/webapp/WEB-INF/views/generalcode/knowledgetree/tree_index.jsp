<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>知识树管理</title>
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
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param name="title" value="知识树管理" />
		<jsp:param name="icon" value="icon-glass" />
		<jsp:param name="menuId" value="${param.dm}"  />
	</jsp:include>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
					<h3>
						知识树信息
						<p style="float: right;" class="btn_link">
							<a href="javascript:void(0)"  class="a3" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</a>
							<a onclick="$.refreshWin();" class="a3" href="javascript:void(0)"><i class="fa  fa-undo"></i>刷新列表</a>
							<a id="addBtn" onclick="loadCreateTopMenuPage()" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>创建顶层节点</a>
						</p>

					</h3>
				</div>
				<div class="content-widgets" style="padding-right:15px;">
					<div class="widget-container" style="width: 200px; float: left">
						<ul id="treeDept" class="ztree"></ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="rMenu" class="rMenu" style="width:296px;">
	<div class="content-widgets light-gray">
		<div class="widget-container">
			<form id="menu_form" class="form-horizontal" novalidate="novalidate" style="padding-bottom:0;">
				<div class="control-group">
					<label class="control-label">节点名称</label>
					<div class="controls" id="dept_name">无</div>
				</div>
				<div class="control-group">
					<label class="control-label">父节点</label>
					<div class="controls" id="parent_name">无</div>
				</div>
				<div class="control-group" style="display:none;">
					<label class="control-label">等级</label>
					<div class="controls" id="level">无</div>
				</div>
			</form>
		</div>
	</div>
	<ul class="btn_xiu">
		<li id="m_add" onclick="addTreeNode();">添加子节点</li>
		<li id="m_edit" onclick="editTreeNode();">修改</li>
		<li id="m_del" onclick="removeTreeNode();">删除</li>
	</ul>
</div>
<input type="hidden" id="id" value="" />
</body>


<script type="text/javascript">

	var requestData = {};
	var globalzTree;
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
		edit : {
			enable : true,//是否可用
			showRemoveBtn : false,//是否显示删除按钮
			showRenameBtn : false,//是否显示修改按钮
			drag : {
				isCopy : false,//是否复制
				isMove : true,//是否移动
				inner : false//禁止拖拽成为目标节点的子节点
			}
		},
		callback : {
			beforeExpand : beforeExpand,
			onAsyncSuccess : onAsyncSuccess,
			onAsyncError : onAsyncError,
			onClick : onClick,
			onRightClick : onRightClick,
			beforeDrag : beforeDrag,
			beforeDrop : beforeDrop,
			onDrop : onDrop
		}
	};

	//在鼠标移动的时候，获取移动当前节点的父节点
	var dragId;
	function beforeDrag(treeId, treeNodes) {
		for (var i = 0, l = treeNodes.length; i < l; i++) {
			dragId = treeNodes[i].pId;
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}

	var canModify = false;
	var node = null;
	//鼠标释放的时候，进行序号的更新操作
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		if(targetNode.pId == dragId){   //如果拖动的时候节点的父节点与释放之后所在的父节点一致；进行保存；否则不在同级
			node = treeNodes;
			canModify = true;
		} else{
			canModify = false;
			$.alert('该排序只能进行同级排序！');
			return false;
		}
	}

	function onDrop(event,treeId, targetNode, moveType,isCopy){
		if(canModify){   //如果拖动的时候节点的父节点与释放之后所在的父节点一致；进行保存；否则不在同级
			var ids = getChildren(node);
			var url = "${pageContext.request.contextPath}/generalcode/knowledgetree/editOrder";
			$.post(url, {"_method" : "post","ids" : ids}, function(data, status) {
				data = JSON.parse(data);
				if ("success" === status) {
					if ("success" == data.info) {
// 								$.success("排序成功");   //成功时不提示
					} else if ("fail" === data.info) {
						$.error("排序失败");
					}
				}
			});
		} else{
			$.alert('该排序只能进行同级排序！');
			return false;
		}
	}

	//获取某节点下的所有子节点   根据当前节点找到父节点下的子节点
	function getChildren(treeNodes) {
		var ids = "";
		if(treeNodes[0].getParentNode() != null){
			var ztLength = treeNodes[0].getParentNode().children.length;
			var treeNodesObj = treeNodes[0].getParentNode().children;
			for(var i = 0; i < ztLength; i++){
				if(ids == ""){
					ids = treeNodesObj[i].id;
				}else{
					ids = ids + "," + treeNodesObj[i].id;
				}
			}
		}else{
			var treeObj = $.fn.zTree.getZTreeObj("treeDept");
			for(var j = 0; j < treeObj.getNodes().length; j++){
				if(ids == ""){
					ids = treeObj.getNodes()[j].id;
				}else{
					ids = ids + "," + treeObj.getNodes()[j].id;
				}
			}
		}

		return ids;
	}

	//右击编辑当前节点
	function onRightClick(event, treeId, treeNode) {
		showRMenu(treeNode, event.clientX, event.clientY);
	}

	function getUrl(treeId, treeNode) {
		var param = "parentId=" + treeNode.id;
		return "${ctp}/generalcode/knowledgetree/tree/json?" + param;
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
			$.each(treeNode.children,function(index, value) {
				value.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
				zTree.updateNode(value);
			});
			zTree.selectNode(treeNode.children[0]);
		}
	}

	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus,errorThrown) {
		treeNode.icon = "";
		zTree.updateNode(treeNode);
	}

	function onClick(event, treeId, treeNode) {
		showRMenu(treeNode, event.clientX, event.clientY+$(document).scrollTop());
	}

	function showRMenu(treeNode, x, y) {
		requestData = {};
		requestData.id = treeNode.id;
		$.getDeptData(requestData, function(data) {
			$("#rMenu #dept_name").html(data[0].name);
			$("#level").html(data[0].level);
			var parentId = data[0].parentId;
			if (parentId != null && parentId != "" && parentId != "undefind") {
				requestData = {};
				requestData.id = parentId;
				$.getDeptData(requestData, function(dataTmp) {
					$("#rMenu #parent_name").html(dataTmp[0].name);
				});
			} else {
				$("#rMenu #parent_name").html("无");
			}
		});

		$("#rMenu ul").show();
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
			var level = zTree.getSelectedNodes()[0].level;
			loadCreateMenuPage(parentId,level);
		}
	}

	function editTreeNode() {
		hideRMenu();
		if (zTree.getSelectedNodes()[0]) {
			var id = zTree.getSelectedNodes()[0].id;
			loadEditMenuPage(id);
		}
	}

	//删除节点
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			var id = nodes[0].id;
			var msg = "确定执行此次操作？";
			$.confirm(msg, function() {
				$.post("${pageContext.request.contextPath}/generalcode/knowledgetree/treeNode/deleted?id="+id, {
					"_method" : "delete"
				}, function(data, status) {
					if ("success" === status) {
						data = eval("(" + data + ")");
						if ("success" === data.info) {
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
		requestData.treeId = ${treeId};
		$.get("${ctp}/generalcode/knowledgetree/tree/json",requestData,function(value, status) {
			var data = eval("(" + value + ")");
			$.each(data,function(index, valueTmp) {
				valueTmp.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
			});
			$.fn.zTree.init($("#treeDept"), setting, data);
			zTree = $.fn.zTree.getZTreeObj("treeDept");
		});
	}

	// 	加载创建组织机构对话框（子机构）
	function loadCreateMenuPage(parentId,level) {
		level=level+2;
		$.initWinOnTopFromLeft('创建节点','${pageContext.request.contextPath}/generalcode/knowledgetree/tree/create?caller=tree&treeId=${treeId}&parentId='
				+ parentId + '&level='+level, '550', '250');
	}
	// 	加载创建组织机构对话框(顶级机构)
	function loadCreateTopMenuPage(){
		if(!${isCanAdd}){
			$.alert("顶级节点只能有一个，不能再创建");
			return;
		}
		$.initWinOnTopFromLeft('创建顶级节点','${pageContext.request.contextPath}/generalcode/knowledgetree/tree/create?treeId=${treeId}&level=1&parentId=',
				'550', '250');
	}
	//  加载编辑组织机构对话框
	function loadEditMenuPage(id,level) {
		level = level + 1;
		$.initWinOnTopFromLeft('编辑部门',
				'${pageContext.request.contextPath}/generalcode/knowledgetree/treeNode/update?caller=tree&id='
				+ id, '550', '250');
	}
	
	function showAddBtn(){
		if(!${isCanAdd}){
			$("#addBtn").hide();
		}else{
			$("#addBtn").show();
		}
	}
	showAddBtn();
	$.getDeptData = function($requestData, handller) {
		$.get("${ctp}/generalcode/knowledgetree/treeList/json", $requestData, function(value,status) {
			if ("success" === status) {
				data = eval("(" + value + ")");
				handller(data);
			}
		});
	}

</script>
</html>