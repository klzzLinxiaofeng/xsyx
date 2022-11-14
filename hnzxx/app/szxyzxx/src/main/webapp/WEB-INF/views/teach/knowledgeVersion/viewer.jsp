<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%-- <%@include file="/views/embedded/knowledgeCatalog.jsp" %> --%>
<style>

</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="知识点" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="ts_top">
						${knowledgeVersion.name}——知识点管理
						<p>
						<a href="${ctp}/teach/knowledgeVersion/index" >版本列表</a>
						<a href="" id="loadViewerCatalogPage" class="on"  onclick="loadViewerCatalogPage('${id}');">知识点目录</a>
						</p>
					</div>
					<div class="widget-head">
						<div class="catalog">
							<div class="dl">
								<a id="DownloadModel" class="a3" href="${ctp}/template/knowledgeCatalog.info.xls"><i class="fa fa-download"></i>下载模板</a>
								<a id="upLoadExcel" class="a3" href="" onclick="upLoadExcel('${knowledgeVersion.code}');"><i class="fa fa-plus"></i>导入excel</a>						
							</div>
						</div>
					</div>
					<div class="class_detail" id="catalog" style="float:right;padding:0 28px;position:relative;top:-56px;">
						<a href="javascript:loadCreateTopMenuPage('${knowledgeVersion.code}')" id="" class="btn btn-green" style="float:right"><i class="fa fa-edit"></i>创建一级知识点</a>
<!-- 						<a href="javascript:getCatalogId()" id="" class="btn btn-green" style="float:right"><i class="fa fa-edit"></i>获取知识点id</a> -->
					</div>
					<div class="row-fluid">
						<div class="widget-container" style="background-color:#fff">
							<ul id="treeZylb" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
<div id="rMenu" class="rMenu">
		<div class="content-widgets light-gray">
			<div class="widget-container" >
				<form id="menu_form" class="form-horizontal" novalidate="novalidate">
					<div class="control-group">
						<label class="control-label">知识点编号</label>
						<div class="controls" id="module_bh">zzjg</div>
					</div>
					<div class="control-group">
						<label class="control-label">名称</label>
						<div class="controls" id="module_mc">组织机构</div>
					</div>
					<div class="control-group">
						<label class="control-label">排序号</label>
						<div class="controls" id="module_pxh">1</div>
					</div>
					<div class="control-group">
						<label class="control-label">父知识点</label>
						<div class="controls" id="module_fcd">无</div>
					</div>
					<!-- 					<div class="control-group"> -->
					<!-- 						<label class="control-label">启用</label> -->
					<!-- 						<div class="controls" id="module_qy">是</div> -->
					<!-- 					</div> -->
				</form>
			</div>
		</div>
		<ul>
			<li id="m_add" onclick="addTreeNode();">添加子知识点</li>
			<li id="m_edit" onclick="editTreeNode();">修改</li>
			<li id="m_del" onclick="removeTreeNode();">删除</li>
		</ul>
	</div>
</body>
<script type="text/javascript">

	$(function() {
// 		var $requestData = {};
// 		$requestData.stageCode = 2;
// 		$requestData.subjectCode = 16;
// 		$requestData.versionCode = 3;
// 		$requestData.knowledgeVersionCode = 135849;
// 		$requestData.catalogId = "77,78,79,80,81";
// 		createKnowledgeCatalogV1("catalog",$requestData);
	});
// 	function getCatalogId(){
// 		getAllKnowledgeCatalogId(function(data){
// 			alert(data);
// 		});
// 		alert(getKnowledgeCatalogId());
// 	}
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
		var param = "parentId=" + treeNode.id + "&knowledgeVersionCode=${knowledgeVersion.code}";
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
		requestData.Id = treeNode.id;
		$.getModuleData(requestData, function(data) {
			$("#rMenu #module_bh").html(data[0].id);
			$("#rMenu #module_mc").html(data[0].name);
			$("#rMenu #module_lj").html(data[0].accessUrl);
			$("#rMenu #module_pxh").html(data[0].sort);
			var parentId = data[0].parentId;
			if (parentId != null && parentId != "0" && parentId != "undefind") {
				requestData.Id = parentId;
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
		initModuleTree();
		rMenu = $("#rMenu");
	});

	function initModuleTree() {
		var requestData = {
				"knowledgeVersionCode" : "${knowledgeVersion.code}"
		};
		$.get("${ctp}/teach/catalog/tree/json",
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
	function loadCreateMenuPage(parentId) {
		$.initWinOnTopFromTop('创建菜单',
				'${pageContext.request.contextPath}/teach/catalog/creator?caller=tree&parentId=' + parentId,"650","320");
	}
	//导入
	function upLoadExcel(code) {
			var url = "${ctp}/teach/catalog/toUpload?knowledgeVersionCode=";
			 url = url + code;
			 $.initWinOnTopFromLeft('导入', url, '800', '400');
	}
	// 	加载创建菜单对话框
	function loadCreateTopMenuPage(code) {
		$.initWinOnTopFromTop('创建菜单',
						'${pageContext.request.contextPath}/teach/catalog/creator?parentId=0&code=' + code,"650","320");
	}
	//  加载编辑菜单对话框
	function loadEditMenuPage(id) {
		$.initWinOnTopFromTop('编辑菜单', '${pageContext.request.contextPath}/teach/catalog/editor?caller=tree&id=' + id ,"650","320");
	}
	
	$.getModuleData = function($requestData, handller) {
		$.get("${ctp}/teach/catalog/list/json", $requestData, function(value,
				status) {
			if ("success" === status) {
				data = eval("(" + value + ")");
				handller(data);
			}
		});
	}
</script>
</html>