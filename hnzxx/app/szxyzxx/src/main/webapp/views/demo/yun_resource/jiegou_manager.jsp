<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/yunzy.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/zTree3.5/js/jquery.ztree.exedit.js"></script>
<title>结构管理</title>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.up {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.down {margin-left:2px; margin-right: -1px;top:0; background-position:-144px -64px; vertical-align:top; *vertical-align:middle}
.ztree li{min-height:18px;}
.ztree li a span{width:120px;}
.ztree{clear:both;}
	</style>
<SCRIPT type="text/javascript">
var i=0,e=0;
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
			onRemove: onRemove,
			onRename: onRename
		}
	};

	var zNodes =[
		{ id:0, pId:4, name:"模板一", open:true},	
		{ id:01, pId:0, name:"父节点 1", open:true},
		{ id:011, pId:1, name:"叶子节点 1-1"},
		{ id:012, pId:1, name:"叶子节点 1-2"},
		{ id:013, pId:1, name:"叶子节点 1-3"},
		{ id:02, pId:0, name:"父节点 2", open:true},
		{ id:021, pId:2, name:"叶子节点 2-1"},
		{ id:022, pId:2, name:"叶子节点 2-2"},
		{ id:023, pId:2, name:"叶子节点 2-3"},
		{ id:03, pId:0, name:"父节点 3", open:true},
		{ id:031, pId:3, name:"叶子节点 3-1"},
		{ id:032, pId:3, name:"叶子节点 3-2"},
		{ id:033, pId:3, name:"叶子节点 3-3"}
	];
// 	var zNodes =[
// 	     		{ id:0, pId:0, name:"新建模板", open:true},	
// 	     	];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function zTreeOnNodeCreated(event, treeId, treeNode) {
		if(treeNode.name!=""&&treeNode.name!="新建模板"){
			return false;
		}
		beforeEditName(treeId, treeNode);
	};
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			/*setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				
			}, 0);*/
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	/*function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}*/
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
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
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:""});
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
			var treeObj =  $.fn.zTree.getZTreeObj(treeId);
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
			var treeObj =  $.fn.zTree.getZTreeObj(treeId);
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
		
		var zTree = $.fn.zTree.getZTreeObj(treeId);
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
		
// 		目录展开关闭
		$(".catalog_list ul li").on("click",".close_1 b",function(){
			$(this).parent().removeClass("close_1").addClass("open_1");
			$(this).parent().parent().children(".mulu").hide();
		});
		$(".catalog_list ul li").on("click",".open_1 b",function(){
			$(this).parent().removeClass("open_1").addClass("close_1");
			$(this).parent().parent().children(".mulu").show();
		});
// 		使用模板
		$(".use_mb").click(function(){
			$(".mb_list").toggle();
		});
		$(".mb_top .mb_list a").click(function(){
			$(".mb_top .mb_list a").removeClass("on");
			$(this).addClass("on");
			$(".mb_list").hide();
			var j=$(".catalog_list ul li p input[type='checkbox']:checked").length;
			e=$(".ztree").length;
			for(i=e;i<j+e;i++){
				$(".catalog_list ul li p input[type='checkbox']:checked").eq(i-e).parent().parent().children(".ztree").remove()
				if($(".catalog_list ul li p input[type='checkbox']:checked").eq(i-e).parent().next().html=="<span></span>"){
					$(".catalog_list ul li p input[type='checkbox']:checked").eq(i-e).parent().parent().append("<ul id='treeDemo"+i+"' class='ztree'></ul>")
				}else{
					$(".catalog_list ul li p input[type='checkbox']:checked").eq(i-e).parent().after("<ul id='treeDemo"+i+"' class='ztree'></ul>")
				}
				$.fn.zTree.init($("#treeDemo"+i), setting, zNodes);
				$("#selectAll"+i).bind("click", selectAll);
			}
		});
		$(".quanxuan").click(function(){
			if($(this ).is(':checked' )){
                $( ".catalog_list ul li input[type='checkbox']").prop("checked" , "checked");
           } else{
                $( ".catalog_list ul li input[type='checkbox']").removeAttr("checked");
           }

		})
	})
	</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="结构管理" name="title" />
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
				<div class="mb_top">
					<a href="javascript:void(0)" class="use_mb">使用模板</a>
					<div class="mb_list" style="display:none">
						<a href="javascript:void(0)" >模板1</a>
						<a href="javascript:void(0)">模板2</a>
						<a href="javascript:void(0)">模板3</a>
					</div>
					<div class="mb_btn">
						<button class="btn  btn-success">结构管理</button>
						<button class="btn btn-primary">模板管理</button>
					</div>
					<div class="clear"></div>
				</div>
				<div class="content-widgets">
						<div class="widget-container">
							<div class="catalog_list">
								<ul>
									<li><input type="checkbox" class="quanxuan">全选<b class="title">三年级英语上册</b></li>
									<li>
										 <p class="open_1"><input type="checkbox"><b>Unit 1 Hello!</b></p>
										<ul style="display:none" class="mulu">
											<li><p class="have"><input type="checkbox"><b>Part A</b></p><span></span></li>
											<li><p class="have"><input type="checkbox"><b>Part A</b></p><span></span></li>
											<li><p class="have"><input type="checkbox"><b>Part A</b></p><span></span></li>
											<li><p class="have"><input type="checkbox"><b>Part A</b></p><span></span></li>
											<li><p class="have"><input type="checkbox"><b>Part A</b></p><span></span></li>
										</ul>
								</ul>
							</div>
							
						</div>
					</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>