<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教师排序" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>教师名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th width="33%">教师ID</th>
											<th width="33%">教师名称</th>
											<th width="33%">排序</th>
<!-- 										<th class="caozuo" style="max-width: 250px;">操作</th> -->
									</tr>
								</thead>
								<tbody id="teacherSort_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
<%-- 							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 								<jsp:param name="id" value="teacherSort_list_content" /> --%>
<%-- 								<jsp:param name="url" value="/teacher/sort/index?sub=list&dm=${param.dm}" /> --%>
<%-- 								<jsp:param name="pageSize" value="${page.pageSize}" /> --%>
<%-- 							</jsp:include> --%>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.teacherName = name;
		}
		val = $.setProperty(val);//设置排序属性
		var id = "teacherSort_list_content";
		var url = "/teacher/sort/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	function editSort(id){
		var $seletor = $("#" + id + "_tr td:eq(2)");
		var sort = $seletor.html();
		if(isDouble(sort)){
			return;
		}
		$seletor.empty();
		$seletor.append('<input onblur="saveValue(' + id +',this.value,' + sort + ');" value="" style="border-radius: 5px;background-color:#BAE5D6;margin:0;"/>');
		$seletor.find("input").focus();
		$seletor.find("input").val(sort);
	}
	//验证是否为分数（数字）
	function isDouble(value){     
	    var str = value.trim();    
	    if(str.length!=0){
	        reg=/^\+?[1-9][0-9]*$/;
	        if(!reg.test(str)){    
// 	            $.alert("对不起，您输入的排序数格式不正确!");
	            return true;
	        }    
	    }else{
//	     	$.alert("请输入分数！");
	    	return true;
	    } 
	    return false;
	} 
	function saveValue(id,value,sort){
		var $seletor = $("#" + id + "_tr td:eq(2)");
		if(isDouble(value)){
			$seletor.html(sort);
			return;
		}
		$seletor.empty();
		if(value != null && value != ""){
			$seletor.html(value);
			if(value != sort){
				var $requestData = {};
				$requestData.sort = value;
				var loader = new loadLayer();
				if(id != null && id != "undefined" && id != null){
					$requestData._method = "put";
					var url = "${ctp}/teacher/sort/" + id;
					loader.show();
					$.post(url, $requestData, function(data, status) {
						if("success" === status) {
							data = eval("(" + data + ")");
							if("success" === data.info) {
							} else {
								$.error("操作失败");
							}
						}else{
							$.error("操作失败");
						}
						loader.close();
					});
				}
			}
		}else{
			$seletor.html(sort);
		}
	}
	$(function(){
		var conditionJson= {
				"sortIndex" : [0,1,2],
				"sortColumn":["teacher_id","teacher_name","sort"],
				"defaultIndex" : 2,
				"ascending" : true,
				"afterHandler" : function(){
					search();
				}
			};
			$.initSortingProperty(conditionJson);
	});
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/teacher/sort/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/teacher/sort/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teacher/sort/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teacher/sort/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>