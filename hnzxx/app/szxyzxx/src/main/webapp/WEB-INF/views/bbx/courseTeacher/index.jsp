<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<style type="text/css">
table th, table td {
	height: 29px;
	line-height: 29px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="走班任课教师" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>走班任课教师
							<p style="float: right;">
								<a href="javascript:void(0)" class="btn btn-success right" onclick="downloadData();" id="downloadExcel">
										下载走班任课教师导入模板
								</a>
								<span class="btn btn-file right" style="background: #e69100;color: #fff;"> 
									<span class="fileupload-new">上传走班任课教师文件</span>
									<input type="file" id="fileUpload" name="fileUpload" accept=".xls" onchange="fileUploadExcel();" />
								</span>
							</p>
						</h3>
						<div class="light_grey"></div>
						<div class="content-widgets">
							<div class="content-widgets">
								<div class="select_b">
									<div class="select_div"><span>学年：</span> <select id="xn" name="xn" style="width:150px;"></select></div>
									<div class="select_div"><span>学期：</span> <select id="xq" name="xq" style="width:150px;"></select></div>
									<div class="select_div"><span>年级：</span> <select id="nj" name="nj" style="width:150px;"></select></div>
									<div class="select_div" style="display: none;"><span>班级：</span> <select id="bj" name="bj" class="chzn-select" style="width:150px;"></select></div>
									<button type="button" class="btn btn-primary" onclick="search()">查询</button>
									
									<div class="clear"></div>
								</div>

								<input type="hidden" id="schoolId" name="schoolId" value="${schoolId}" />
								<div class="select_c">
									<%-- <c:forEach items="${gradeList}" var="grade" varStatus="status">
										<a data-obj-code="${grade.id}" href="javascript:void(0)" onclick="ajaxFunction('${grade.id}')" <c:if test='${status.index==0}'>class="on"</c:if>> 
											${grade.name}
										</a>
									</c:forEach> --%>
									
									
									<%-- <c:forEach
										items="${sessionScope[sca:currentUserKey()].stageCodes}"
										var="stage" varStatus="status">
										<c:if test="${stage != -1}">
											<a data-obj-code="${stage}" href="javascript:void(0)"
												onclick="ajaxFunction('${stage}')"
												<c:if test='${status.index==0}'>class="on"</c:if>> <jc:cache
													echoField="name" tableName="jc_stage" paramName="code"
													value="${stage}"></jc:cache>
											</a>
										</c:if>

									</c:forEach> --%>
								</div>
								<div class="select_d" style="" id="subject"></div>
								<div class="widget-container" id="code_Nation"
									style="position: relative;">
									<div class="widget-head" style="border-bottom: 0 none;">
										<h3 id="krTeacher">
											科任教师
											<p style="float: right; margin-bottom: 3px;" class="btn_link">
												<a href="javascript:void(0)" class="a3"
													style="position: absolute; right: 130px; top: 3px; border-radius: 0; height: 30px;"
													onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
												<a class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>新增科任教师</a>
											</p>
										</h3>
									</div>
									<table class="table  table-striped responsive" id="tbody">
										<tbody id="tbodyId">
										</tbody>
									</table>
									<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
										<jsp:param name="id" value="tbodyId" />
										<jsp:param name="url" value="/bbx/bwSubjectTeacher/getCourseTeacherList?sub=list" />
										<jsp:param name="pageSize" value="${page.pageSize}" />
									</jsp:include>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		
		$.createMemberSelectorByClass({
			"inputClassSelector" : ".a4"
		});
		
		$.initCascadeSelector({
			"type" : "team",
			"selectOne":true,
			"yearChangeCallback" : function(year) {
				if(year != "") {
					$.SchoolTermSelector({
						"selector" : "#xq",
						"condition" : {"schoolYear" : year},
						"afterHandler" : function($this) {
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
			"gradeCallback" : function($this){
 				search();
	 		}
		});
		
		
		$(".select_c a").click(function() {
			$(".select_c a").removeClass("on");
			$(this).addClass("on");
		});

		/* var gradeId = $(".select_c a").attr("data-obj-code");
		$("#gradeId").val(gradeId);
		ajaxFunction(gradeId); */

		
	});

	//根据学段科目列表
	function ajaxFunction(gradeId, termCode) {
		
		var courseList = '${courseList}';
		var url = "${pageContext.request.contextPath}/bbx/courseTeacher/getCourseList";
		var aj = $.ajax({
			url : url,
			data : {"gradeId":gradeId, "termCode": termCode},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				courseList = data.courseList;
				var krTeacher = document.getElementById("krTeacher");
				if (courseList.length == 0) {
					krTeacher.style.display = "none";
					document.getElementById("tbody").style.display = "none";

				} else {
					krTeacher.style.display = "";
					document.getElementById("tbody").style.display = "";
				}
				loadDiv(courseList, gradeId);
// 				afterHandler();

			},
			error : function() {
				$.alert("异常！");
			}
		});
	}

	//加载科目列表
	function loadDiv(courseList, gradeId) {
		var div = $("#subject");
		div.html('');
		if (gradeId == null) {
			div.html('<p id="noStage">暂无数据</p>');
		} else {
			if (courseList.length == 0) {
				div.html('<p id="noSubject">暂无科目数据</p>');
			} else {
				for (var i = 0; i < courseList.length; i++) {
					div.append('<input type="hidden" id="courseId" name="courseId" value="'+courseList[i].id+'"/>');
					if (i == 0) {
						 div.append('<a data-subjectCode="'
										+ courseList[i].id
										+ '"id="'
										+ courseList[i].id
										+ '"name="'
										+ courseList[i].name
										+ 'href="javascript:void(0) onclick="ajaxSubjectFunction('
										+ courseList[i].id + "," + courseList[i].termCode
										+ ')"' + 'class="on"' + 'style="cursor:pointer;">'
										+ courseList[i].name + '</a>');
					} else {
						div.append('<a data-subjectCode="'
								+ courseList[i].id
								+ '"id="'
								+ courseList[i].id
								+ '"name="'
								+ courseList[i].name
								+ 'href="javascript:void(0) onclick="ajaxSubjectFunction('
								+ courseList[i].id + "," + courseList[i].termCode
								+ ')"' + 'style="cursor:pointer;"' + '>'
								+ courseList[i].name + '</a>');
					}
				}
			}

		}

		var courseId = $(".select_d .on").attr("data-subjectCode");
		$("#courseId").val(courseId);	
		ajaxSubjectFunction(courseId);

		$(".select_d a").click(function() {
			$(".select_d a").removeClass("on");
			$(this).addClass("on");
		});
	}
	
	
	
	
	
	function search(){
		var termCode = $("#xq").val();
		if(termCode == ""){
			$.error("请选择学期");
			return;
		}
		var gradeId = $("#nj").val();
		if(gradeId == ""){
			$.error("请选择年级");
			return;
		}
		//alert(termCode + "," + gradeId);
		//$("#gradeId").val(gradeId);
		ajaxFunction(gradeId, termCode);
	}
	
	
	
	
	
	
	
	
	//加载科任教师
	function ajaxSubjectFunction(courseId) {
		//var subjectTeacherList = '${subjectTeacherList}';
		var url = "/bbx/courseTeacher/getCourseTeacherList";
		myPagination("tbodyId", {"courseId" : courseId}, url);
	}
	
	$(function() {
		//var stageCode = document.getElementById("stageCode");
		var krTeacher = document.getElementById("krTeacher");
		/* if (stageCode == null) {
			krTeacher.style.display = "none";
		} */
	});
	
	
	function selectedHandler(data) {
		var loader = new loadLayer();
		var $requestData = {};
		var $requestEditData = {};
		var $thisBtn = $("#" + data.ids);
		var $btnSelect = $("#" + data.ids + "_select");
		var tit = $btnSelect.attr("title");
		var jsonInfo = getAllInfo($thisBtn);
		$requestData.courseId = jsonInfo.courseId;
		//$requestData.subjectCode = jsonInfo.subjectCode;
		$requestData.ids = jsonInfo.ids;
		var url = "${pageContext.request.contextPath}/bbx/courseTeacher/addCourseTeacher";

		saveOrUpdate(url, $requestData, tit, data);
	}

	// 	此方法以JSON格式返回点击表格后对应的科目ID跟教师ID等等数据
	function getAllInfo(elem) {
		/* alert(JSON.stringify(data)); */
		//var stageCode = $(".select_c .on").attr("data-obj-code");
		var courseId = $(".select_d .on").attr("data-subjectCode");
		return {
			courseId : courseId,
			ids : elem.selector
		};
	}

	function saveOrUpdate(url, $requestData, tit, data) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/bbx/courseTeacher/checkTeacher",
			data : {
				ids : $requestData.ids,
				courseId : $requestData.courseId
				//subjectCode : $requestData.subjectCode
			},
			success : function(data) {
				var response = data.substring(data.length - 9,
						data.length - 2);
				var isTeacher = data.substring(2, data.length - 9);
				if ("success" == response) {
					$.error(isTeacher + "这些科任教师已存在,请重新选择!");
				} else {
					$.post(url, $requestData, function(responseData,
							status) {
						if ("success" === status) {
							if ("success" === responseData) {
								ajaxSubjectFunction($requestData.courseId);
								$.success("保存成功");
								//$.closeWindowByName(data.windowName);
								//window.location.reload();
							} else {
								$.error(responseData);
							}

						} else {
							$.error("服务器异常");
						}
						loader.close();

					});
				}
			}
		});
	}
	
	//删除科任教师信息
	function deleteCourseTeacher(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}

	function executeDel(id) {
		$.post(
			"${pageContext.request.contextPath}/bbx/courseTeacher/deleteCourseTeacher?id="
			+ id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("删除成功");
					$("#" + id).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});			
	}
	
	
	//修改科任教师信息
	function loadModifyCourseTeacherPage(id) {
		var stageCode = $(".select_c .on").attr("data-obj-code");
		var subjectCode = $(".select_d .on").attr("data-subjectCode");	 
		$.initWinOnTopFromLeft(
			"修改科任教师",
			'${pageContext.request.contextPath}/bbx/courseTeacher/modifyCourseTeacherPage?id='+ id, '600', '300');
	}
	
	//走班上课特色
	function characteristic(id){
		$.initWinOnTopFromLeft(
				"走班上课特色",
				'${pageContext.request.contextPath}/bbx/courseTeacher/characteristic?userId='+ id, '600', '310');
	}
	
	// 导出模板
	function downloadData() {
		var gradeId = $("#nj").val();
		var termCode = $("#xq").val();
		var url = "${ctp}/bbx/courseTeacher/downloadData?gradeId="+gradeId+"&termCode="+termCode;
		$("#downloadExcel").attr("href", url);
	}
	
	// 导入数据
	function fileUploadExcel(){
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
		var urlParamter = "${ctp}/bbx/courseTeacher/uploadData?gradeId=" + gradeId + "&termCode=" + termCode; 
        //上传
	    var loader = new loadLayer();
	    var file = $("#fileUpload").val(); 
	    if(true){
		var resultStatus = "error";
			loader.show();
			//执行上传文件操作的函数 
 			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
				url : urlParamter, 
 				secureuri : false, //是否启用安全提交,默认为false    
 				fileElementId : 'fileUpload', //文件选择框的id属性   
 				dataType : 'text', //服务器返回的格式,可以是json或xml等   
 				success : function(data, status) { //服务器响应成功时的处理函数
 					if(status=="success"){
 						data = eval("("+data+")");
 						// search();
 						$.each(data,function(key,value){
							if ( key === "ERROR" ) {
								$.alert(value);
							}
							if ( key === "SUCCESS" ) {
								$.success("导入完成");
							}
 						});
 						loader.close();
 					}else{
 						$.success("服务器异常");
 					}
 				} , 
 				error : function(data, status, e) { //服务器响应失败时的处理函数
 					$.error("上传失败，请重新上传");
 					location.reload();
 					loader.close();
 				} 
 			});
			
		}
	}

	
	//新增科任教师
	/* function loadCreateSubjectTeacherPage() {
		var stageCode = $(".select_c .on").attr("data-obj-code");
		var subjectCode = $(".select_d .on").attr("data-subjectCode");
		var schoolId = $("#schoolId").val();
		$.initWinOnTopFromLeft('新增科任教师','${pageContext.request.contextPath}/teach/subjectTeacher/addSubjectTeacherPage?stageCode='+ stageCode + ' &subjectCode=' + subjectCode,'600', '250');
	} */

	

	
</script>

</html>