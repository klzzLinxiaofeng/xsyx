<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<scrip src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<title>学生管理</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="选科录取" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							选科录取
							<p class="btn_link" style="float: right;">		
								<a href="javascript:void(0)" class="btn btn-success right" onclick="downloadCourseAdmission();" id="downloadCourseAdmissionExcel">
									下载选科录取导入模板
								</a>
								<span class="btn btn-file right" style="background: #e69100;color: #fff;"> 
									<span class="fileupload-new">上传选科录取结果</span>
									<input type="file" id="courseAdmissionFileUpload" name="courseAdmissionFileUpload" accept=".xls" onchange="courseAdmissionFileUploadExcel();" />
								</span>	
								<a href="javascript:void(0)" class="btn btn-success right" onclick="downloadCourseAdmissionData();" id="downloadCourseAdmissionDataExcel">
									下载选科录录取结果
								</a>		
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:140px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
								<input type="checkbox" id="notChoice" name="notChoice" value="1" style="margin:0 8px;margin-left:20px;vertical-align: text-bottom;">未选科
								<input type="checkbox" id="notPass" name="notPass" value="1" style="margin:0 8px;margin-left:20px;vertical-align: text-bottom;"/>未录取
								
								<button type="button" class="btn btn-primary" onclick="search()" style="margin-left:8px;">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
		                           <th>姓名</th>
		                           <th>选科结果</th>
		                           <th>录取结果</th>
		                           <th class="caozuo" style="width:250px;">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url"  value="/bbx/courseAdmission/index?sub=list" />
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
	$(function() {	
		$.ajaxSetup({
			async : false
		});
		$.initCascadeSelector({
			"type" : "team", 
			"selectOne":true,
			"yearChangeCallback" : function(year){
				if(year != "") {
					$.SchoolTermSelector({
						"selector" : "#xq",
						"condition" : {"schoolYear" : year},
						"afterHandler" : function($this) {
							$this.change();
							$("#xq_chzn").remove();
							$this.show().removeClass("chzn-done").chosen();
						}
					});
				} else {
					$("#xq").val("");
					$("#xq_chzn").remove();
					$("#xq").show().removeClass("chzn-done").chosen();
				}
				
			},
			"teamCallback" : function() {
				search();
			}
		});
	});

	//导出模板
	function downloadCourseAdmission() {
		var gradeId = $("#nj").val();
		if ( gradeId == "" || gradeId == null ) {
			$.error("请选择年级");
			return;
		}
		var termCode = $("#xq").val();
		if ( termCode == "" || termCode == null ) {
			$.error("请选择学期");
			return;
		} 
		var url = "${ctp}/bbx/courseAdmission/downLoadCourseAdmissionTemplate?gradeId="+gradeId+ "&termCode=" + termCode;
		$("#downloadCourseAdmissionExcel").attr("href", url);
	}	
	
	//导出模板
	function downloadCourseAdmissionData() {
		var gradeId = $("#nj").val();
		if ( gradeId == "" || gradeId == null ) {
			$.error("请选择年级");
			return;
		}
		var termCode = $("#xq").val();
		if ( termCode == "" || termCode == null ) {
			$.error("请选择学期");
			return;
		} 
		var url = "${ctp}/bbx/courseAdmission/downLoadCourseAdmission?gradeId="+gradeId+ "&termCode=" + termCode;
		$("#downloadCourseAdmissionDataExcel").attr("href", url);
	}
	
	// 导入数据 
	function courseAdmissionFileUploadExcel() {
		var gradeId = $("#nj").val();
		if ( gradeId == "" || gradeId == null ) {
			$.error("请选择年级");
			return;
		}
	
		var termCode = $("#xq").val();
		if ( termCode == "" || termCode == null ) {
			$.error("请选择学期");
			return;
		} 
		
		var urlParamter = "${ctp}/bbx/courseAdmission/uploadCourseAdmissionTemplate?gradeId=" + gradeId + "&termCode=" + termCode; 
    	//上传
	   	var loader = new loadLayer();
		var file = $("#courseAdmissionFileUpload").val();
		loader.show();
		//执行上传文件操作的函数 
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
			url : urlParamter, 
			secureuri : false, //是否启用安全提交,默认为false    
			fileElementId : 'courseAdmissionFileUpload', //文件选择框的id属性   
			dataType : 'text', //服务器返回的格式,可以是json或xml等   
			success : function(data, status) { //服务器响应成功时的处理函数
				if(status=="success"){
					data = eval("("+data+")");
					$.each(data,function(key,value){
					if ( key === "ERROR" ) {
						alert(value);
					}
					if ( key === "SUCCESS" ) {
						$.success("导入完成");
					}
					});
					loader.close();
					search();
				}else{
					$.success("服务器异常");
				}
			}, 
			error : function(data, status, e) { //服务器响应失败时的处理函数
				$.error("上传失败，请重新上传");
				loader.close();
			} 
		});
	}



	
	
	
	
	
	
	function changeInterrupteur(){
		var teamId = $("#bj").val();
		var val = {
				"schoolYear" : $("#xn").val(),
				"gradeId" : $("#nj").val(),
				"teamId" : $("#bj").val(),
				"name" : $("#name").val(),
			};
		if(teamId == "" || teamId == "undefind"){
			return;
		}
		
		var url = "${pageContext.request.contextPath}/teach/student/modifyInterrupteur";
		$.post(url, {"teamId":teamId}, function(data) {
			if(data =="true"){
// 				 $(".student_right a").removeClass("auto_close").addClass("auto_open");
// 				$(".student_right span").html("家长端APP可以录入学生档案"); 
			} else{
// 				$(".student_right a").removeClass("auto_open").addClass("auto_close");
// 				$(".student_right span").html("家长端APP可以录入学生档案");
			}
		});
		
		//重新载入表格
		var id = "module_list_content";
		var url = "/bbx/courseAdmission/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}


	function search() {	
		var val = {
				"schoolYear" : $("#xn").val(),
				"gradeId" : $("#nj").val(),
				"teamId" : $("#bj").val(),
				"name" : $("#name").val(),
				"termCode" : $("#xq").val(),
				"termCode" : $("#xq").val(),
				"termCode" : $("#xq").val(),
				"notPass" : $("#notPass").is(':checked'),
				"notChoice" : $("#notChoice").is(':checked')
			};
	
		var id = "module_list_content";
		var url = "/bbx/courseAdmission/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	function enroll(id){
		var loader = new loadLayer();
		var termCode = $("#xq").val();
		var $requestData = {"id":id,"termCode":termCode};
		var url = "${pageContext.request.contextPath}/bbx/courseAdmission/enroll";
		loader.show();
		$.post(url,$requestData,function(data,status){
			if(status === "success"){
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success('操作成功');
					search();
				}else {
					$.error("操作失败");
				}
			}
			loader.close();
		});
	}
	
	function changePage(id){
		var gradeId = $("#nj").val();
		var termCode = $("#xq").val();
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/courseAdmission/changePage?gradeId='+gradeId+"&termCode="+termCode+"&id="+id, '600', '250');
	}
	
		
	function getGrade(gradeId){
		$("#gradeId").val(gradeId);
	}
	
	function deleteStudent(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/teach/student/deleteStudent?id=" + id, {"_method" : "delete"}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	function tjstudent(){
		location.href="${pageContext.request.contextPath}/teach/student/studentTJ?type=all";
	}
	
	function changeOfPage(id, name, sex, mobile){
		if(sex == 1){
			sex = "男";
		}else if(sex == 2){
			sex = "女";
		}
		$tr = $("#tr_"+id);
// 		$tr.find("td")[2].innerText = name;
		$tr.find("td")[3].innerText = sex;
		$tr.find("td")[4].innerText = mobile;
	}
</script>
</html>