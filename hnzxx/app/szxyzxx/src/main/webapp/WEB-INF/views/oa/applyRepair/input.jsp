<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
	<%--弃用uploadify插件改为uploadifive插件--%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>

	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<%-- <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-datetimepicker.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
<style type="text/css">
.form-horizontal .controls #zpa .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zpa .img_1 img {
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zpa .img_1 a {
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
.form-horizontal .controls{
 	margin-left: 100px;
 }
 .myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
 .table tr th,.table tr td{text-align:center;}
</style>
<script type="text/javascript">
	 $(function () {
      /*   $('#datetimepicker1').datetimepicker({
            language: 'pt-BR'
        }); */
  
	//收起展开
	$(".x-collapse").click(function(){
		if($(this).hasClass("fold-on")){				
				$(this).prev().find(".x-up").hide();
				$(this).text("展开↑").removeClass("fold-on");
				}	
			else{
				$(this).prev().find(".x-up").show();
				$(this).text("收起↓").addClass("fold-on")
			}
		});	
	});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="报修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
						<h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请报修</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
				</div>
				<div class="space20"></div>
				<div class="x-main" >
					<div class="clearfix x-mright">
						<div class="list">
							<form class="form-horizontal" id="applyrepair_form">
								<div class="control-group">
									<label class="control-label">标题<span style="color:red">*</span></label>
									<div>
										<input type="text" name="title" value="${applyRepair.title}" placeholder="请输入标题，少于40个中文字符" class="span6 left-stripe {required : true,maxlength:40}">
									</div>
								</div>
								<div class="x-up">
										<div class="control-group">
											<label class="control-label">
												报修类型<span style="color:red">*</span>
											</label>
											<select id="typeId" name="typeId">
												<c:forEach items="${typeList}" var="type" >
													<option value="${type.id}">${type.name}</option>
												</c:forEach>
											</select>
											</select>
										</div>

									    <div class="control-group">
										<label class="control-label">
											审核人<span style="color:red">*</span>
										</label>
										<select id="weixiugong" name="weixiugong">
											<option value="">请选择</option>
										</select>
									</div>
									<div class="control-group">
										<label class="control-label"><font style="color: red">*</font>
											保修图片：
										</label>
										<div class="controls">
											<div>
												<input type="hidden" id="pictureUuid" name="pictureId" value="${homeWoke.pictureUuid}">
											</div>
											<div id="zpa" style="display:inline-block;">

											</div>

										</div>
										<div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
										<c:if test="${isCK==''||isCK==null}">
											<input type="hidden" id="uploader" name="uploader">
										</c:if>
										<span id="tp_queue"></span>

										<div class="clear"></div>
									</div>
									<div class="control-group">
												<label class="control-label">故障描述<span style="color:red">*</span></label>
												<div class="left">
												<div class="textarea">
													<textarea name="details" style="width:800px;height:200px;">${applyRepair.details}</textarea>
												</div>
												</div>
										</div>

								</div>
										<div class="control-group">
												<label class="control-label">维修地点<span style="color:red">*</span></label>
												<div>

													<input style="position:relative;top:-12px;" type="text" id="place" name="place" value="${applyRepair.place}" placeholder="" class="span3 left-stripe {maxlength:20}">
												</div>
										</div>

					       </form>
						</div>
					</div>
					<a href="javascript:void();" class="tc x-collapse fold-on">收起 ↓</a>
					<p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">发布</button>
					</p>
					<input type="hidden" id="applyrepari_id" value="${applyRepair.id}"/>
				  </div>
			</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		if($("#bildingId").val()!=null && $("#bildingId").val()!=''){
			findRoom();
		}
		$("#bildingId").on("change",function(){ findRoom(); });

		checker = initValidator();
		uploadFile();
		$("#bildingId").chosen();
		$("#roomId").chosen();



		//部门选择器
		$.createDeptSelector({
			"deptContainer" : "#dept",
			enableBatch : false,
			selectedItemId : "${depId}",
			selectedItemTitle : "${depName}"
		});
		addOption('/oa/applyrepair/findshenheren', "weixiugong", "user_id", "name")

	});
	function addOption(url, id, valProperty, namePropety, callback) {
		$.get(url, function (d) {
			d = JSON.parse(d);
			for (var i = 0; i < d.length; i++) {
				var obj = d[i];
				$("#" +id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
			}
			if (callback != null && callback != undefined) {
				callback(d);
			}
		})
	}
	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}

	function reMove(obj) {
		$(obj).parent().remove();
		var id=obj.getAttribute("id");
		$("#pictureUuid").val("");
	}

	function uploadFile() {
		$('#uploader').uploadifive({
			'auto': true,
			'fileObjName' : 'file',
			//'queueID': 'queue',
			'buttonText': '上传维修图片',
			removeCompleted:true,
			formData: {
				'jsessionId': '<%=request.getSession().getId()%>'
			},
			'uploadScript': '/uploader/common',
			'onUploadComplete': function (file,data) {
				var $jsonObj = eval("(" + data + ")");
				var img = '<div class="img_1"  style="margin: 5px;"><a id="'
						+ $jsonObj.uuid
						+ '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
						+ $jsonObj.url
						+ '"/>&nbsp;&nbsp;&nbsp;</div>';
				$("#pictureUuid").val($jsonObj.uuid);

				$("#zpa").append(img);
			},
			onUpload:function (file) { //上传开始时触发（每个文件触发一次）
				$("#infoBox").prev("p").css("display", "none");
				$("#infoBox").css("display", "block");
			},
			onFallback : function() {
				alert("该浏览器无法使用!");
			},
		});
	}
	var checker;
	var editor;

	
	function addDepartment(){
		var depName = $("#depName").val();
		var depId = $("#depId").val();
		if(depName==""){
			depName = "请选择";
		}
		$("#dept_selected_name_span").text(depName);
	}
	
	function initValidator() {
		return $("#applyrepair_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $requestData = formData2JSONObj("#applyrepair_form");
			//=========2016-3-7=====
			var departmentId;
			
			$requestData.departmentId = $("#dept").attr("data-id");
			
			var loader = new loadwkx();
			var $id = $("#applyrepari_id").val();

			$requestData.pictureId=$("#pictureUuid").val();
			$requestData.shenherenId=$("#weixiugong").val();
			$requestData.shenherenName=$("#weixiugong option:selected").text();
			//$requestData.details = editor.html();
			var url = "${ctp}/oa/applyrepair/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/applyrepair/" + $id;
			}
			if(($("#bildingId").val()=="" || $("#roomId").val()=="")){
				if($.trim($("#place").val())==""){
					$.alert("请输入维修地点");
					return;
				}
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('操作成功');
						window.location.href="${ctp}/oa/applyrepair/index";
					}else if("fail" === data.info){
						$.error("编号重复，请重新输入！");
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
	
	//查找维修地点
	function findRoom() {
		var optionHtml = $("#roomId");
// 		var roomIdView = $("#roomIdView").val();
		var blidingId = $("#bildingId").val();
		$.post("${ctp}/oa/device/selectRoom",{"blidingId" : blidingId},function(returnData,status){
			optionHtml.find("option").remove();
			optionHtml.append("<option value='"+"' selected='selected'>请选择</option>");
			$.each(returnData, function(index, value) {
// 				if(roomIdView == value.id) {
// 					optionHtml.append("<option value='" + value.id +"' selected='selected'>" + value.name + "</option>");
// 				}else {
				optionHtml.append("<option value='" + value.id +"'>" + value.name + "</option>");
// 				}
			});
			optionHtml.removeClass("chzn-done");
			optionHtml.css("display", "block");
			$("#roomId_chzn").remove();
			optionHtml.chosen();
		},'json');
	}
	
	//查找所在房间
	function initRoomChange(){
		var optionHtml = $("#roomId");
		var roomIdView = $("#roomIdView").val();
		optionHtml.html("");
		$("#bildingId").on("change",function(){
			var blidingId = $(this).val(); 
			$.post("${ctp}/oa/device/selectRoom",{"blidingId" : blidingId},function(returnData,status){
				optionHtml.find("option").remove();
				$.each(returnData, function(index, value) {
// 					alert(value.id)
// 					if(roomIdView == value.id) {
// 						optionHtml.append("<option value='" + value.id +"' selected='selected'>" + value.name + "</option>");
// 					}else {
					optionHtml.append("<option value='" + value.id +"'>" + value.name + "</option>");
// 					}
				});
// 				optionHtml.val("${device.roomId}");
				optionHtml.removeClass("chzn-done");
				optionHtml.css("display", "block");
				$("#roomId_chzn").remove();
				optionHtml.chosen();
			},'json');
		});
	}
</script>
</html>