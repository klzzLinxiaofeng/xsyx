<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="praiseMedal_form" action="javascript:void(0);">
					<input type="hidden" id="id" name="id" value="${praiseMedal.id}"/>
					<input type="hidden" id="fileId" name="fileId" value="${praiseMedal.fileId}"/>
							<div class="control-group">
								<label class="control-label">
									图片文件：
								</label>
								<div class="controls">
								<div style="width: 15%; float: left;">
								<img width="40px" id="img" alt="" src="${imgUrl}">
								</div>
								<div style="float: left;" class="edit">
										<div id="uploads"></div>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									头衔名称：
								</label>
								<div class="controls">
								<input type="text" id="rank" name="rank" class="span13" placeholder="" value="${praiseMedal.rank}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#praiseMedal_form").validate({
			errorClass : "myerror"/* ,
			rules : {
				rank : {required:true, maxLength:200}
			},
			messages : {
				rank : '<font color="red">勋章名称不能为空!</font>'
			} */
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#praiseMedal_form");
			var url = "${ctp}/bbx/praiseMedal/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/praiseMedal/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
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
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
	$(function(){
		  var uploadify_onSelectError = function(file, errorCode, errorMsg) {  
		        var msgText = "上传失败\n";  
		        switch (errorCode) {  
		            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
		                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
		                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
		                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:  
		                msgText += "文件大小为0";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:  
		                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;  
		                break;  
		            default:  
		                msgText += "错误代码：" + errorCode + "\n" + errorMsg;  
		        }  
		       // alert(msgText);  
		        $("#imgUpload").append("<p><font color='green'>上传失败："+msgText+"</font></p>")
		    };  
		    
		var uploadify_onSelect = function(){  
		   //$('#confirmUpload').show();
		};  
		
		//上传成功回调方法   
		var uploadify_onUploadSuccess = function(file, data, response) {  
		    data = eval("("+data+")");
		    // 显示已上传的图片
			if(data != null){
				$('#img').attr('src',data.url);
				$('#fileId').val(data.uuid);
			}
		};  
		
		var uploadify_config = {  
			'auto'  : true, 
			'multi' : false,  
	        'swf'   : '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        'uploader' : '${pageContext.request.contextPath}/uploader/common',
	        //'script' : '/admin/familyschoolOfficeDocument/upload',
	        'hideButton':true,
	        'wmode' : 'transparent',
	        'width' : '80' ,
	        'height' : '32',
	        'buttonCursor':'hand',
	        //'buttonClass':'some-class',
		    'removeTimeout' : 0,  
		    'buttonText' : '上传图片',  
		    //'buttonImage' : '${pageContext.request.contextPath}/res/css/bbx/images/add.jpg',
		    'fileTypeExts':'*.png;*.jpg;*.jpeg;*.gif;*.bmp',
		    //'buttonImage':'${ctxStatic}/images/btn.png',
		    'fileSizeLimit' : '10MB',  
		    'formData' : {jsessionId:"${pageContext.session.id}"},  //由于FLASH的BUG导致在FF中上传时获取不到SESSION，可以使用formData来传值
		    'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],  
		    'onSelect' : uploadify_onSelect,  
		    'onSelectError' : uploadify_onSelectError,  
		    //'onUploadError' : uploadify_onUploadError,  
		    'onUploadSuccess' : uploadify_onUploadSuccess,
		    'onDialogClose'  : function(queueData) {
	        },
	        onCancel:function(file){
	        },
	        onClearQueue:function(){
	        },
	        onQueueComplete:function(queueData){
	        }
		};  
		
		//调用插件上传方法   
		$("#uploads").uploadify(uploadify_config);
		//---------------------- 以上是uploadify上传插件代码----------------------
	  });
	
</script>
</html>