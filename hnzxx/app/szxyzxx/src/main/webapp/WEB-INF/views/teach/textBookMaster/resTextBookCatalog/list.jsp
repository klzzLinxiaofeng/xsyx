<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/tree_new.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.exedit.js"></script>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.up {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -48px; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.down {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -64px; vertical-align:top; *vertical-align:middle}
.ztree li{min-height:18px;}
.ztree li a span{width:120px;}
.ztree{clear:both;}
</style>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<div class="catalog_list">
<ul><li><b class="title">${parentCatalog.name}</b></li></ul>
<ul id="catalogtree" class="ztree"></ul>
</div>
<script type="text/javascript">
	var isNew = false;
	$(function(){
		$(".catalog_list ul li").on("click",".close_1 b",function(){
			$(this).parent().removeClass("close_1").addClass("open_1");
			$(this).parent().next("ul").hide();
		});
		$(".catalog_list ul li").on("click",".open_1 b",function(){
			$(this).parent().removeClass("open_1").addClass("close_1");
			$(this).parent().next("ul").show();
		});
	}); 
	
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
				removeTitle :'移除',
				renameTitle : "改名"
				/*showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn*/
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
				onRemove: zTreeOnRemove,
				onRename: onRename
			}
		};
	var log, className = "dark";
	
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	//创建节点时触发
	function zTreeOnNodeCreated(event, treeId, treeNode) {
		//创建节点并使节点进入编辑状态
		if(treeNode.name=="") {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.selectNode(treeNode);
			zTree.editName(treeNode);
		}
	};
	
	//在节点编辑前触发
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		zTree.editName(treeNode);
		return true;
	}
	
	//删除节点前触发
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
/* 		$.confirm("确定执行此次操作？", function() {
			return true;
		}); */
		return confirm("会将下面的子节点也一起删除，确认删除吗？");
	}
	
	//删除节点后触发
	function zTreeOnRemove(event, treeId, treeNode) {
		$.ajax({  
			type:"DELETE",
			async:false,
			url:"${ctp}/teach/textBookMaster/resTextBookCatalog/"+treeNode.id,
			data:{},
			success:function(data) {
			}  
		});
	}
	
	//在节点编辑的时候触发
	function beforeRename(treeId, treeNode, newName, isCancel) {
		var treeObj =  $.fn.zTree.getZTreeObj(treeId);
		className = (className === "dark" ? "":"dark");
		if (newName.length == 0) {
			treeObj.removeNode(treeNode);
			return true;
		}
		return true;
	}
	
	//在编辑结束后触发
	function onRename(e, treeId, treeNode, isCancel) {
		if(treeNode.name==null || treeNode.name=="") {
			$.ajax({  
				type:"DELETE",
				async:false,
				url:"${ctp}/teach/textBookMaster/resTextBookCatalog/"+treeNode.id,
				data:{},
				success:function(data) {
				}  
			});
			return;
		}
		//节点新建
		if(treeNode.id=="") {
			var zTree = $.fn.zTree.getZTreeObj("catalogtree");
			var parentNode = zTree.getNodeByTId(treeNode.pId);
			//添加子节点并返回子节点id
			$.ajax({  
				type:"post",
				async:false,
				url:"${ctp}/teach/textBookMaster/resTextBookCatalog/creator",  
				data:{'name':treeNode.name,'level':1,'parentId':"${parentCatalog.id}", "testBookId":"${textBookId}"},
				success:function(data) {
					//解析返回的json数据
					var json = JSON.parse(data);
					//创建树的子节点
					zTree.addNodes(null, {id:json.id, pId:"${parentCatalog.id}", name:treeNode.name});
					zTree.removeNode(treeNode);
				}
			});
		} else {
			//修改节点信息
			$.ajax({ 
				type:"POST",
				async:false,
				url:"${ctp}/teach/textBookMaster/resTextBookCatalog/modify",
				data:{"id":treeNode.id, "name":treeNode.name,"testBookId":"${textBookId}"},
				success:function(data) {
				}
			});
		}
	}
	
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='添加' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			//添加子节点并返回子节点id
			$.ajax({  
				type:"post",
				async:false,
				url:"${ctp}/teach/textBookMaster/resTextBookCatalog/creator",  
				data:{'name':"",'level':treeNode.level+2,'parentId':treeNode.id, "testBookId":"${textBookId}"},
				success:function(data) {
					//解析返回的json数据
					var json = JSON.parse(data);
					//创建树的子节点
					zTree.addNodes(treeNode, {id:json.id, pId:treeNode.id, name:""});
				}
			});
			return false;
		});
		
		if (treeNode.id != 0) {
			//下移按钮
			if (treeNode.editNameFlag || $("#downBtn_"+treeNode.tId).length>0) return;
			var upStr = "<span class='button down' id='downBtn_" + treeNode.tId
			+ "' title='下移' onfocus='this.blur();'></span>";
			sObj.after(upStr);
			var btn_2 = $("#downBtn_"+treeNode.tId);
			if (btn_2) btn_2.bind("click", function(){
				var treeObj =  $.fn.zTree.getZTreeObj(treeId);
				var sNodes = treeObj.getSelectedNodes();
				var node
				if (sNodes.length > 0) {
					node = sNodes[0].getNextNode();
				}
				if(node!=null){
					var json = "[{'id':'"+node.id+"','name':'"+node.name+"'},{'id':'"+treeNode.id+"','name':'"+treeNode.name+"'}]";
					console.log(json);
					treeObj.moveNode(node,treeNode, "next");
					//互换节点id开始
					var tempTreeId = node.id;
					node.id=treeNode.id;
					treeNode.id=tempTreeId;
					treeObj.updateNode(node);
					treeObj.updateNode(treeNode);
					//互换节点id结束
					moveNode(json);
				}
			});
	 		//上移按钮
			if (treeNode.editNameFlag || $("#upBtn_"+treeNode.tId).length>0) return;
			var upStr = "<span class='button up' id='upBtn_" + treeNode.tId
			+ "' title='上移' onfocus='this.blur();'></span>";
			sObj.after(upStr);
			var btn_1 = $("#upBtn_"+treeNode.tId);
			if (btn_1) btn_1.bind("click", function(){
				var treeObj =  $.fn.zTree.getZTreeObj(treeId);
				var sNodes = treeObj.getSelectedNodes();
				var node
				if (sNodes.length > 0) {
					node = sNodes[0].getPreNode();
				}
				if(node!=null){
					var json = "[{'id':'"+node.id+"','name':'"+node.name+"'},{'id':'"+treeNode.id+"','name':'"+treeNode.name+"'}]";
					console.log(json);
					treeObj.moveNode(node,treeNode, "prev");
					//互换节点id开始
					var tempTreeId = node.id;
					node.id=treeNode.id;
					treeNode.id=tempTreeId;
					treeObj.updateNode(node);
					treeObj.updateNode(treeNode);
					//互换节点id结束
					moveNode(json);
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
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	function moveNode(json) {
		$.ajax({  
			type:"post",
			async:false,
			url:"${ctp}/teach/textBookMaster/resTextBookCatalog/bantch/editor",  
			data:{'data':json},
			success:function(data) {
			}
		});
	}
	// 	新增顶级节点
	function add_Node(){
		var zTree = $.fn.zTree.getZTreeObj("catalogtree");
		var paramNode = zTree.getNodeByParam("name","", null)
		if(paramNode!=null) {
			return false;
		}
		zTree.addNodes(null, {id:"", pId:"${parentCatalog.id}", name:""});
	}
	$(document).ready(function(){
		//获取目录数据
		var zTreeList = JSON.parse('${zTreeList}');
		var treeObj = $.fn.zTree.init($("#catalogtree"), setting, zTreeList);
		treeObj.expandAll(true); 
	});
</script>