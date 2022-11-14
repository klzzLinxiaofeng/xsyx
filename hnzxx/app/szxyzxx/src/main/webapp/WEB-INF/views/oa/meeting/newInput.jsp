<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>文印</title>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<style type="text/css">
.row-fluid .span4{
		width:220px;
	}
input[type="radio"]{
		margin:0 5px 0 60px;
		position:relative;
		top:-1px;
	}
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.ke-container{display:inline-block}
</style>
<script type="text/javascript">
var checker;
var editor;
var ids = "";
	$(function(){
		 $("input:radio[name='daike']").change(function (){
		 	var checkType = $("input:radio[name='daike']:checked").attr("id");
		 	if(checkType=='all'){
				$("#nowCheck").val(0);
		 	}else if(checkType=='person'){
		 		$("#nowCheck").val(2);
		 	}else{
		 		$("#nowCheck").val(1);
		 	}
	       });
	
			checker = initValidator();
			uploadFile();
			
			//部门选择器
			$.createDeptSelector({
				"deptContainer" : "#dept",
				enableBatch : true,
				selectedItemId : "${depId}",
				selectedItemTitle : "${depName}"
			});
			
			//教师筛选
			$.createMemberSelector({
				"inputIdSelector" : "#member_selector",
				"ry_type" : "teach",
				"layerOp" : {
					"shift" : "top",
					type : 2
				}
			});
		
			isCheck();
	});
	
	function addDepartment(){
		var depName = $("#depName").val();
		var depId = $("#depId").val();
		if(depName==""){
			depName = "请选择";
		}
		$("#dept_selected_name_span").text(depName);
	}

	var ts = "已选择的老师："
	var teachName= "";
	function selectedHandler(data) {
	teachName= $("#teachName").text();
	ids = $("#teaId").val();
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids = ids + value + ",";
				if($.trim(teachName)==""){
					teachName = data.names[index];
				}else{
					teachName = teachName + "," + data.names[index];
				}
			}
		});
		$("#ts").text(ts);
		$("#teachName").text(teachName);
		$("#teaId").val(ids);
		$.success("设置成功");
		$.closeWindowByName(data.windowName);
	}
	
	//文档上传
	function uploadFile(){
    	var obj = setTimeout(function(){$("#uploaderFile").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.doc; *.txt; *.docx;',
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传附件",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var file ='<a href="' + $jsonObj.url +'">' + $jsonObj.realFileName + '</a>'
										+ '&nbsp;&nbsp;&nbsp;';
								$.success("上传成功!", 9);
								$("#fj").html(file);
								$("#fjId").val($jsonObj.uuid);
							},
							onUploadStart : function(file) { //上传开始时触发（每个文件触发一次）
								$("#infoBox").prev("p").css("display", "none");
								$("#infoBox").css("display", "block");
							},
							onUploadError : function(file, errorCode, errorMsg,
									errorString) {
								$.alert('The file ' + file.name
										+ ' could not be uploaded: '
										+ errorString);
							}
    	})},10);
	}
	

	function initValidator() {
		return $("#meeting_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//清除教师信息
	function cleanTeacher(){
		$("#ts").text("");
		$("#teaId").val("");
		$("#teachName").text("");
	}
	
	//判断是否选中对应的按钮
	function isCheck(){
		//部门选择
		$("#dept_selected_name_span").click(function(){
			 cleanTeacher();
			 $("input:radio[id='department']").prop("checked",true);
			 $("#nowCheck").val(1);
		});
		//人员选择
		$("#member_selector_select").click(function(){
			 $("input:radio[id='person']").prop("checked",true);
			 $("#nowCheck").val(2);
			 $("#dept_selected_name_span").text("请选择");
		});
		//选择全部
		$("#all").click(function(){
			 $("input:radio[id='all']").prop("checked",true);
			 $("#nowCheck").val(0);
			 cleanTeacher();
			 $("#dept_selected_name_span").text("请选择");
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var tId = $("#teaId").val();
			var tName = $("#teachName").text();
			var departmentId = $("#dept").attr("data-id");
			if(departmentId === undefined){
				departmentId = $("#depId").val();
			}
			var receiverType = $("#nowCheck").val();
			var $requestData = formData2JSONObj("#meeting_form");
			$requestData.uploadFile = $("#fjId").val();
			
			$requestData.departmentIds = departmentId;
			$requestData.teacherIds = tId;
			$requestData.teacherNames = tName;
			if(receiverType=='1'){
				if(departmentId == "" || departmentId == null){
					$.alert("请选择接收信息的部门！");
					return;
				}
			}else if(receiverType=='2'){
				if(tId == "" || tId == null){
					$.alert("请选择接收信息的人员！");
					return;
				}
			}
			
			var url = "${pageContext.request.contextPath}/office/meeting/addMeeting";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/office/meeting/editMetting/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('操作成功');
						window.location.href = document.referrer;
					}else if("notUpdate" === data.info){
						$.error("会前一个小时不可再次修改");
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
	function back(){
		window.location.href=document.referrer; 
	}
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="会议" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
				        <a href="javascript:void(0);" onclick="back();" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">组织会议</a></li>
				        </ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" id="meeting_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">标题<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" name="meetingName" class="span8 left_red {required : true,maxlength:40}" placeholder="请输入标题，少于40个中文字符" value="${meeting.meetingName}"/>
								</div>
							</div>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label">主持人<span style="color:red">*</span>：</label> -->
<!-- 								<div class="controls"> -->
<!-- 									<input type="text" name="zhuchi" class="span4 left_red {required : true,maxlength:20}" placeholder="请输入主持人" value=""/> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="control-group">
								<label class="control-label">会议时间<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" id="starttime" name="starttime" style="width:187px" class="span2 left_red {required:true}"
									placeholder="请输入会议开始时间" value="${meeting.starttime}"
									onclick="WdatePicker({minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd HH:mm'});">
									-
									<input type="text" id="endtime" name="endtime" style="width:187px" class="span2 left_red {required:true}"
									placeholder="请输入会议结束时间" value="${meeting.endtime}"
									 onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{m:+5})}',dateFmt:'yyyy-MM-dd HH:mm'});">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">会议地点<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" name="address" class="span4 left_red {required : true,maxlength:20}" placeholder="请输入地址" value="${meeting.address}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">附件：</label>
								<div class="controls">
								<div id="fj" style="display:inline-block;">
									<a href='<entity:getHttpUrl uuid="${meeting.uploadFile}"/>'>
										${entity.fileName}
									</a>
								</div>
								<input type="hidden" id="fjId" value="${meeting.uploadFile}"/>
								<input type="hidden" id="uploaderFile">
								<span id="tp_queue"></span>
								<div class="clear"></div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" style="height:45px;line-height:45px;">发布对象：</label>
								<input type="hidden" value="${empty meeting.fanwei ? 0 : meeting.fanwei}" id="nowCheck" name="fanwei"/>
								<div class="controls" style="padding-top:5px;">
									<input type="radio" name="daike" id="all" style="margin-left:0;" <c:if test="${meeting.fanwei==0 || empty meeting}">checked="checked"</c:if>/>全员
									<input type="radio" name="daike" id="department" <c:if test="${meeting.fanwei==1}">checked="checked"</c:if>/>
									<span>部门</span>
									<div id="dept" style="margin-left:10px;display:inline-block;position:relative;top:10px;"></div>
									<input type="hidden" value="${depId}" id="depId"/>
									<input type="hidden" value="${depName}" id="depName"/>
									<input type="radio" name="daike" id="person" <c:if test="${meeting.fanwei==2}">checked="checked"</c:if>/>个人
									<input type="button" id="member_selector" value="添加教师" >
									<input type="hidden" id="teaId" value="${tIds}"/>
									<span id="ts"></span>
                                  <c:if test="${!empty  noticeUser}">已选择的老师</c:if>
                                  <sapn id="teachName">${tNames}</sapn>
                                     <button class="btn btn-danger" onclick="cleanTeacher();">清除</button>
									<div class="s_select" style="position:relative;display:inline-block;">
									</div>
								</div>
							</div>
							<div class="caozuo" style="text-align:center;">
								<input type="hidden" id="id" value="${meeting.id}"/>
								<button class="btn btn-success" type="button" onclick="saveOrUpdate();">发布</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>