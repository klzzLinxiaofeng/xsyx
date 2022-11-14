<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增学校</title>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>

<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
            <div class="content-widgets white">
	            <div class="widget-head">
					<h3>学校基本信息
						<p class="btn_link" style="float:right;">
							<a href="javascript:void(0)" class="a3" onclick="saveSchool();">保存</a>
						</p>
					</h3>
				</div>
				<div class="light_grey"></div>
				
				<div class="widget-head">
					<span style="width: 80px;line-height: 30px;padding-left: 25px;">学校创建者：</span>
					<select class="span2" name="attribute" id="attribute" style="position:absolute;width:80;height:20;">
						<!-- <option value="">请选择</option> -->
						<c:forEach items="${schoolAttributePlatformList }" var ="attribute">
							<option value="${attribute.id}">${attribute.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="light_grey"></div>
				
                <div class="content-widgets">
                    <div class="widget-container">
                       	<div class="form-horizontal_1">
                       	<form action="javascript:void(0);" id="schoolForm" name="schoolForm">
                       		<table class="table table-bordered responsive">
                       			<tbody>
                       				<tr>
                       					<td rowspan="6">基本信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学校名称：</label>
												<div class="controls">
													<input type="text" name="name" id="name" placeholder="请输入学校名称" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red"></span>英文名称：</label>
												<div class="controls">
													<input type="text" name="englishName" id="englishName" placeholder="请输入学校英文名称" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr><td><div class="control-group">
												<label class="control-label"><span class="red">*</span>学校代码：</label>
												<div class="controls">
													<input type="text" id="code" name="code" placeholder="请输入学校代码" class="span10">
												</div>
											</div></td>
										</tr>
										<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学校类别：</label>
												<div class="controls">
															<select class="span10" id="schoolType" name="schoolType">

															</select>
														</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red">*</span>办学类别:</label>
												<div class="controls">
													<select class="span10" id="runningType" name="runningType">
														
													</select>
												</div>
											</div>
                       					</td>
                       					</tr>
                       					<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">所在地城乡类型：</label>
												<div class="controls">
													<select class="span10" name="cityType" id="cityType">
														
													</select>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">所在地经济属性：</label>
												<div class="controls">
													<select class="span10" id="econonyType" name="econonyType">
														
													</select>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学段：</label>
												<div class="controls">
													<span class="nianduan">
														<%--<c:forEach items="${stageList}" var="stage">
															<input type="checkbox" id="stageScope" name="stageScope" value="${stage.code }">${stage.name}
														</c:forEach> --%>
														<input type="hidden" name="stageScope" id="stageScope" value="" />
														<input type="checkbox" id="stageScope_one" name="stageScope_one" value="2" onclick="selectStageScope();">小学
														<input type="checkbox" id="stageScope_two" name="stageScope_two" value="3" onclick="selectStageScope();">初中
														<input type="checkbox" id="stageScope_three" name="stageScope_three" value="4" onclick="selectStageScope();">高中
													</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red">*</span>学制：</label>
												<div class="controls">
													<select class="span10" id="schoolSystem" name="schoolSystem">
													</select>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label"><span class="red">*</span>所在地：</label>
												<div class="controls" style="width:100%">
													<select class="span2" id="province" name="province">
													</select>
													<select class="span2" id="city" name="city">
													</select>
													<select class="span2" id="district" name="district">
													</select>
													<input  type="text" class="span3" id="street" name="street">
													
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td rowspan="6">联系信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">电子邮箱：</label>
												<div class="controls">
													<input type="text" id="email" name="email" placeholder="请输入电子信箱" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">邮编：</label>
												<div class="controls">
													<input type="text" id="zipcode" name="zipcode" placeholder="请输入邮箱" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr><td>
                       					<div class="control-group">
												<label class="control-label"><span class="red"></span>联系电话：</label>
												<div class="controls">
													<input type="text" id="telephone" name="telephone" placeholder="请输入联系电话" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">传真：</label>
												<div class="controls">
													<input type="text" id="fax" name="fax" placeholder="请输入传真" class="span10">
												</div>
											</div>
                       				</td></tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%">
												<label class="control-label">学校主页：</label>
												<div class="controls">
													<input type="text" id="websit" name="websit" placeholder="请输入学校网址" class="span11">
												</div>
											</div>
										</td>
									</tr>
									<tr><td><div class="control-group" style="width:100%">
												<label class="control-label"><span class="red"></span>学校地址：</label>
												<div class="controls">
													<input type="text" id="address" name="address" placeholder="请输入学校地址" class="span11">
												</div>
											</div></td>
										</tr>
										<tr><td>
                       					<div class="control-group">
												<label class="control-label">校长姓名：</label>
												<div class="controls">
													<input type="text" id="schoolMaster" name="schoolMaster" placeholder="校长姓名" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">校庆日：</label>
												<div class="controls">
													<input type="text" id="decorationDay" name="decorationDay" placeholder="校庆日" onclick="WdatePicker();" class="span10">
												</div>
											</div>
                       				</td></tr>
                       				<tr><td>
                       					<div class="control-group">
												<label class="control-label">党委负责人：</label>
												<div class="controls">
													<input type="text" id="partyCommittee" name="partyCommittee" placeholder="党委负责人" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"></label>
												<div class="controls"></div>
											</div>
                       				</td></tr>
                       				<tr>
                       					<td rowspan="7">其他信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">建校年月：</label>
												<div class="controls">
													<input type="text" id="buildDate" name="buildDate" placeholder="建校年月" onclick="WdatePicker();" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">招生范围：</label>
												<div class="controls">
													<input type="text" id="enrollScope" name="enrollScope" placeholder="招生范围" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">法定代表人：</label>
												<div class="controls">
													<input type="text" id="corporation" name="corporation" placeholder="法定代表人" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">法人证书号：</label>
												<div class="controls">
													<input type="text" id="certificate" name="certificate" placeholder="法人证书号" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">主教学语言：</label>
												<div class="controls">
													<select id="mainLanguage" name="mainLanguage" class="span10">
														
													</select>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">行政区划代码：</label>
												<div class="controls">
													<input type="text" id="regionCode" name="regionCode" placeholder="行政区划代码" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">组织机构代码：</label>
												<div class="controls">
													<input type="text" id="institutionCode" name="institutionCode" placeholder="组织机构代码" class="span10">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">学校主管部门代码：</label>
												<div class="controls">
													<input type="text" id="authority" name="authority" placeholder="学校主管部门代码" class="span10">
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%">
												<label class="control-label">学校介绍：</label>
												<div class="controls">
													<textarea rows="4" cols="5" id="remark" name="remark" class="span12"></textarea>
												</div>
											</div>
										</td>
									</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label">校徽：</label>
												<div class="controls">
													<div class="up_images">
														<img src="${pageContext.request.contextPath}/res/images/500.jpg" id="imgId"/>
														<div class="right"><p>支持jpg、gif、png、bmp格式，大小：400*260</p>
<!-- 															<a href="javascript:void(0)" class="up_img"></a> -->
															<input type="file" id="uploader" name="uploader" class="up_img"/>
														</div>
														<input type="hidden" id="entityId_badge" name="entityId_badge" value=""/>
													</div>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label">学校照片：</label>
												<div class="controls">
													<div class="up_images">
														<img src="${pageContext.request.contextPath}/res/images/500.jpg" id="imgId_"/>
														<div class="right">
															<p>支持jpg、gif、png、bmp格式，大小：400*260</p>
															<input type="file" id="uploader_" name="uploader_" class="up_img"/>
<!-- 															<a href="javascript:void(0)" class="up_img"></a> -->
														</div>
														<input type="hidden" id="entityId_image" name="entityId_image" value=""/>
													</div>
												</div>
											</div>
                       					</td>
                       				</tr>
                       			</tbody>
                       		</table>
                       	   </form>
                       	</div>
						<div class="clear"></div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
    <script>
    var checker;

    $(function () {
    	checker = initValidator();
    	$(".chzn-select").chosen();
    	$(".chzn-select-deselect").chosen({
    		allow_single_deselect: true
    	});
    	
    	//学校类别
		$.jcGcSelector("#schoolType", {tc : "JY-XXLB"}, "", function() {
			
		});
    	//办学类别
    	$.jcGcSelector("#runningType", {tc : "JY-BXLB"}, "", function() {
			
		});
    	//所在地城乡类别 
    	$.jcGcSelector("#cityType", {tc : "JY-SZDCXLB"}, "", function() {
			
		});
    	//所在地经济属性
    	$.jcGcSelector("#econonyType", {tc : "JY-SZDJJSX"}, "", function() {
			
		});
    	//主教学语言
    	$.jcGcSelector("#mainLanguage", {tc : "GB-ZGYZ"}, "", function() {
			
		});
    	//学制
		$.jcGcSelector("#schoolSystem", {tc : "XY-JY-XZ"}, "", function() {
			
		});
    	
    	$.initRegionSelector({
    		sjSelector : "province",
			shijSelector : "city",
			qxjSelector : "district"
    	});
    	
    	uploadImageFile();
    	uploadImageSchoolFile();
    });
    
    function uploadImageFile(){
    	var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "图片上传",
            fileTypeExts: "*.jpg; *.png",
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 30,
            width: 98,
            onUploadSuccess: function(file, data, response) {
           	 var $jsonObj = eval("(" + data + ")");
           	 $("#entityId_badge").val($jsonObj.uuid);
           	 $("#imgId").attr('src',$jsonObj.url); 
           	
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
    	
    }
    function uploadImageSchoolFile(){
    	var obj = $("#uploader_").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "图片上传",
            fileTypeExts: "*.jpg; *.png",
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 30,
            width: 98,
            onUploadSuccess: function(file, data, response) {
           	 	var $jsonObj = eval("(" + data + ")");
           		$("#entityId_image").val($jsonObj.uuid);
           	 	$("#imgId_").attr('src',$jsonObj.url);
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
    }
    //学段设置
    function selectStageScope(){
    	var flag ="";
		if($("#stageScope_one").is(":checked")){
			flag+=$("#stageScope_one").val()+",";
		}else{
			flag+="-1"+",";
		}
		if($("#stageScope_two").is(":checked")){
			flag+=$("#stageScope_two").val()+",";
		}else{
			flag+="-1"+",";
		}
		if($("#stageScope_three").is(":checked")){
			flag+=$("#stageScope_three").val();
		}else{
			flag+="-1";
		}
		
		$("#stageScope").val(flag)
    }
    
    function initValidator() {
		return $("#schoolForm")
				.validate(
						{
							errorClass : "myerror",
							rules : {
								"name" : {
									required : true,
									maxlength : 30,
									remote : {
										async : false,
										type : "GET",
										url : "${pageContext.request.contextPath}/teach/school/checkerGradeCode",
										data : {
											'dxlx' : 'name',
											'name' : function() {
												return $("#name").val();
											}
										}
									}
								},
								"englishName" : {
//									required : true,
//									minlength : 3,
									maxlength : 100
								},
								"code":{
									required : true,
									minlength : 3,
									maxlength : 20,
									remote : {
										async : false,
										type : "GET",
										url : "${pageContext.request.contextPath}/teach/school/checkerSchoolCode",
										data : {
											'dxlx' : 'code',
											'name' : function() {
												return $("#code").val();
											}
										}
									}
								},
								"stageScope":{
									required : true
								},
//								"telephone":{
//									required : true
//								},
								"schoolType":{
									required : true
								},
								"runningType":{
									required : true
								},
								"schoolSystem":{
									required : true
								},
								"province":{
									required : true
								},
								"city":{
									required : true								
								},
								"district":{
									required : true
								},
//								"street":{
//									required : true
//								},
								"address":{
//									required : true,
//									minlength : 10,
									maxlength : 100
								}
							},
							messages : {
								"name" : {
									remote:"学校名称已存在",
									required:"学校名称不能为空",
									maxlength:"名称不能超过15个汉字"
								},
								"englishName":{
//									required : "英文名不能为空",
									maxlength : "英文名不能超过100个字母"
								},
								"stageScope":{
									required:"学段必选一个"
								},
								"code":{
									required : "学校代码不能为空",
									remote:"学校代码已存在"
								},
								"schoolType":{
									required : "学校类型必选"
								},
								"runningType":{
									required : "办学类别必选"
								},
								"schoolSystem":{
									required : "学制必选"
								},
								"province":{
									required : "省必选"
								},
								"city":{
									required : "市必选"					
								},
								"district":{
									required : "区必选"
								},
//								"street":{
//									required : "街道必选"
//								},
								"address":{
//									required : "学校地址不能为空",
									maxlength: "地址不能超过50个汉字"
								}
							}
						});
	}
    
    function saveSchool(){
    	if (checker.form()) {
    		var loader = new loadLayer();
    		var $requestData = formData2JSONObj("#schoolForm");
    		var url = "${pageContext.request.contextPath}/teach/school/addSchool";
    		
    		var attributeId = $("#attribute").val();
    		if (attributeId != null && attributeId != "") {
    			$requestData.attributePlatform = attributeId;
    		}
    		
    		console.log($requestData);
    		loader.show();
    		$.post(url, $requestData, function(data, status) {
    			if("success" === status) {
    				//data = eval("(" + data + ")");
    				if("success" === data) {
    					$.success('保存成功');
    					if(parent.core_iframe != null) {
    							parent.core_iframe.window.location.reload();
    						} else {
    							parent.window.location.reload();
    						}
    					$.closeWindow();
    				} else {
    					$.error("保存失败");
    				}
    			}else{
    				$.error("服务器异常");
    			}
    			loader.close();
    		});
    	}
    }
    </script>
</body>
</html>