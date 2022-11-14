<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title>教师照片</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教师照片" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师照片
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="allPhoto();"><i class="fa fa-plus"></i>导&nbsp; &nbsp;入 </a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span >部门：</span> <select id="dept"></select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<div class="tu_list" id="teacherList">
								
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function(){
			//获取部门
			$.PjDeptSelector({
				   "selector" : "#dept",
				   "afterHandler" : function() {
					   search();
					}
			   });
		})
		
		
		<%--
			@function 部门选择
		--%>
		$.PjDeptSelector = function(options) {
		var defOption = {
			"selector" : "#dept",
			"condition" : {},
			"selectedVal" :  "",
			"afterHandler" : function() {},
			"firstOptionTitle" : "请选择",
			"isUseChosen" : true
		};
		options = $.extend({}, defOption, options || {});
		
		var selector = $(options.selector);
		selector.html("");
		selector.append("<option value=''>"+ options.firstOptionTitle +"</option>");
		
		$.getPjDept(options.condition, function(data) {
			$.each(data, function(index, value) {
				selector.append("<option value='" + value.id + "'>" + value.name + "</option>")
			});
			selector.val(options.selectedVal);
			if(options.isUseChosen == null || options.isUseChosen) {
				selector.chosen();
			}
			options.afterHandler(selector);
		});
	}
		
	$.getPjDept = function(conditionJson, afterHandler) {
		$.get("${ctp}/teach/photoManager/list/dept", conditionJson, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				afterHandler(data);
			}
		});
	}	
	
	<%--
		@function 查询部门老师
		@date 2016年02月19日
	--%>
	function search(){
		var $request = {
				departmentId:$("#dept").val()
		};
		$("#teacherList").load("${ctp}/teach/photoManager/teacherList",$request);
		
	}
	
	<%--
	@function 修改图片
	--%>
	function allPhoto(){
		$.initWinOnTopFromLeft('批量导入', '${ctp}/teach/photoManager/allPhoto', '500', '500');
	}
	</script>
</body>
</html>