<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>批量导入教师信息数据</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							批量导入教师信息数据
						</h3>
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" onclick="saveUploadInformation();" id="saveButton" class="btn btn-warning finish" disabled="disabled" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container"> 
							<form id="uploadForm" name="uploadForm"  method="post"
								action=""
								enctype="multipart/form-data" class="form-horizontal left-align form-well" 
								novalidate="novalidate">
								<fieldset title="第1步">
									<legend style="display: none;">下载教师信息模板文件</legend>
									<div class="control-group">
										<p>下载Excel模板文件并按照说明要求填写内容</p>
										<a href="${pageContext.request.contextPath}/template/teacher_info.xls" class="btn-info btn"><i class="fa fa-download"></i>模板下载</a>
									</div>
								</fieldset>
								<fieldset title="第2步">
									<legend style="display: none;">上传文件导入</legend>
									<div class="control-group">
										<div class="controls" style="margin-left:0;">
	                                        <div class="fileupload fileupload-new" data-provides="fileupload">
	                                        	<div class="select_b"  style="margin:10px 0;">
	                                            	<div class="select_div">
	                                            		 <span><span class="red">*</span>角色：</span><select id="role" name="role" style="width:150px;"></select>
	                                            	</div>
	                                            	<div class="clear"></div>
	                                            </div>
	                                            <div class="input-append">
	                                                <div class="uneditable-input span3">
	                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
	                                                </div>
	                                                <span class="btn btn-file">
	                                                	<span class="fileupload-new">请选择上传文件</span>
	                                                	<span class="fileupload-exists">重新选择</span>
	                                                	<!-- <span id="warning" style="color: red"></span> -->
	                                                	<input type="file" id="fileUpload" name="fileUpload"  accept=".xls" />
<!-- 	                                                </span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a> -->
	                                            </div>
	                                            <span style="color:#009A00">
<!-- 	                                            	<button class="btn btn-warning finish" onclick="checkForm();">验证表格</button> -->
	                                            </span>
	                                        </div>
	                                        
	                                    </div>
									</div>
								</fieldset>
								<fieldset title="第3步">
									<legend style="display: none;">查看导入结果</legend>
									<div class="control-group">
										<div class="select_success">
											<ul>
												<li><a href="javascript:void(0)"><i class="fa  fa fa-comments"></i>成功</a></li>
												<li class="on"><a href="javascript:void(0)"><i class="fa  fa-envelope-o"></i>失败</a></li>
											</ul>
										</div>
										<div class="select_table">
											<table class="table table-bordered responsive" style="display:none">
												<thead>
													<tr>
														<th>姓名</th>
														<th>性别</th>
														<th>证件号码</th>
														<th>手机号码</th>
														<th>导入结果</th>
													</tr>
												</thead>
												<tbody id="successResultId">
<!-- 													<tr><td></td><td></td><td></td><td></td></tr> -->
												</tbody>
											</table>
											<table class="table table-bordered responsive" >
												<thead>
													<tr>
														<th>姓名</th>
														<th>性别</th>
														<th>证件号码</th>
														<th>手机号码</th>
														<th>导入结果</th>
													</tr>
												</thead>
												<tbody id="failResultId">
