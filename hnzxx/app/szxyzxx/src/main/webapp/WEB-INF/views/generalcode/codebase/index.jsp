<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/dmgl.css" rel="stylesheet">
<title>基础代码管理系统</title>
<style>
</style>
</head>
<body style="background-color:#fff">
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon"/>
		<jsp:param value="代码管理" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="dmgl">
		<div class="dm_left">
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码规范</p>
					<p class="btn-right">
						<a href="javasript:void(0)" onclick="createFrameWork();"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)" id="edF" onclick="editFrameWork(2);">管理</a>
					</p>
				</div>
				<div class="dm_all">
					<select id="frameworkSelect" name="frameworkSelect" onchange="loadTable($(this).val())" style="margin-bottom:0"><!-- <option>项目管理</option> -->
						<c:forEach items="${frameworkList}" var ="framework">
							<option value="${framework.id}">${framework.name}${framework.code}</option>
						</c:forEach>
						
					</select>
				</div>
			</div>
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码表</p>
					<p class="btn-right">
						<a href="javasript:void(0)" id="ct" onclick="createTable();"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)" id="et" onclick="showTable();">管理</a>
					</p>
				</div>
				<div id="tableSelect" class="dm_all dmb">
					<!-- <a href="javascript:void(0)">A</a>
					<a href="javascript:void(0)">B</a>
					<a href="javascript:void(0)">C</a> -->
					<c:forEach items="${tableList}" var="table">
						<a href="javascript:void(0)" style="display:none" data-type="${table.frameworkId }" onclick="loadItem(${table.id })" >${table.name}(${table.code})</a>
					</c:forEach>
					
				</div>
			</div>
		</div>
		<div class="dm_right">
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码项</p>
					<p class="btn-right">
						<a href="javasript:void(0)" id="ci" onclick="createItem(-1);"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)" id="ei" onclick="edItem();">编辑</a>
						<a href="javasript:void(0)" class="turn_up">上移</a>
						<a href="javasript:void(0)" class="turn_down">下移</a>
					</p>
				</div>
				<div class="dm_all dm_table white">
					<table class="table " id="itemTable">
						<thead>
							<tr><th>次序</th><th>名称</th><th>代码</th></tr>
						</thead>
						<tbody>
							<!-- <tr><td>1</td><td>1</td><td>1</td></tr>
							<tr><td>2</td><td>2</td><td>2</td></tr>
							<tr><td>3</td><td>3</td><td>3</td></tr> -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
<script>
$(function() { 
    //上移 
    var $up = $(".turn_up");
    $up.click(function() { 
        var $tr = $(".blue_1"); 
        if ($tr.index() != 0) { 
 			var td_first=$tr.children("td:first-child").text();
 			$tr.children("td:first-child").text($tr.prev().children("td:first-child").text());
 			$tr.prev().children("td:first-child").text(td_first)
            $tr.fadeOut().fadeIn(); 
            $tr.prev().before($tr);
            //存库
            var itemId1 = $tr.data("id");
            var sort1 = $tr.children("td:first-child").text();
            var itemId2 = $tr.next().data("id");
            var sort2 = $tr.next().children("td:first-child").text();
            
            var url = "${ctp}/gc/codebase/itemSort";
            var dataJson = '[{"id":'+itemId1+',"sortOrder":'+sort1+'},{"id":'+itemId2+',"sortOrder":'+sort2+'}]';
            //alert(dataJson);
        	var aj = $.ajax({
        		url : url,
        		data : 'itemList='+dataJson,
        		type : 'post',
        		cache : false,
        		dataType : 'json',
        		success : function(data) {
        			loadItemTable(data);
        		},error : function() {
        			//$.alert("异常！");
        		}
        	});
        } 
    }); 
    //下移 
    var $down = $(".turn_down"); 
    var len = $(".dm_all table tr").length; 
    $down.click(function() { 
        var $tr = $(".blue_1");
        if ($tr.index() != len - 1) { 
        	var td_second=$tr.children("td:first-child").text();
 			$tr.children("td:first-child").text($tr.next().children("td:first-child").text());
 			$tr.next().children("td:first-child").text(td_second)
            $tr.fadeOut().fadeIn(); 
            $tr.next().after($tr); 
            
          	//存库
            var itemId1 = $tr.data("id");
            var sort1 = $tr.children("td:first-child").text();
            var itemId2 = $tr.prev().data("id");
            var sort2 = $tr.prev().children("td:first-child").text();
            
            var url = "${ctp}/gc/codebase/itemSort";
            var dataJson = '[{"id":'+itemId1+',"sortOrder":'+sort1+'},{"id":'+itemId2+',"sortOrder":'+sort2+'}]';
            //alert(dataJson);
        	var aj = $.ajax({
        		url : url,
        		data : 'itemList='+dataJson,
        		type : 'post',
        		cache : false,
        		dataType : 'json',
        		success : function(data) {
        			loadItemTable(data);
        		},error : function() {
        			//$.alert("异常！");
        		}
        	});
        } 
    }); 
    
    
    loadTable($("#frameworkSelect").val());
	
});

