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
					<form class="form-horizontal tan_form" id="softwareresources_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									软件名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${softwareresources.name}" maxlength="20" style="width:300px">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									附件：
								</label>
								<div class="controls" style="position:relative;">
								<span style="position:absolute;top:3px;left:87px;color:#ff0000;">最大可上传200M</span>
<%-- 								<input type="text" id="fileName" name="fileName" class="span13" placeholder="" value="${softwareresources.fileName}"> --%>
						<p class="p1" style="position:relative;"><a href="javascript:void(0)"></a><input type="file" id="fileUpload" name="fileUpload" accept=".*" style="opacity:0;position:absolute;left:0;cursor:pointer"></p>
								
								</div>
								<div class="controls">
								<div id="fj" style="display:inline-block;">
                                           </div>
                                           	<c:if test="${ not empty softwareresources.id}">
                                           		<div id="uploaded"><a class="text_name" href="javascript:download(${softwareresources.id})"> 
                                                    ${softwareresources.fileName}</a>&nbsp&nbsp&nbsp<a class="close_1" data-id="' + $jsonObj.uuid + '" onclick="move(this)" style="cursor:pointer">删除</a></div>
                                           	</c:if>
                                 </div>
                                           
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea id="remark" name="remark"  placeholder="" value="${softwareresources.remark}" maxlength="400" style="width:300px;height:120px">${softwareresources.remark}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${softwareresources.id}"  />
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
	var file_uuid="";
	var file_name="";
	var file_size=0;
	
	var checker;
	$(function() {
		uploadFile();
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#softwareresources_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	function validator(){
		var flag=true;
		var name=$("#name");
		var file=$("#uploaded");
		if(name.val().trim()==""){
			$.alert("请输入软件名称！")
			return false;
		}
		if(file.length>0){
		}else{
			$.alert("请上传附件！")
			return false;
		}
		return flag;
	}
	
	function saveOrUpdate() {
		if(validator()){
			saveOrUpdate2();
		}
	}
	
	//保存或更新修改
	function saveOrUpdate2() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#softwareresources_form");
			$requestData.fileUuid=file_uuid;
			$requestData.fileName=file_name;
			$requestData.size=file_size;
			$requestData.remark=$("#remark").val();
			
			
			var url = "${ctp}/schoolaffair/softwareResources/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/softwareResources/" + $id;
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
	
	//文档上传
    function uploadFile(){
          var obj = setTimeout(function(){$("#fileUpload").uploadify({
             swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
             uploader: '${pageContext.request.contextPath}/uploader/common',
             formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                                    fileObjName : 'file',
                                    fileTypeDesc : "文件上传",
                                 	fileTypeExts : "*.*", //默认*.*
//                                  fileTypeExts : '*.doc; *.txt; *.docx;',
                                    method : 'post',
                                    multi : false, // 是否能选择多个文件
                                    auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                                    removeTimeout : 1,
                                    queueSizeLimit : 5,
                                    fileSizeLimit : 2 * 1024 * 1024,
                                    buttonText : "上传附件",
                                    requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                                    height : 20,
                                    width : 70,
                                    onUploadSuccess : function(file, data, response) {
                                          var $jsonObj = eval("(" + data + ")");
//                                           var file ='<div id="uploaded"><a class="text_name" href="' + $jsonObj.url +'">' + $jsonObj.realFileName +
//                                                     '</a>&nbsp&nbsp&nbsp<a class="close_1"  onclick="move(this)">删除</a></div>';
                                          var file ='<div id="uploaded"><a class="text_name">' + $jsonObj.realFileName +
                                                    '</a>&nbsp&nbsp&nbsp<a class="close_1"  onclick="move(this)" style="cursor:pointer">删除</a></div>';
//                                           $.success("上传成功!", 9);
                                          file_uuid=$jsonObj.uuid;
                                          file_name=$jsonObj.realFileName;
                                          file_size=$jsonObj.size;
                                          $("#uploaded").remove();
                                         $("#fj").append(file);
                                          var $value = $("#fjId").val() + "";
                                          if($value == ""){
                                              $("#fjId").val($jsonObj.uuid);
                                          }else{
                                               $("#fjId").val($jsonObj.uuid + "," + $value);
                                          }
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
	
	
    function move(obj){
    	$("#uploaded").remove();
//         var $id = $(obj).attr("data-id");
//         var $value = $("#fjId").val() + "";
//         if($value.indexOf($id) != -1){
//               $value = $value.replace($id,"");
//               if($value.indexOf(",") == 0){
//                    $value = $value.substring(1);
//               }else if($value.lastIndexOf(",") == $value.length - 1){
//                    $value = $value.substring(0,$value.length -1);
//               }
//               $("#fjId").val($value);
//         }
//         $(obj).parent().remove();
       }
    
    function download(id){
		var url="${ctp}/schoolaffair/softwareResources/downloadFile?id="+id;
		window.open(url,"_blank");
	}
</script>
</html>