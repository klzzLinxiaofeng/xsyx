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
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/tree_new.css" type="text/css">
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
	var i=0;
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
		return confirm("会将下面的子节点也一起删除，确认删除吗？");
	}

	//删除节点后触发
	function zTreeOnRemove(event, treeId, treeNode) {
		$.ajax({  
			type:"DELETE",
			async:false,
			url:"${ctp}/teach/textBookMaster/catalogmodel/"+treeNode.id,
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
			$.ajax({  
				type:"DELETE",
				async:false,
				url:"${ctp}/teach/textBookMaster/resTextBookCatalog/"+treeNode.id,
				data:{},
				success:function(data) {
					treeObj.removeNode(treeNode);
				}  
			});
			return false;
		}
		return true;
	}
	
	//在编辑结束后触发
	function onRename(e, treeId, treeNode, isCancel) {
		//修改节点信息
		$.ajax({  
			type:"POST",
			async:false,
			url:"${ctp}/teach/textBookMaster/catalogmodel/modify",
			data:{"id":treeNode.id, "name":treeNode.name},
			success:function(data) {
			}
		});
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
				url:"${ctp}/teach/textBookMaster/catalogmodel/creator",  
				data:{'name':"",'level':treeNode.level+1,'parentId':treeNode.id},
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
					moveNode(json);
					//互换节点id结束
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
			url:"${ctp}/teach/textBookMaster/catalogmodel/bantch/editor",  
			data:{'data':json},
			success:function(data) {
			}
		});
	}
	
	//教材基本信息显示
	function loadViewerPage(id) {
		var url = "${ctp}/teach/textBookMaster/resTextBook/viewer?id=";
		url = url+id;
		$("#loadViewerPage").attr("href", url);
	}
	
	function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/resTextBookCatalog/resCatalogList?resTextBookId=";
		url = url+id;
		$("#loadViewerCatalogPage").attr("href", url);
	}
	 
	function loadViewerModelPage(id) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/model?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
	}
	
</SCRIPT>
<script type="text/javascript">
	$(function(){
		//新增模板事件
		$(".add_mb").click(function(){
			//Ajax请求添加顶级结点
			$.ajax({  
				type:"post",
				async:false,
				url:"${ctp}/teach/textBookMaster/catalogmodel/creator",  
				data:{'name':"",'level':0,'parentId':0},
				success:function(data) {
					//解析返回的json数据
					var json = JSON.parse(data);
					//设置树的信息，把返回的id赋值给树的id
					var zNodes =[{ id:json.id, pId:0, name:"", open:true},];
					$(".muban").append("<ul id='treeDemo"+json.id+"' class='ztree'></ul>")
					//初始化树
					var ztreeObj = $.fn.zTree.init($("#treeDemo"+json.id), setting, zNodes);
					ztreeObj.expandAll(true); 
				}
			});
		})
		
		//获取属于当前学校的母板
		var treeList = '${treeList}';
		var json = JSON.parse(treeList);
		for(var i=0; i<json.length; i++) {
			$(".muban").append("<ul id='tree"+i+"' class='ztree'></ul>")
			var ztreeObj = $.fn.zTree.init($("#tree"+i), setting, json[i]);
			ztreeObj.expandAll(true);
		}
		
// 		展开节点，关闭节点
		$("#expandAllBtn").bind("click", {type:"expandAll"}, function(){
			for(var i=0; i<$(".ztree").length; i++) {
			var zTree = $.fn.zTree.getZTreeObj("tree"+i);
			zTree.expandAll(true);
			}
			});
		$("#collapseAllBtn").bind("click", {type:"collapseAllBtn"}, function(){
			for(var i=0; i<$(".ztree").length; i++) {
			var zTree = $.fn.zTree.getZTreeObj("tree"+i);
			zTree.expandAll(false);
			}
			});
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
						<li><a href="${ctp}/teach/textBookMaster/resTextBook/index" >图书列表</a></li>
						<li><a href="" id="loadViewerPage"  onclick="loadViewerPage('${textBookId}');">基本信息</a></li>
						<li><a href="" id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${textBookId}');" class="on">教材目录</a></li>
						<li class="on"><a href="" id="loadViewerModelPage"  onclick="loadViewerModelPage('${textBookId}');">目录管理</a></li>
					</ul>
				</div>
			</div>
			<div class="new_mb">
				<div class="mb_top">
					<a href="javascript:void(0)" class="add_mb">新建模板</a>
					<a href="javascript:void(0)" class="close_jd" id="expandAllBtn" onclick="return false;"><i class="fa fa-chevron-down"></i>展开节点</a>
					<a href="javascript:void(0)" class="open_jd" id="collapseAllBtn" onclick="return false;"><i class="fa  fa-times"></i>关闭节点</a>
					<div class="mb_btn">
						<a class="btn  btn-success" href="${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId=${textBookId}">结构管理</a>
						<a class="btn btn-primary"  href="${ctp}/teach/textBookMaster/catalogmodel/model?textBookId=${textBookId}">模板管理</a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="muban">
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>