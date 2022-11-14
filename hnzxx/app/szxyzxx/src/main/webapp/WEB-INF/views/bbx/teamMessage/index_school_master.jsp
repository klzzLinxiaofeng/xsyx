<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<style type="text/css">
.container-fluid {
	padding-top: 20px;
}

.entry p {
	padding-top: 15px;
	padding-bottom: 10px
}
.widget-head{
	height: 50px;
}
.trends ul li span {
	background: #e15a6b;
}
</style>
<script type="text/javascript">
  function delDiv(a){

   var divA = document.getElementById(a);
   var p=divA.parentElement
   var pp=p.parentElement


  pp.parentNode.removeChild(pp);
  }
  function delSpan(){
   var divA = document.getElementById("divA");
   divA.innerText = "";
  };
 </script>
<body>
	<div class="container-fluid">
		<div class="row-fluid "></div>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<div class="widget-head">
						<div class="select_top" style="margin-top:0">
								<div class="s1 s1_bg">
								  <span style="font-size: 16px;font-family: 微软雅黑;line-height: 35px;margin: 0 0 0 10px;">${team.name }</span>
								  <input id="teamId" value=${team.id }  type="hidden"/>
							<!-- <select id="xn" name="xn" class="chzn-select"
								style="width: 120px;"></select> -->
							<div class="content">
								<input type="text" placeholder="请输入内容" id="content">
								<div class="click">
									<button class="btn btn-success" type="button"
										onclick="search();">搜索</button>
									<button class="btn btn-primary" onclick="back()">返回</button>
									
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
				</div>
			</div>
			<div class="trends">
				<ul id="school_master_message_content">
<%-- 					<jsp:include page="./list.jsp"/> --%>
				</ul>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="school_master_message_content" />
					<jsp:param name="url"
						value="/bbx/teamMessage/indexForSchoolMaster?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

$(function(){
	search();
});


	function search() {
		var val = {};
		var teamId = $("#teamId").val().trim();
		var content = $("#content").val().trim();
		
		if(teamId !=null && teamId !=""){
			val.teamId = teamId;
		}
		
		if (content != null && content != "") {
			val.content = content;
		}
		var id = "school_master_message_content";
		var url = "/bbx/teamMessage/indexForSchoolMaster?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}


	
	//  加载浏览对话框
	function loadScannerPage(id) {
		var teamId = $("#teamId").val().trim();
		$.initWinOnTopFromLeft_bbx('浏览详情', '${ctp}/bbx/teamMessage/scannerViewer?id='  + id+'&teamId='+teamId+"&scannerType='notRead'", '750', '350');
	}
	

	//返回对话框
	 function back(){
		 window.location.href="${ctp}/bbx/teamMessage/schoolMasterView";
	 }
	
</script>
</html>