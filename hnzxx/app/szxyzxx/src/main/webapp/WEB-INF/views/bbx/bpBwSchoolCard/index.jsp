<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<script
	src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style type="text/css">
.right{float: right;}
</style>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="BwSchoolCard" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="content-widgets">
						<!-- <div class="widget-container"> -->
							<div class="select_top">
								<div class="s1 s1_bg" id="schoolManagerSearch">
									<div hidden>
										<select id="xn"></select>
									</div>
									<select id="nj" name="gradeId" style="width:160px;"></select>
									<select id="bj" name="teamId" style="width:160px;"></select>
									<input type="text" id="name" name="name" style="width:160px;margin-top: -12px;"  placeholder="姓名"/>
									<input type="text" id="phyAccountId" name="phyAccountId" style="width:160px;margin-top: -12px;" placeholder="卡号"/>
									<button class="btn btn-success" style="margin-top: -23px;"
										id="sosuo" onclick="search()">搜索</button>
									<span class="btn btn-file right" style="background: #e69100;color: #fff;"> <span class="fileupload-new">批量导入一卡通名单</span>
									<span class="fileupload-exists"></span> 
										<input type="file" id="fileUpload" name="fileUpload" accept=".xls" onchange="fileOnchange();" />
									</span> 
									<a href="javascript:void(0)" class="btn btn-success right" onclick="downLoadData();" id="downLoadExcel">
										下载一卡通名单
									</a>
								</div>
							</div>
						<!-- </div> -->
				</div>
			</div>
			<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
									<thead>
										<tr role="row">
											<!-- <th>主键</th> -->
											<!-- <th style="display: none;" >用户Id</th>
											<th style="display: none;">用户类型</th> -->
											<th width="20%">用户姓名</th>
											<th width="20%">卡号</th>
											<th width="20%">物理卡号</th>
											<!-- <th>状态</th> -->
											<!-- <th>记录是否删除</th>
											<th>记录创建时间</th>
											<th>记录修改时间</th> -->
											<th class="caozuo">操作</th>
										</tr>
									</thead>
									<tbody id="bwSchoolCard_list_content">
										<jsp:include page="./list.jsp" />
									</tbody>
								</table>
								<jsp:include page="/views/embedded/jqpagination.jsp"
									flush="true">
									<jsp:param name="id" value="bwSchoolCard_list_content" />
									<jsp:param name="url"
										value="/clazz/bwSchoolCard/index?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
								<div class="clear"></div>
						</div>
					</div>
				</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {	  
	$.initCascadeSelector({
		"type" : "team",
		"teamCallback" : function($this) {
			// search();
		}
	});
});
/* $(function(){		
	// alert('${sessionScope[sca:currentUserKey()].currentRoleCode}');		
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	if(currentRoleCode == "SCHOOL_LEADER"){
		$("#classMasterSearch").html("");
		$("#classMasterSearch").hide();
		$.initCascadeSelector({
			"type" : "team",
			"teamCallback" : function($this) {
			}
		});		
	}else{
		$("#schoolManagerSearch").html("");		
		$("#schoolManagerSearch").hide();		
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#bj",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   search();
				}	
		   });
	}	
}); */
	function search() {
		var teamId = $("#bj").val();
		var gradeId = $("#nj").val();
		var name = $("#name").val();
		var phyAccountId = $("#phyAccountId").val();
		/* if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		} */
		var val = {};
		val.teamId = teamId;
		val.gradeId = gradeId;
		val.name = name;
		val.phyAccountId = phyAccountId;
		var id = "bwSchoolCard_list_content";
		var url = "/bbx/bpBwschoolcard/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/bpBwschoolcard/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwschoolcard/editor?id=' + id, '700', '400');
	}
	
	function downLoadData() {
		var teamId = $("#bj").val();
		var gradeId = $("#nj").val();
		/* if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		} */
		var url = "${ctp}/bbx/bpBwschoolcard/downLoadData?teamId="+teamId+"&gradeId="+gradeId;
		$("#downLoadExcel").attr("href", url);
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwschoolcard/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwschoolcard/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	function fileOnchange(){
    	//上传
	   var loader = new loadLayer();
		var file = $("#fileUpload").val(); 
		if(true){
			//location.reload();
			$(".stepy-widget .stepy-titles > li").removeClass("current-step");
			$("#uploadForm-title-1").addClass("current-step");
			$("fieldset").hide();
			$("#uploadForm-step-1").show();
		}
		if(true){
			var resultStatus = "error";
			loader.show();
			//执行上传文件操作的函数 
 			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
				url : "${ctp}/bbx/bpBwschoolcard/upLoaddata", 
 				secureuri : false, //是否启用安全提交,默认为false    
 				fileElementId : 'fileUpload', //文件选择框的id属性   
 				dataType : 'text', //服务器返回的格式,可以是json或xml等   
 				success : function(data, status) { //服务器响应成功时的处理函数
 					if(status=="success"){
 						data = eval("("+data+")");
						$.each(data,function(key,values){
							if(key === "SUCCESS"){
		 						search();
								/* var successList = data[key];
								loadTableSuccess(successList); */
							}else if(key === "ERROR"){
								var errorList = data[key];
								//loadTableFail(errorList);
								alert(errorList);
								//$.errorDialog(errorList);
							}
						});
 						loader.close();
						$.success("导入完成");
 					}else{
 						$.success("服务器异常");
 					}
 				}, 
 				error : function(data, status, e) { //服务器响应失败时的处理函数
 					$.error("上传失败，请重新上传");
 					location.reload();
 					loader.close();
 				} 
 			});
		}
}

/* function ajaxFileUpload(){
	var fileUpload = $("#fileUpload").val();
	$.ajaxFileUpload({ 
		//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
		url : "${ctp}/bbx/bpBwschoolcard/upLoaddata",
		secureuri : false, //是否启用安全提交,默认为false
		fileElementId : 'fileUpload', //文件选择框的id属性
		dataType : 'text', //服务器返回的格式,可以是json或xml等   
		success : function(data, status) { //服务器响应成功时的处理函数
			alert(data+"=="+status);
		}, 
		error : function(data, status, e) { //服务器响应失败时的处理函数
			alert(data+"==="+status+"=="+e);
		} 
	});
} */

function loadTableSuccess(listSuccess){
	var parSucc = "";
	if (listSuccess.length == 0) {
		$("#successResultId").html("<tr><td colspan='4'>暂无成功成绩信息</td></tr>");
	}else{
		var index = 0;
		for (var i = 0, len = listSuccess.length; i < len; i++) {
			index = i+1;
			parSucc+="<tr><td>"+index+"</td><td>"+listSuccess[i].teacherName+"</td><td>"+'成功'+"</td></tr>";
		}
		$("#successResultId").html(parSucc);
	}
}

function loadTableFail(listFail){
	var parFail = "";
	if(listFail.length == 0){
		$("#failResultId").html("<tr><td colspan='4'>暂无失败成绩信息</td></tr>");
	}else{
		var index = 0;
		for(var k = 0, lenTemp = listFail.length; k < lenTemp; k++){
			index = k+1;
			parFail+="<tr><td>"+index+"</td><td>"+listFail[k].teacherName+"</td><td>"+listFail[k].errorMessage+"</td></tr>";
		}
		$("#failResultId").html(parFail);
	}
}
</script>
</html>