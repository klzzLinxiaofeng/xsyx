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
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/tree_new.css" type="text/css">
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
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "", "N": "" }
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			removeTitle :'移除',
			renameTitle : "改名"
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
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		zTree.editName(treeNode);
		return false;
	}
	
	//删除节点前触发
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		return confirm("会将下面的子节点也一起删除，确认删除吗？");
	}

	//删除节点后触发
	function onRemove(event, treeId, treeNode) {
		$.ajax({  
			type:"DELETE",
			async:false,
			url:"${ctp}/teach/textBookMaster/resTextBookCatalog/"+treeNode.id,
			data:{},
			success:function(data) {
			}  
		});
	}
	
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
			url:"${ctp}/teach/textBookMaster/resTextBookCatalog/modify",
			data:{"id":treeNode.id, "name":treeNode.name},
			success:function(data) {
			}
		});
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
				data:{'name':"",'level':treeNode.level+1,'parentId':treeNode.id, "testBookId":"${textBookId}"},
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
	//取消选中状态时触发
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
		$("#upBtn_"+treeNode.tId).unbind().remove();
		$("#downBtn_"+treeNode.tId).unbind().remove();
	};
	
	function selectAll() {
 		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.setting.edit.editNameSelectAll =  $("#selectAll"+i).attr("checked");
	}
	
	//为目录节点添加模版
	function addChildren(parentid, node, zTree) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/textCatalog?parentId="+parentid+"&nodeId="+node.id;
		$.ajax({  
			type:"PUT",
			url:url,  
			data:{},
			success:function(data) {
				//解析返回的json数据
				var json = JSON.parse(data);
				for(var i=0; i<json.length; i++) {
					//解析json数据
					var zNodes = JSON.parse(json[i]);
					//为目录添加模版
					zTree.addNodes(node, zNodes);
					//把节点的状态设成不选中
					node.checked=false;
					//更新节点状态
					zTree.updateNode(node, false);
				}
			}
		});
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
		var url = "${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
	}
	
	function structrueManager(id) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
	}
	</SCRIPT>
	<script type="text/javascript">
	$(function(){
		//初始化模版
		var motherList = JSON.parse('${motherList}');
		var motherHtml = "";
		for(var i=0; i<motherList.length; i++) {
			if(motherList[i].parentId==0) {
				motherHtml+="<a href=\"javascript:void(0)\" parentid=\""+motherList[i].id+"\">"+motherList[i].name+"</a>";
			}
		}
		//填充模版下拉
		$(".mb_list").html(motherHtml);
	
		//获取目录数据
		var zTreeList = JSON.parse('${zTreeList}');
		//获取最顶级目录
		var parentCatalog = JSON.parse('${parentCatalog}');
		var catalogListHtml = "<ul><li><input type=\"checkbox\" class=\"quanxuan\">全选<b class=\"title\">"+parentCatalog.name+"</b></li>"+
							  "<ul id='tree"+parentCatalog.id+"' class='ztree'></ul></ul>";
		//填充目录树所需的class
		$(".catalog_list").html(catalogListHtml);
		//生成目录树
		var zTree = $.fn.zTree.init($("#tree"+parentCatalog.id), setting, zTreeList);
// 		目录展开
		zTree.expandAll(true);
		
		//目录展开关闭
		$(".catalog_list ul li").on("click",".close_1 b",function(){
			$(this).parent().removeClass("close_1").addClass("open_1");
			$(this).parent().parent().children(".mulu").hide();
		});
		$(".catalog_list ul li").on("click",".open_1 b",function(){
			$(this).parent().removeClass("open_1").addClass("close_1");
			$(this).parent().parent().children(".mulu").show();
		});
		//使用模板
		$(".use_mb").click(function(){
			$(".mb_list").toggle();
		});
		
		//选择模版触发事件
		$(".mb_top .mb_list a").click(function(){
			$(".mb_top .mb_list a").removeClass("on");
			$(this).addClass("on");
			$(".mb_list").hide();
			//获取目录的id
			var parentid = $(this).attr("parentid");
			//获取所有选中的节点
			var nodes = zTree.getCheckedNodes();
			//遍历选中的节点
			for (var i = 0; i < nodes.length; i++) {
				//为节点添加模版
				addChildren(parentid, nodes[i], zTree);
			}
			$.success("应用"+$(this).text()+"成功!");	
		});
		
		//全选触发事件
		$(".quanxuan").click(function(){
			//选中状态
			if($(this ).is(':checked' )){
                $( ".catalog_list ul li input[type='checkbox']").prop("checked" , "checked");
                //把所有节点置为选中状态
                zTree.checkAllNodes(true);
           } else{
        	   //取消选中
                $( ".catalog_list ul li input[type='checkbox']").removeAttr("checked");
        	   //把所有节点置为取消选中状态
                zTree.checkAllNodes(false);
           }

		});
// 		展开节点，关闭节点
		$("#expandAllBtn").bind("click", {type:"expandAll"}, function(){zTree.expandAll(true)});
		$("#collapseAllBtn").bind("click", {type:"collapseAll"}, function(){zTree.expandAll(false)});
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
						<li><a href="${ctp}/teach/textBookMaster/resTextBook/index">图书列表</a></li>
						<li><a href="" id="loadViewerPage"  onclick="loadViewerPage('${textBookId}');">基本信息</a></li>
						<li><a href="" id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${textBookId}');" class="on">教材目录</a></li>
						<li class="on"><a href="" id="loadViewerModelPage"  onclick="loadViewerModelPage('${textBookId}');">目录管理</a></li>
					</ul>
				</div>
			</div>
			<div class="new_mb">
				<div class="mb_top">
					<a href="javascript:void(0)" class="use_mb">使用模板</a>
					<a href="javascript:void(0)" class="close_jd" id="expandAllBtn" onclick="return false;"><i class="fa fa-chevron-down"></i>展开节点</a>
					<a href="javascript:void(0)" class="open_jd" id="collapseAllBtn" onclick="return false;"><i class="fa  fa-times"></i>关闭节点</a>
					<div class="mb_list" style="display:none">
					</div>
					<div class="mb_btn">
						<a class="btn  btn-success" href="${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId=${textBookId}">结构管理</a>
						<a class="btn btn-primary"  href="${ctp}/teach/textBookMaster/catalogmodel/model?textBookId=${textBookId}">模板管理</a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="content-widgets">
						<div class="widget-container">
							<div class="catalog_list">
							</div>
						</div>
					</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>