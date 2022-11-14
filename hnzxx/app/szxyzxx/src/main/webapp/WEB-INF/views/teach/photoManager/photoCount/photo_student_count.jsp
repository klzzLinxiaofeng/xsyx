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
			<jsp:param value="学生图片统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生图片统计
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="widget-container">
							<div class="select_b">
								<div class="container-fluid">
									<div class="row-fluid">
										<div class="select_class">
											<div hidden><select id="xn" ></select></div>
										    <span  style="width:45px;margin-left: 40px;line-height: 35px;float:left;">年级：</span><select id="nj" ></select>
											<div hidden><select id="bj"></select></div>
											<button class="btn btn-primary" onclick="search()" style="margin-top:-23px;">查询</button>
										</div>
									</div>
									</div>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>班级</th>
											<th>已上传人数（人）</th>
											<th>未上传人数（人）</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="student_list_content">
									<jsp:include page="./studentList.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="student_list_content" />
								<jsp:param name="url" value="/teach/photoManager/studentCountIndex?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
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
		var val = {
				"gradeId" : $("#nj").val(),
		};
		var id = "student_list_content";
		var url = "/teach/photoManager/studentCountIndex?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	$.initCascadeSelector({"type" : "team", "teamCallback" : function($this){
	}});
	
	$(function(){
		
	});
	
    // 	加载创建对话框
	function loadPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teach/photoManager/loadDetail?id='+id+'&type=2', '550','300');
	}
</script>
</html>