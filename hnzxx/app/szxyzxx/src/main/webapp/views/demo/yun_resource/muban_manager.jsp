<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/yunzy.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.exedit.js"></script>
<title>母版管理</title>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.up {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.down {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -64px; vertical-align:top; *vertical-align:middle}
	</style>
<SCRIPT type="text/javascript">
	var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			showIcon: false,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onNodeCreated: zTreeOnNodeCreated,
			onRemove: onRemove,
			onRename: onRename
		}
	};

// 	var zNodes =[
// 		{ id:0, pId:4, name:"模板一", open:true},	
// 		{ id:01, pId:0, name:"父节点 1", open:true},
// 		{ id:011, pId:1, name:"叶子节点 1-1"},
// 		{ id:012, pId:1, name:"叶子节点 1-2"},
// 		{ id:013, pId:1, name:"叶子节点 1-3"},
// 		{ id:02, pId:0, name:"父节点 2", open:true},
// 		{ id:021, pId:2, name:"叶子节点 2-1"},
// 		{ id:022, pId:2, name:"叶子节点 2-2"},
// 		{ id:023, pId:2, name:"叶子节点 2-3"},
// 		{ id:03, pId:0, name:"父节点 3", open:true},
// 		{ id:031, pId:3, name:"叶子节点 3-1"},
// 		{ id:032, pId:3, name:"叶子节点 3-2"},
// 		{ id:033, pId:3, name:"叶子节点 3-3"}
// 	];
	var zNodes =[
	     		{ id:0, pId:0, name:"新建模板", open:true},	
	     	];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	//树节点增加的时候触发
	function zTreeOnNodeCreated(event, treeId, treeNode) {
		//对节点进行编辑
		if(treeNode.name!=""&&treeNode.name!="新建模板"){
			return false;
		}
		beforeEditName(treeId, treeNode);
	};
	
	//对节点进行编辑的时候触发
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"+i);
		zTree.selectNode(treeNode);
		//
		if(treeNode.name!=""){
			return false;
		}
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	}
	//开始删除节点时触发
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"+i);
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	
	//删除节点后触发
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	//开始编辑节点的时候触发 
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			return false;
		}
		return true;
	}
	//编辑结束后触发
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"+i);
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:""});
			beforeEditName(treeId, treeNode);
			return false;
		});
		if (treeNode.id != 0) {
// 		下移按钮
		if (treeNode.editNameFlag || $("#downBtn_"+treeNode.tId).length>0) return;
		var upStr = "<span class='button down' id='downBtn_" + treeNode.tId
		+ "' title='down node' onfocus='this.blur();'></span>";
		sObj.after(upStr);
		var btn_2 = $("#downBtn_"+treeNode.tId);
		if (btn_2) btn_2.bind("click", function(){
			var treeObj =  $.fn.zTree.getZTreeObj("treeDemo"+i);
			var sNodes = treeObj.getSelectedNodes();
			var node
			if (sNodes.length > 0) {
				node = sNodes[0].getNextNode();
			}
			if(node!=null){
				alert("下移")
			treeObj.moveNode(node,treeNode, "next");
			}
		});
// 		上移按钮
		if (treeNode.editNameFlag || $("#upBtn_"+treeNode.tId).length>0) return;
		var upStr = "<span class='button up' id='upBtn_" + treeNode.tId
		+ "' title='up node' onfocus='this.blur();'></span>";
		sObj.after(upStr);
		var btn_1 = $("#upBtn_"+treeNode.tId);
		if (btn_1) btn_1.bind("click", function(){
			var treeObj =  $.fn.zTree.getZTreeObj("treeDemo"+i);
			var sNodes = treeObj.getSelectedNodes();
			var node
			if (sNodes.length > 0) {
				node = sNodes[0].getPreNode();
			}
			if(node!=null){
				alert("上移");
			treeObj.moveNode(node,treeNode, "prev");
			}
		});
		}
	};
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
		$("#upBtn_"+treeNode.tId).unbind().remove();
		$("#downBtn_"+treeNode.tId).unbind().remove();
	};
	
	function selectAll() {
		
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"+i);
		zTree.setting.edit.editNameSelectAll =  $("#selectAll"+i).attr("checked");
	}
	
	$(document).ready(function(){
		/* $.fn.zTree.init($(".ztree"), setting, zNodes);
		$("#selectAll").bind("click", selectAll); */
	});
	</SCRIPT>
	<script type="text/javascript">
	$(function(){
		$(".add_mb").click(function(){
			i=$(".ztree").length;
			$(".muban").append("<ul id='treeDemo"+i+"' class='ztree'></ul>")
			$.fn.zTree.init($("#treeDemo"+i), setting, zNodes);
			$("#selectAll"+i).bind("click", selectAll);
		})
	})
	</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="母版管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12 white">
			<div class="sjck">
			<Span>书籍查看</Span>
				<div class="mulu_btn">
					<ul>
						<li><a href="javascript:void(0)">图书列表</a></li>
						<li><a href="javascript:void(0)">基本信息</a></li>
						<li><a href="javascript:void(0)">教材目录</a></li>
						<li class="on"><a href="javascript:void(0)">导学案结构</a></li>
					</ul>
				</div>
			</div>
			<div class="new_mb">
				<a href="javascript:void(0)" class="add_mb">新建模板</a>
				<div class="muban">
<!-- 					<div class="mb_div"> -->
<!-- 						 <ul id="treeDemo" class="ztree"></ul>  -->
<!-- 					</div> -->
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>