function loadTable(frameworkId){
	var a = $("#frameworkSelect").val();
	//alert(a);
	$("#edF").attr("onclick","editFrameWork("+a+");");
	$("#ct").attr("onclick","createTable("+a+");");
	$("#et").attr("onclick","showTable("+a+");");
	if(frameworkId != null) {
		//alert(frameworkId);
		//var tableSelect = $("#tableSelect a:first-child").data("type");
        var tables = $("#tableSelect a");
        tables.attr("style","display:none");
        tables.each(function(i){ //i:代表索引，从0开始的
            //alert($(this).html()); // this： 代表遍历的DOM对象
            if($(this).data("type") == frameworkId){
            	$(this).attr("style","display:block");
            }
        });
	}
}

function loadItem(tableId){
	$("#ci").attr("onclick","createItem("+tableId+");");
	//alert(tableId);
	var url = "${ctp}/gc/codebase/itemList";
	var aj = $.ajax({
		url : url,
		data : 'tableId=' +tableId,
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			loadItemTable(data);
		},
		error : function() {
			$.alert("异常！");
		}
	});
	
}

function edItem(){
	var $tr = $(".blue_1");
	var itemId = $tr.data("id");
	if(itemId == null){
		$.alert("请选择代码项");
	}else {
		editItem(itemId);
	}
}

function loadItemTable(data){
	var itemList = data.itemList;
	var items = $("#itemTable tbody");
	//alert(itemList[1].name);
	$("#itemTable tbody tr").remove();
	
	for (var i = 0; i < itemList.length; i++) {
		items.append("<tr data-id="+itemList[i].id+"><td>"+itemList[i].sortOrder+"</td><td>"+itemList[i].name+"</td><td>"+itemList[i].value+"</td></tr>");
	}
}

//	加载创建对话框
function createFrameWork() {
	$.initWinOnTopFromLeft('新增代码规范', '${ctp}/gc/codebase/createFrameWork', '850', '650');
}

//加载创建对话框
function editFrameWork(id) {
	$.initWinOnTopFromLeft('编辑代码规范', '${ctp}/gc/codebase/editFramework?id=' + id, '850', '650');
}

//加载创建对话框
function createTable(frameworkId) {
	$.initWinOnTopFromLeft('新增代码表', '${ctp}/gc/codebase/createTable?frameworkId=' + frameworkId, '850', '650');
}

//加载创建对话框
function showTable(frameworkId) {
	$.initWinOnTopFromLeft('编辑代码表', '${ctp}/gc/codebase/showTable?frameworkId=' + frameworkId, '850', '650');
}

//加载创建对话框
function createItem(tableId) {
	if(tableId == -1){
		$.alert("请选择代码表");
	}else{
		$.initWinOnTopFromLeft('新增代码项', '${ctp}/gc/codebase/createItem?tableId=' + tableId, '850', '650');
	}
}

//加载创建对话框
function editItem(id) {
	$.initWinOnTopFromLeft('编辑代码项', '${ctp}/gc/codebase/editItem?id=' + id, '850', '650');
}

</script>
</html>