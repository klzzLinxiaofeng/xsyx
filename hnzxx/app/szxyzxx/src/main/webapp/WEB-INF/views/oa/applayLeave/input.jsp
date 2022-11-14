<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">

<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
<title>请假</title>
<style type="text/css">
.row-fluid .span4 {
	width: 220px;
}

input[type="radio"] {
	margin: 0 5px;
	position: relative;
	top: -1px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

.back-right {
	color: #7C7C7C;
	font: 500 18px/35px "Microsoft YaHei";
	height: 35px;
	padding-right: 20px;
	float: right;
	line-height: 56px;
}

.back-right i {
	color: #9D9D9D;
	padding-right: 10px;
}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="请假" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<a href="javascript:void(0);" class="back-right" onclick="back();"><i
							class="fa fa-arrow-circle-left"></i>返回</a>
						<ul class="top_ul">
							<li><a href="javascript:void(0)" class="on">请假</a></li>
						</ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);"
							id="leave_form">
							<input type="hidden" value="${propserDepartmentId }" id="fabuId"/>
<%--							<div class="control-group">--%>
<%--								<label class="control-label">标题<span style="color: red">*</span>：--%>
<%--								</label>--%>
<%--								<div class="controls">--%>
<%--									<input type="text" class="span8 left_red {required : true,maxlength:40}"--%>
<%--										placeholder="请输入标题，少于40个中文字符" id="title" name="title" value="${applayLeave.title }"/>--%>
<%--								</div>--%>
<%--							</div>--%>
							<div class="control-group">
								<label class="control-label">请假类型<span style="color: red">*</span>：
								</label>
								<div class="controls">
								    <select class="span3" style="width: 150px;"name="leaveType" id="leaveType">
									</select>		
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">请假时间<span
									style="color: red">*</span>：
								</label>
								<div class="controls">
									<input type="text" id="startDate" name="startDate"
										class="span4 left_red {required:true}" placeholder="开始时间"
										onclick="WdatePicker({minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});" value="${applayLeave.startDate }"/>
									&nbsp; 至 &nbsp; 
									<input type="text" id="endDate" name="endDate"
										class="span4 left_red {required:true}" placeholder="结束时间"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{m:+5})}',dateFmt:'yyyy-MM-dd HH:mm'});" value="${applayLeave.endDate }" />
								</div>
							</div>
							<c:if test="${departmentList.size()!=0 }">
								<div class="control-group content">
									<label class="control-label">部门<span style="color: red">*</span>：
									</label>
									<div class="controls">
										<select class="span3" style="width: 150px;"
											name="propserDepartmentId" id="propserDepartmentId"
											onclick="getDepartment();">
											<option value="">请选择</option>
											<c:forEach items="${departmentList }" var="department">
												<option value="${ department.departmentId}"
													<c:if test="${department.departmentId==d.id}">selected</c:if>>${ department.departmentName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</c:if>
<%--							<div class="control-group ">--%>
<%--								<label class="control-label">联系电话<span style="color:red">*</span>：</label>--%>
<%--								<div class="controls">--%>
<%--									<input type="text" name="mobile" value="${applayLeave.mobile}"--%>
<%--										class="span8 left_red {required:true,isTel:true,maxlength:11} " placeholder="请输入正确的联系电话" />--%>
<%--								</div>--%>
<%--							</div>--%>
							
							<div class="control-group">
								<label class="control-label">详情<span style="color: red">*</span>：
								</label>
								<div class="controls">
									<textarea style="" class="span8 left_red {required : true,maxlength:120}"
										id="detail" name="detail" placeholder="请输入详情，少于120个中文字符">${applayLeave.detail }</textarea>
								</div>
							</div>

<%--							<div class="control-group">--%>
<%--								<label class="control-label">有无代课<span--%>
<%--									style="color: red">*</span>：--%>
<%--								</label> <input type="hidden"--%>
<%--									value="${empty applayLeave.isDaike ? 0 : applayLeave.isDaike}"--%>
<%--									id="nowCheck" name="isDaike" />--%>
<%--								<div class="controls" style="padding-top: 5px;">--%>
<%--									<input type="radio" name="isDaike" id="you" value="0" <c:if test="${empty applayLeave || applayLeave.isDaike==0}">checked</c:if>/>有 --%>
<%--									<input type="radio" name="isDaike" id="wu" value="1" <c:if test="${applayLeave.isDaike==1 }">checked</c:if>/>无--%>
<%--								</div>--%>
<%--							</div>--%>

<%--							<div class="control-group">--%>
<%--								<label class="control-label">代课老师：</label>--%>
<%--								<div class="controls" style="padding-top: 5px;">--%>
<%--									<div class="s_select" style="position: relative;">--%>
<%--										<div class="d1">--%>
<%--											<input type="button" id="member_selector" value="添加代课教师">--%>
<%--											<input type="hidden" id="teaId" value="${tIds}" /> <span id="ts"></span>--%>
<%--											<c:if test="${!empty  alUserList}"><span id="yixuan">已选择的教师：</span></c:if>--%>
<%--											<sapn id="teachName">${tNames }</sapn>--%>
<%--											<button class="btn btn-danger" onclick="cleanTeacher();">清除</button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>

							<!-- 默认无代课老师 -->
							<input type="hidden" name="isDaike" id="wu" value="1">
							<div class="control-group">
							    <label class="control-label">审批人员<span
									style="color: red">*</span>：</label>
							    <div class="controls" style="padding-top: 5px;">
							     	<div class="s_select" style="position: relative;">
							     		<div class="d1">
							     		    <input type="button" id="person_selector" value="添加审批人员">
											<input type="hidden" id="pId" value="${pIds }" /> <span id="ps"></span>
											<c:if test="${!empty  alaUserList}"><span id="yp">已选择的审批人员：</span></c:if>
											<sapn id="pName">${pNames }</sapn>
											<button class="btn btn-danger" onclick="cleanPerson();">清除</button>
							     		</div>
							     	</div>
							    </div>
							</div>
							<div class="control-group">
								<label class="control-label">附件：</label>
								<div class="controls update_img">
									<c:choose>
										<c:when test="${not empty entity.uuid  }">
											<p
													style='display: inline-block; margin-bottom: 0; width: 240px; overflow: hidden;'>
												<a target="_blank" id="a" href='<entity:getHttpUrl uuid="${entity.uuid }"/>'>${entity.fileName}</a>
												<button id="b" onclick="deleteFile();" class="btn btn-red">删除
												</button>
											</p>
										</c:when>
										<c:otherwise>
											<p style='display: inline-block; margin-bottom: 0; width: 240px; overflow: hidden;'>
												<a taget="_blank" id="a"></a>
											</p>
											<input type="hidden" id="uploader" name="uploader"/>
										</c:otherwise>
									</c:choose>
									<input type="hidden" id="entityId" name="attachmentUuid"
										   value="${entity.uuid }"/>
								</div>
							</div>
							<div class="caozuo" style="text-align: center;">
							<input type="hidden" id="id" name="id" value="${applayLeave.id }" />
								<button class="btn btn-success" onclick="saveOrUpdate();">发布</button>
								<!-- <button class="btn">预览</button> -->
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var ids = "";
	var pIds = "";
	var checker;
	$(function() {
		$.jcGcSelector("#leaveType", {"tc" : "XY-JY-QJLX"},"${applayLeave.leaveType}",function (data) {

		});
		$("input:radio[name='isDaike']").change(
				function() {
					var checkType = $("input:radio[name='isDaike']:checked").attr("id");
					if (checkType == 'you') {
						$("#nowCheck").val(0);
					} else if (checkType == 'wu') {
						$("#nowCheck").val(1);
					}
				});

		checker = initValidator();
		// //教师筛选
		// $.createMemberSelector({
		// 	"inputIdSelector" : "#member_selector",
		// 	"ry_type" : "teach",
		// 	"excludeSelf":true,
		// 	"layerOp" : {
		// 		"shift" : "top",
		// 		type : 2
		// 	}
		// });

		isCheck();
		
		
		//审批人员筛选
		$.createMemberSelector({
			"inputIdSelector" : "#person_selector",
			"ry_type" : "teach",
			"enableBatch":false,
			"excludeSelf":true,
			"layerOp" : {
				"shift" : "top",
				type : 2
			}
		});


		uploadFile();
		//文件上传
		function uploadFile() {

			var obj = $("#uploader").uploadify({
				swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
				uploader: '${pageContext.request.contextPath}/uploader/common',
				formData: {
					'jsessionId': '<%=request.getSession().getId()%>'
				},
				fileObjName: 'file',
				fileTypeDesc: "文件上传",
				fileTypeExts: "*.*", //默认*.*
				method: 'post',
				multi: false, // 是否能选择多个文件
				auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
				removeTimeout: 1,
				queueSizeLimit: 1,
				fileSizeLimit: 4 * 1024,
				buttonText: "上传文件",
				requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
				height: 20,
				width: 70,
				onUploadSuccess: function (file, data, response) {
					var $jsonObj = eval("(" + data + ")");
					$("#entityId").val($jsonObj.uuid);
					$("#a").text($jsonObj.realFileName);
					$("#a").attr('href', $jsonObj.url);
					$("#a").attr('target', '_blank');

				},
				onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
					$("#infoBox").prev("p").css("display", "none");
					$("#infoBox").css("display", "block");
				},
				onUploadError: function (file, errorCode, errorMsg,
										 errorString) {
					$.alert('The file ' + file.name
							+ ' could not be uploaded: '
							+ errorString);
				}
			});

		}

		function deleteFile() {
			$.confirm("确定执行此次操作？", function () {
				executeDel();
			});
		}

		function executeDel() {
			$("#a").text("");
			$("#a").attr('href', "");
			$("#entityId").val("");
			$("#b").remove();
			$(".update_img").children().append(
					"<input type='file' id='uploader' name='uploader'/>");
			uploadFile();
		}


	});

	var ts = "已选择的教师：";
	var teachName = "";
	var ps ="已选择的审批人：";
	var pName = "";
	function selectedHandler(data) {
		if(data.btnId=="member_selector"){
			$("#yixuan").text("");
			ids = $("#teaId").val();
			teachName = $("#teachName").text();
			$.each(data.ids, function(index, value) {
				if (ids.indexOf(value) == -1) {
					ids = ids + value + ",";
					if ($.trim(teachName) == "") {
						teachName = data.names[index];
					} else {
						teachName = teachName + "," + data.names[index];
					}
				}
			});

			if(ids.split(",").length-1>5){
				$.error("所选代课教师已超过5个，请重新选择!");
				return;
			}
			$("#ts").text(ts);
			$("#teachName").text(teachName);
			$("#teaId").val(ids);
		}else if(data.btnId=="person_selector"){
			$("#yp").text("");
			pIds = $("#pId").val();
			pName = $("#pName").text();
			$.each(data.ids, function(index, value) {
				if (pIds.indexOf(value) == -1) {
					pIds = pIds + value + ",";
					if ($.trim(pName) == "") {
						pName = data.names[index];
					} else {
						pName = pName + "," + data.names[index];
					}
				}
			});
			if(pIds.split(",").length-1>5){
				$.error("所选审批人员已超过5个，请重新选择!");
				return;
			}
			$("#ps").text(ps);
			$("#pName").text(pName);
			$("#pId").val(pIds);
		}

		$.success("设置成功");
		$.closeWindowByName(data.windowName);

	}

	function initValidator() {
		return $("#leave_form").validate({
			errorClass : "myerror",
			rules : {
				"propserDepartmentId":{
					selectNone : true
				}

			},
			messages : {
				"propserDepartmentId":{
					required : "部门必选"
				}

			}
		});
	}

	//清除已选的教师
	function cleanTeacher() {
		$("#yixuan").text("");
		$("#ts").text("");
		$("#teaId").val("");
		$("#teachName").text("");
	}
	
	//清除已选的审批人员
	function cleanPerson() {
		$("#yp").text("");
		$("#ps").text("");
		$("#pId").val("");
		$("#pName").text("");
	}

	//判断是否选中对应的按钮
	function isCheck() {
		var checkType = $("input:radio[name='isDaike']:checked").attr("id");
		//有代课教师选择
		$("#member_selector_select").click(function() {
			$("input:radio[id='you']").prop("checked", true);
			$("#nowCheck").val(0);

		});

		//无代课教师选择
		$("#wu").click(function() {
			$("input:radio[id='wu']").prop("checked", true);
			$("#nowCheck").val(1);
			cleanTeacher();

		});

	}

	//返回
	function back() {
		window.location.href = document.referrer;
	}

	//保存或更新
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $fabuId = $("#fabuId").val();
			var tId = $("#teaId").val();
			var tName = $("#teachName").text();
			var pId = $("#pId").val();
			var receiverType = $("#nowCheck").val();
			var $requestData = formData2JSONObj("#leave_form");
			$requestData.teacherIds = tId;
			$requestData.teacherNames = tName;
			$requestData.personIds = pId;
			$requestData.fabuId = $fabuId;
			if (receiverType == "0") {
				if (tId == "" || tId == null) {
					$.alert("请选择代课教师！");
					return;
				}
			}
			
			if(pId=="" || pId==null){
				$.alert("请选择审批人员！");
				return;
			}

			var url = "${pageContext.request.contextPath}/oa/applayleave/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/oa/applayleave/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('发布成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						window.location.href = document.referrer;
					}else if("isApplay" === data.info){
						$.error("该假条已审批，不能再修改");
					} else {
						$.error("发布失败");
					}
				} else {
					$.error("发布失败");
				}
				loader.close();
			});

		}

	}
</script>


</html>