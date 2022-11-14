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
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>导入知识点</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				
				<div class="stepy-widget">
					
					<div class="widget-container gray ">
						<div class="form-container"> 
							<form id="uploadForm" 
								name="uploadForm" 
								onsubmit="return onSubmit()" method="post" 
								action="javascript:void(0);"
								enctype="multipart/form-data" class="form-horizontal left-align form-well" 
								novalidate="novalidate">
								<fieldset title="">
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
                                                </span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                <input type="hidden" name="knowledgeVersionCode" value="${knowledgeVersionCode}"/>
                                            </div>
                                            <span style="color:#009A00"><input value="导入表格" onclick="saveFile();" type="submit"/></span>
                                        </div>
                                    </div>
									</div>
								</fieldset>
								
								
								  <input type="hidden" value = "${textBookId }" id="textBookId" name = "textBookId">
								 <button href="saveFile();" class="btn btn-warning finish" style="display:none;">保存</button>
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
    function closeWindow(){
//     	var loader = new loadLayer();
//     	loader.show();
//     	if(parent.core_iframe != null) {
// 			parent.core_iframe.window.location.reload();
// 		} else {
// 			parent.window.location.reload();
// 		}
//     	$.closeWindow();
    }
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
    function saveFile() { 
    	if (onSubmit()) {
	        var form = $("form[name=uploadForm]");  
	        var options  = {    
	            url:'${pageContext.request.contextPath}/teach/catalog/upLoadInfo',    
	            type:'post',
	            enctype:"multipart/form-data",
	            success:function(data,status)    
	            {    
	            	if("success" === status) {
						$.success('操作成功');
						data = eval("(" + data + ")");
						if("success" === data.info) {
							if(parent.core_iframe != null) {
	 							parent.core_iframe.window.location.reload();
	 						} else {
	 							parent.window.location.reload();
	 						}
							$.closeWindow();
						} else {
							$.alert(data.pk);
						}
					}else{
						$.error("操作失败");
					}
					loader.close();
	            }    
	        };    
	        form.ajaxSubmit(options);  
    	}  
    }
 
	</script>
</body>
</html>
