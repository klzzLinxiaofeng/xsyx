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
                
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							批量导入家长信息数据
							<p style="float: right;" class="btn_link">
								<a class="a4" href="javascript:void(0)" onclick="getBack();"><i
									class="fa fa-mail-reply"></i>返回列表</a>
							</p>
						</h3>
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" onclick="saveUploadInformation();" id="saveButton" class="btn btn-warning finish" disabled="disabled"  style="position:absolute;right:25px;top:11px;">退出</button>
					</div>
					
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="uploadForm" 
								  name="uploadForm" 
								  method="post" 
								  enctype="multipart/form-data"
								  class="form-horizontal left-align form-well"
								  novalidate="novalidate">
								
								<fieldset title="第1步">
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
	                                                <input type="file" id="fileUpload" name="fileUpload"  accept=".xls"  onchange="fileOnchange();"/>
                                                </span>
                                            </div>
                                            <span style="color:#009A00">
                                            </span>
                                        </div>
                                    </div>
									</div>
								</fieldset>
								<fieldset title="第2步">
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
														<th>学生姓名</th>
														<th>学籍号</th>
														<th>家长姓名</th>
														<th>手机号码</th>
														<th>导入结果</th>
													</tr>
												</thead>
												<tbody id="successResultId">
												</tbody>
											</table>
											<table class="table table-bordered responsive" >
												<thead>
													<tr>
														<th>学生姓名</th>
														<th>学籍号</th>
														<th>家长姓名</th>
														<th>手机号码</th>
														<th>导入结果</th>
													</tr>
												</thead>
												<tbody id="failResultId">
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
            backLabel: '上一步',
            nextLabel: '下一步',
//             backLabel: 'Back',
//             nextLabel: 'Next',
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
		//获取角色 JSON 数据  1为教师 2为管理员 4为学生的下拉列表3为家长
		$.RoleSelector({
			"condition" : {
				"userType" : "3"
			}
		});
		

		
		/* $("#uploadForm-title-2").click(function(){
			loadTable(successList,errorList);
		}); */
		
		
		
	});
   
	 function fileOnchange(){
	    	//上传
		   var loader = new loadLayer();
			var file = $("#fileUpload").val(); 
			var role = $("#role").val();
			
			if(role==""){
				$.error("请先选择角色");
				//location.reload();
				$(".stepy-widget .stepy-titles > li").removeClass("current-step");
				$("#uploadForm-title-1").addClass("current-step");
				$("fieldset").hide();
				$("#uploadForm-step-1").show();
			}
			if(role!=""){
				var resultStatus = "error";
				loader.show();
				//执行上传文件操作的函数 
	 			$.ajaxFileUpload({
					//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
					url : "${pageContext.request.contextPath}/teach/parent/upLoadParentInfo?role="+role, 
	 				secureuri : false, //是否启用安全提交,默认为false    
	 				fileElementId : 'fileUpload', //文件选择框的id属性   
	 				dataType : 'text', //服务器返回的格式,可以是json或xml等   
	 				success : function(data, status) { //服务器响应成功时的处理函数
	 					
	 					if(status=="success"){
	 						var map = eval("("+data+")");
	 						loader.close();
	 						var resultStatus = map['status'];
	 						var errorList =  map['error'];
	 						var successList =  map['success'];
	 						
	 						/* for (var k in a) {  
	 							alert(k+'---'+a[k]);
	 						} */  
	 						
	 						if(resultStatus=="success"){
	 							$.success("导入完成");
	 							
	 							loadTable(successList,errorList);
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
	 					loader.close();
	 				} 
	 				
	 				
	 			});
				
	 			
			}
			
	}
    
    function ajaxFileUpload(){
    	var fileUpload = $("#fileUpload").val();
    	$.ajaxFileUpload({ 
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
			url : "${pageContext.request.contextPath}/teach/student/upLoadStudentInfo", 
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
    }
   
    
 
    
    function loadTable(parentListSuccess,parentListFail){
    	var parSucc = "";
    	var parFail = "";
    	if (parentListSuccess.length == 0) {
    		$("#successResultId").html("<tr><td colspan='4'>暂无成功家长信息</td></tr>");
    		
    	}else{
    		for (var i = 0, len = parentListSuccess.length; i < len; i++) {
    			parSucc+="<tr><td>"+parentListSuccess[i].studentName+"</td><td>"+parentListSuccess[i].studentNumber+"</td><td>"+parentListSuccess[i].name+"</td><td>"+parentListSuccess[i].mobile+"</td><td>"+parentListSuccess[i].errorInfo+"</td></tr>";
    		}
    		$("#successResultId").html(parSucc);
    	}
    	
    	if(parentListFail.length == 0){
    		$("#saveButton").prop("disabled",false);
    		$("#failResultId").html("<tr><td colspan='4'>暂无失败家长信息</td></tr>");
    	}else{
    		$("#saveButton").prop("disabled",true);
    		for(var k = 0, lenTemp = parentListFail.length; k < lenTemp; k++){
    			parFail+="<tr><td>"+parentListFail[k].studentName+"</td><td>"+parentListFail[k].studentNumber+"</td><td>"+parentListFail[k].name+"</td><td>"+parentListFail[k].mobile+"</td><td>"+parentListFail[k].errorInfo+"</td></tr>";
    		}
    		$("#failResultId").html(parFail);
    	}
    }
    
    function saveUploadInformation(){
    	$.closeWindow();
    }
    
  //返回列表
    function getBack() {
    	if(parent.core_iframe != null) {
			parent.core_iframe.window.location.reload();
		} else {
			parent.window.location.reload();
		}
    	$.closeWindow();
    }
  
	</script>
</body>
</html>