<!-- 													<tr><td></td><td></td><td></td><td></td></tr> -->
												</tbody>
											</table>
										</div>
									</div>
								</fieldset>
								 <button href="javascript:void(0)" class="btn btn-warning finish" style="display:none;">保存</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
    $(function(){
        $('#uploadForm').stepy({
            backLabel: 'Back',
            nextLabel: 'Next',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
            });
    });
    
    $(function(){
		$(".select_success li a").click(function(){
			$(".select_success li a").parent().removeClass("on");
			$(this).parent().addClass("on");
			var i=$(this).parent().index();
			$(".select_table table").hide();
			$(".select_table table").eq(i).show()
		})
		
		//获取角色 JSON 数据  1为教师 2为管理员 4为学生的下拉列表
		$.RoleSelector({
			"condition" : {
				"userType" : "1"
			}
		});
		
		$("#uploadForm-title-2").click(function(){
			getUploadTeacher();
		});
// 		var loader = new loadDialog(); 实例化对象
// 		loader.show(); 显示
// 		loader.close(); 关闭
		//上传
		$("input[type='file']").on('change',function(event) {
			var file = $("#fileUpload").val(); 
			var role = $("#role").val();
			if(role==""){
				
				alert("请先选择角色");
				//location.reload();
				$(".stepy-widget .stepy-titles > li").removeClass("current-step");
				$("#uploadForm-title-1").addClass("current-step");
				$("fieldset").hide();
				$("#uploadForm-step-1").show();
				getUploadTeacher();
			}
			if(role!=""){
				var loader = new loadDialog();
				loader.show();
				/* $("#warning").html('上传中，请勿做其他操作'); */
				//执行上传文件操作的函数 
	 			$.ajaxFileUpload({
					//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	 				url : "${pageContext.request.contextPath}/teach/teacher/upLoadTeacherInfo?role="+role, 
	 				secureuri : false, //是否启用安全提交,默认为false    
	 				fileElementId : 'fileUpload', //文件选择框的id属性   
	 				dataType : 'text', //服务器返回的格式,可以是json或xml等   
	 				success : function(data, status) { //服务器响应成功时的处理函数
	 					/* $("#warning").html(''); */
	 					loader.close();
	 					if(status=="success"){
	 						if(data=="success"){
	 							$.success("导入成功");
	 							$(".stepy-widget .stepy-titles > li").removeClass("current-step");
	 							$("#uploadForm-title-2").addClass("current-step");
	 							$("fieldset").hide();
	 							$("#uploadForm-step-2").show();
	 							getUploadTeacher();
	 						}else{
	 							$.success("导入失败");
	 						}
	 					}else{
	 						$.success("服务器异常");
	 					}
	 				}, 
	 				error : function(data, status, e) { //服务器响应失败时的处理函数
	 					$.error("上传失败，请重新上传");
	 					location.reload();
	 					/* $("#warning").html(''); */
	 					loader.close();
	 				} 
	 			});
			}
			
		});
		
	});
    
   
    
    function submitLoadForm(){
    	var role = $("#role").val();
        if(role==""){
        	$.alert("请选择角色");
        	return false;
        }
        var fileUpload = $("#fileUpload").val();
        var path = "${pageContext.request.contextPath}/teach/teacher/upLoadTeacherInfo";  
        if(fileUpload==""){
        	$.alert("请选择上传导入文件");
        	return false;
        }else{
        	$('#uploadForm').attr("action", path).submit();
        }
    }
    

	function getUploadTeacher(){
		var path = "${pageContext.request.contextPath}/teach/teacher/getUploadInformation";
		var aj = $.ajax({
			url : path,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				upLoadInformationListSuccess = data.upLoadInformationListSuccess;
				upLoadInformationListFail = data.upLoadInformationListFail;
				loadTable(upLoadInformationListSuccess,upLoadInformationListFail);
			},
			error : function() {
				$.alert("加载导入教师异常");
			}
		});
	}
	function loadTable(teacherListSuccess,teacherListFail){
    	var stuSucc = "";
    	var stuFail = "";
    	if (teacherListSuccess.length == 0) {
    		$("#successResultId").html("<tr><td colspan='5'>暂无成功教师信息</td></tr>");
    	}else{
    		stuSucc="<tr><td colspan='5'>您已经成功导入"+teacherListSuccess.length+"条教师信息</td></tr>";
    		for (var i = 0, len = teacherListSuccess.length; i < len; i++) {
    			stuSucc+="<tr><td>"+teacherListSuccess[i].name+"</td><td>"+teacherListSuccess[i].sex+"</td><td>"+teacherListSuccess[i].idCardNumber+"</td><td>"+teacherListSuccess[i].telephone+"</td><td>"+teacherListSuccess[i].message+"</td></tr>";
    		}
    		$("#successResultId").html(stuSucc);
    	}
    	
    	if(teacherListFail.length == 0){
    		$("#saveButton").prop("disabled",false);
    		$("#failResultId").html("<tr><td colspan='5'>暂无失败教师信息，你可以点击保存</td></tr>");
    	}else{
    		$("#saveButton").prop("disabled",true);
    		stuFail="<tr><td colspan='5'>您导入"+teacherListFail.length+"条教师不合格信息</td></tr>";
    		for(var k = 0, lenTemp = teacherListFail.length; k < lenTemp; k++){
    			stuFail+="<tr><td>"+teacherListFail[k].name+"</td><td>"+teacherListFail[k].sex+"</td><td>"+teacherListFail[k].idCardNumber+"</td><td>"+teacherListFail[k].telephone+"</td><td>"+teacherListFail[k].message+"</td></tr>";
    		}
    		$("#failResultId").html(stuFail);
    	}
    }
	
	function saveUploadInformation(){
		
		$("#saveButton").attr("disabled",true);
		var loader = new loadLayer();
    	var $requestData = "";
    	var url = "${pageContext.request.contextPath}/teach/teacher/saveUploadTeacherInformation";
    	loader.show();
    	$.post(url, $requestData, function(data,status){
    		if("success" === status) {
    			if("success" === data) {
    				$.success("保存成功");
    				if(parent.core_iframe != null) {
    						parent.core_iframe.window.location.reload();
    					} else {
    						parent.window.location.reload();
    					}
    				$.closeWindow();
    			} else {
    				$.success(data);
    			}
    		}else{
    			$.error("服务器异常");
    		}
    		loader.close();
    	});
    	
    }
	</script>
</body>
</html>
