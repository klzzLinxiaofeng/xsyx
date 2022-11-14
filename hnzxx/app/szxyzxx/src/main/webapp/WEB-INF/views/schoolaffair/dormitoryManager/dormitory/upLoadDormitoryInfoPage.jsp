<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid "> 
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>批量导入宿舍信息数据</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				
				<div class="stepy-widget">
					
					<div class="widget-container gray ">
						<div class="form-container"> 
							<form id="dormitory_uploadForm" 
								name="uploadForm" 
								onsubmit="return onSubmit()" method="post" 
								action="${pageContext.request.contextPath}/schoolaffair/dormitory/upLoadDormitoryInfo" 
								enctype="multipart/form-data" class="form-horizontal left-align form-well" 
								novalidate="novalidate">
								
								<fieldset title="上传信息">
									<legend style="display: none;">上传文件导入</legend>
									<div class="control-group">
										<div class="controls">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                            <div class="input-append">
                                                <div class="uneditable-input span3">
                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
                                                </div>
                                                <span class="btn btn-file"><span class="fileupload-new">请选择上传文件</span><span class="fileupload-exists">重新选择</span>
                                                 <input type="file" id="fileUpload" name="fileUpload"  accept=".xls" />
                                                </span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
                                                
                                            </div>
                                            <span style="color:#009A00"><input value="导入表格" type="submit"/></span>
                                        </div>
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
        $('#dormitory_uploadForm').stepy({
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
		});
    
    function onSubmit(){
        var fileUpload = $("#fileUpload").val();
        if(fileUpload==""){
        	$.alert("请选择上传文件");
        	return false;
        }else{
        	return true;
        }
        return true;
    }
    
    
	</script>
</body>
</html>
