<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title>学生照片</title>
<style type="text/css">
#nj_chzn{
	float:left;
}
</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生照片" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生照片
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="allPhoto();"><i class="fa fa-plus"></i>导&nbsp; &nbsp;入 </a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<!-- 
								<div class="select_div">
									<span >部门：</span> <select id="dept"></select>
								</div> -->
								<div class="container-fluid">
									<div class="row-fluid">
										<div class="select_class">
											<div hidden><select id="xn" ></select></div>
										    <span  style="width:45px;margin-left: 40px;line-height: 35px;float:left;">年级：</span><select id="nj" onchange="search();" ></select>
											<span  style="width:45px;margin-left: 40px;line-height: 35px;float:left;">班级：</span><select id="bj"></select>
											<button class="btn btn-primary" onclick="search()" style="margin: 0 30px 0 0;height: 30px;float: right;">查询</button>
										</div>
									</div>
									</div>
								<div class="clear"></div>
							</div>
							<div class="tu_list" id="studentList">
								
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
			
		})
		
		$.initCascadeSelector({"type" : "team", "teamCallback" : function($this){
		}});
	
	<%--
		@function 查询班级学生
		@date 2016年02月26日
	--%>
	function search(){
		var val = {};
		var xn = $("#xn").val();
		var nj = $("#nj").val();
		var bj = $("#bj").val();
		if (xn != null && xn != "") {
			val.schoolYear = xn;
		}
	
		if (nj != null && nj != "") {
			val.gradeId = nj;
		}
	
		if (bj != null && bj != "") {
			val.id = bj;
		}else{
			return;
		}
		var url = "${ctp}/teach/photoManager/studentList";
		//加载学生列表
	    $("#studentList").load(url,val);
	}
	
	<%--
	@function 修改图片
	--%>
	function allPhoto(){
		$.initWinOnTopFromLeft('批量导入', '${ctp}/teach/photoManager/allStudentPhoto', '500', '500');
	}
	</script>
</body>
</html>