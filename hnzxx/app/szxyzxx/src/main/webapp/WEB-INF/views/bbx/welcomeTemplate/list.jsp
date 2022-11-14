<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<c:if test="${items.size()>0 }">
	<c:forEach items="${items }" var="item" varStatus="i">
		<div class="ban-active left" id="${item.fileUuid }"
			style="position: relative;">
			<a href="javascript:void(0)" class="feng"> <img
				src="${item.fileURL }">
			</a> <a href="javascript:void(0);" class="delete_img" onclick="delDiv(this,${item.id})"
				style="display: none"> <img
				src="${pageContext.request.contextPath}/res/css/bbx/images/x.png"
				class="ww"
				style='position: absolute; top: 142px; left: 0; height: 110px;'>
			</a>
			<div class="wenzi">模板${i.index+1 }</div>
		</div>
	</c:forEach>
</c:if>

<script type="text/javascript">
$(function(){
	uploadFile();
	$(".ban-active .feng").mouseover(function(){
		$(this).next().show();
	})
	$(".delete_img").mouseout(function(){
		$(this).hide()
	}) 
	
});
//=========图片上传
function uploadFile(){
	var obj = $("#uploader").uploadify({
        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
        uploader: '${pageContext.request.contextPath}/uploader/common',
        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
        fileObjName: 'file',
        fileTypeDesc: "文件上传",
        fileTypeExts: "*.gif; *.jpg; *.png;*.jpeg;*.bmp", //默认*.*
        method: 'post',
        multi: false, // 是否能选择多个文件
        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
        removeTimeout: 1,
        queueSizeLimit: 1,
        fileSizeLimit: 4 * 1024,
        buttonText: "上传文件",
        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
        height: 110,
        width: 190,
        onUploadSuccess: function(file, data, response) {
       	 var $jsonObj = eval("(" + data + ")");
       	 var imageStr = '';
        	$.post("${pageContext.request.contextPath}/bbx/welcomeTemplate/findByUUId", { fileUuid: $jsonObj.uuid },
       		   function(data){
       		      if(data==="success"){
       		    	$.success('上传成功!');
       		    	if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
     				  //显示已上传的图片 
// 				    imageStr += '<li id="'+$jsonObj.uuid+'"><div class="img"><img src="'+$jsonObj.url+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
       		      }else{
       		    	$.error("该图片已存在，请重新上传!");
       		      }
       		  
       		
       		   });  
       
	    
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

//执行删除对话框 
function delDiv(obj,id){
	executeDel(obj, id);
  }
//	执行删除
function executeDel(obj, id) {
	$.post("${pageContext.request.contextPath}/bbx/welcomeTemplate/" + id, {"_method" : "delete"}, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("删除失败，系统异常", 1);
			}
		}
	});
}


</script>